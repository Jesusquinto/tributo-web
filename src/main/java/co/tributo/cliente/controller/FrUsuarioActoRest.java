package co.tributo.cliente.controller;

import com.aspose.words.Document;
import com.aspose.words.PdfSaveOptions;
import co.tributo.cliente.model.BcActoDetalle;
import co.tributo.cliente.model.FrUsuario;
import co.tributo.cliente.model.FrUsuarioActo;
import co.tributo.cliente.model.Parametros;
import co.tributo.cliente.security.CurrentUser;
import co.tributo.cliente.security.UserPrincipal;
import co.tributo.cliente.service.BcActoDetalleService;
import co.tributo.cliente.service.BcActoEntidadService;
import co.tributo.cliente.service.FrUsuarioActoService;
import co.tributo.cliente.service.FrUsuarioService;
import co.tributo.cliente.service.parametrosService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = { "*" })
@RestController
//@PreAuthorize("hasRole('USER')")
@RequestMapping("/rest/v1/")
public class FrUsuarioActoRest {

    @Autowired
    private BcActoEntidadService bcActoEntidadService;

    @Autowired
    private FrUsuarioService frUsuarioService;

    @Autowired
    private FrUsuarioActoService frUsuarioActoService;

    @Autowired
    private parametrosService parametrosService;

    @Autowired
    private BcActoDetalleService bcActoDetalleService;

    @Value("${entidadesserverurl}")    
    String entidadesserverurl;

    private float valor = 0;



    // GET
    @GetMapping("usuariosacto/list")
    public List<FrUsuarioActo> listarFrUsuarioActo() {
        return frUsuarioActoService.findAllFrUsuarioActo();

    }

    @GetMapping("usuariosacto/{id}")
    public Optional<FrUsuarioActo> getFrUsuarioActoById(@PathVariable("id") final Integer id) {
        return frUsuarioActoService.findOneFrUsuarioActo(id);
    }

    @GetMapping("usuariosacto/usuario")
    public List<FrUsuarioActo> listarFrUsuarioActoByIdUsuario(@CurrentUser UserPrincipal userPrincipal) {
        return frUsuarioActoService.listarByIdUsuario(userPrincipal.getId());
    }

    @GetMapping("usuariosacto/filter/{idEntidadContratantes}/{idEntidad}/{idActo}/{estado}")
    public List<FrUsuarioActo> filterByVarios(@PathVariable("idEntidadContratantes") final int idEntidadContratantes,
            @PathVariable("idEntidad") final int idEntidad,
            @PathVariable("idActo") final int idActo,
            @PathVariable("estado") final String estado,
            @CurrentUser UserPrincipal userPrincipal) {
        return frUsuarioActoService.filterByVarios(userPrincipal.getId(), idEntidadContratantes, idEntidad, idActo, estado);
    }

    @GetMapping("generarpdf/{id}")
    public ResponseEntity<Resource> generarPDF(@PathVariable("id") final Integer id) throws InterruptedException, Exception {
        FrUsuarioActo tramite = this.frUsuarioActoService.findById(id);
        String nombreArchivo = UUID.randomUUID().toString() + "_" + tramite.getFkUsuario().getIdentificacion().replace(" ", "");
        Path rutaPDF = Paths.get("docsgenerados").resolve(nombreArchivo.concat(".pdf")).toAbsolutePath();
        Path docEditado = Paths.get("docsgenerados").resolve(nombreArchivo.concat(".doc")).toAbsolutePath();
        Path plantilla = Paths.get("docsgenerados").resolve(nombreArchivo.concat("P.doc")).toAbsolutePath();
        POIFSFileSystem fs;
        try {

            String url = entidadesserverurl.concat(tramite.getFkActoEntidad().getEsquema());
            System.out.println(url);
            downloadUsingNIO(url, plantilla.toString());
            fs = new POIFSFileSystem(new FileInputStream(plantilla.toString()));
            HWPFDocument doc = new HWPFDocument(fs);
            switch(tramite.getEstado()) {
                case "BO":
                doc = replaceText(doc, "«¬*ESTADO*¬»", "BORRADOR");
                break;
                case "PR":
                doc = replaceText(doc, "«¬*ESTADO*¬»", "PRESENTADO");
                break;
                default:
                // code block
                    }
            

            doc = replaceText(doc, "«¬*VIGENCIA*¬»", tramite.getTipoPeriodo());

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

            if (tramite.getFechaPresentacion() != null) {
                doc = replaceText(doc, "«¬*FECHAPRESENTACION*¬»", sdf.format(tramite.getFechaPresentacion()));
            } else {
                doc = replaceText(doc, "«¬*FECHAPRESENTACION*¬»", "xx/xx/xxxx");
            }
            doc = replaceText(doc, "«¬*NOMBRETRAMITE*¬»", tramite.getFkActoEntidad().getFkBcActo().getNombreActo());
            doc = replaceText(doc, "«¬*DESCRIPCIONTRAMITE*¬»", tramite.getFkActoEntidad().getFkBcActo().getDescripcion());
            doc = replaceText(doc, "«¬*CODIGOTRAMITE*¬»", tramite.getFkActoEntidad().getCodigo());

            if(tramite.getConsecutivoActo() != null){
                doc = replaceText(doc, "«¬*CONSECUTIVO*¬»", tramite.getConsecutivoActo());
            }else{
                doc = replaceText(doc, "«¬*CONSECUTIVO*¬»", "Tramite en borrador");

            }

            doc = replaceText(doc, "«¬*NIT*¬»", tramite.getFkUsuario().getIdentificacion());
            doc = replaceText(doc, "«¬*NOMBRE*¬»", tramite.getFkUsuario().getName());
            doc = replaceText(doc, "«¬*APELLIDO*¬»", tramite.getFkUsuario().getName());
            doc = replaceText(doc, "«¬*RAZONSOCIAL*¬»", tramite.getFkUsuario().getRazonsocial());
            doc = replaceText(doc, "«¬*DIRECCION*¬»", tramite.getFkUsuario().getDireccion());
            doc = replaceText(doc, "«¬*CIUDAD*¬»", tramite.getFkUsuario().getFkDivipo().getNombreMunicipio());
            doc = replaceText(doc, "«¬*CORREO*¬»", tramite.getFkUsuario().getEmail());
            doc = replaceText(doc, "«¬*TIPOID*¬»", tramite.getFkUsuario().getTipoIdentificacion());
            doc = replaceText(doc, "«¬*ID*¬»", tramite.getFkUsuario().getIdentificacion());
            doc = replaceText(doc, "«¬*NOCONTRATO*¬»", tramite.getNumeroActo());
            doc = replaceText(doc, "«¬*CONTRATANTE*¬»", tramite.getFkEntidadContratantes().getFkContratante().getNombreContratante());

            doc = replaceText(doc, "«¬*FECHACONTRATO*¬»", sdf.format(tramite.getFechaInicioActo()));
            doc = replaceText(doc, "«¬*ENTIDAD*¬»", tramite.getFkActoEntidad().getFkBcEntidad().getNombre());
            doc = replaceText(doc, "«¬*MUNICIPIO*¬»", tramite.getFkActoEntidad().getFkBcEntidad().getFkGedivipo().getNombreMunicipio());
            doc = replaceText(doc, "«¬*BV*¬»", "$".concat(String.format("%,.2f", (double) tramite.getValorActo())));
            doc = replaceText(doc, "«¬*VE*¬»", "$".concat(String.format("%,.2f", (double) tramite.getValorApagar())));
            doc = replaceText(doc, "«¬*SC*¬»", "$".concat(String.format("%,.2f", (double) tramite.getValorApagar())));
            doc = replaceText(doc, "«¬*NP*¬»", "$".concat(String.format("%,.2f", (double) tramite.getValorApagar())));
            switch (tramite.getEstado()){
                case "BO":
                    doc = replaceText(doc, "CODIGOBARRAS12345", "");
                    break;
                default :
                    doc = replaceText(doc, "CODIGOBARRAS12345", tramite.getConsecutivoActo());
                    break;
            }

            saveWord(docEditado.toString(), doc);
            File elminarPlantilla = new File(plantilla.toString());
            elminarPlantilla.delete();

            FileOutputStream os = null;
            try{
                os = new FileOutputStream(rutaPDF.toString());
                os.write(frUsuarioActoService.doc2PDF(docEditado.toFile().getAbsolutePath()));
            }finally{
                os.close();
            }

           // PdfSaveOptions saveOptions = new PdfSaveOptions();
           // pdf.save(rutaPDF.toString(), saveOptions);
            File eliminarDocGenerado = new File(docEditado.toString());
            eliminarDocGenerado.delete();

            Path rutaArchivo = Paths.get("docsgenerados").resolve(nombreArchivo.concat(".pdf")).toAbsolutePath();
            Resource recurso = null;
            try {
                recurso = new UrlResource(rutaArchivo.toUri());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            if (!recurso.exists() && !recurso.isReadable()) {
                throw new RuntimeException("Error no se pudo cargar el archivo: " + nombreArchivo);
            }
            HttpHeaders cabecera = new HttpHeaders();
            cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
            return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("usuariosacto/calcular/{identidad}/{idacto}/{valorContrato}")
    public Float calcularValorActo(@PathVariable("idacto") final int idacto, @PathVariable("identidad") final int identidad, @PathVariable("valorContrato") final int valorContrato) {
        this.valor = 0;
        List<BcActoDetalle> bcActoDetalles = bcActoDetalleService.findByIdActo(identidad, idacto);
        bcActoDetalles.forEach(detalle -> {
            String parametros = detalle.getFkEntidadTributo().getParametroTributo();
            JSONParser parser = new JSONParser();
            JSONArray jsonObj = null;
            try {
                jsonObj = (JSONArray) parser.parse(parametros);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < jsonObj.size(); i++) {
                JSONObject parametro = (JSONObject) jsonObj.get(i);
                float mi = parametro.getAsNumber("mi").floatValue();
                float mf = parametro.getAsNumber("mf").floatValue();
                if (valorContrato >= mi && valorContrato <= mf) {
                    switch (parametro.getAsString("type")) {
                        case "f":
                            this.valor += Float.parseFloat(parametro.getAsString("value"));
                            break;
                        case "p":
                            float valor = Float.parseFloat(parametro.getAsString("value"));
                            float value = (valorContrato * valor) / 100;
                            this.valor += value;
                            break;
                        default:
                            this.valor += 0;
                    }
                }
            }
        });
        return this.valor;
    }

    @GetMapping("usuarioacto/estadisticas")
    public List<Object> getEstadisticas(@CurrentUser UserPrincipal userPrincipal) {
        return frUsuarioActoService.getEstadisticas(userPrincipal.getId());
    }

    @GetMapping("usuarioacto/grafica/{estado}/{anno}")
    public List<Object> getEstadisticasGraficas(@CurrentUser UserPrincipal userPrincipal, @PathVariable("estado") String estado, @PathVariable("anno") Integer anno ){
        return frUsuarioActoService.getEstadisticasGraficas(anno, userPrincipal.getId(), estado);
    }


    @GetMapping("usuarioacto/resumen")
    public List<FrUsuarioActo> getResumen(@CurrentUser UserPrincipal userPrincipal ){
        return frUsuarioActoService.getResumen(userPrincipal.getId());
    }



    // POST
    @PostMapping("usuariosacto/save/{identidad}/{idacto}")
    public FrUsuarioActo saveFrUsuarioActo(@PathVariable("idacto") final int idacto, @PathVariable("identidad") final int identidad, @RequestBody FrUsuarioActo frUsuarioActo, @CurrentUser UserPrincipal userPrincipal) {
        frUsuarioActo.setFechaCrea(new Date());
        frUsuarioActo.setFechaBorrador(new Date());
        FrUsuario user = new FrUsuario();
        user.setIdUsuario(userPrincipal.getId());
        frUsuarioActo.setFkUsuario(user);
        frUsuarioActo.setValorApagar(this.calcularValorActo(idacto, identidad,(int) frUsuarioActo.getValorActo()));
/*         frUsuarioActo.setConsecutivoActo(setConsecutivo(frUsuarioActo, identidad));
 */     return frUsuarioActoService.saveFrUsuarioActo(frUsuarioActo);
    }

    @PostMapping("acto/contrato/upload")
    public void upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        FrUsuarioActo acto = frUsuarioActoService.findById(id);
        if (!archivo.isEmpty()) {
            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
            try {
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir el contrato " + nombreArchivo);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
            }
            String nombreLogoAnterior = acto.getPdfRuta();
            if (nombreLogoAnterior != null && nombreLogoAnterior.length() > 0) {
                Path rutaLogoAnterior = Paths.get("uploads").resolve(nombreLogoAnterior).toAbsolutePath();
                File archivoLogoAnterior = rutaLogoAnterior.toFile();
                if (archivoLogoAnterior.exists() && archivoLogoAnterior.canRead()) {
                    archivoLogoAnterior.delete();
                }
            }
            acto.setPdfRuta(nombreArchivo);
            frUsuarioActoService.saveFrUsuarioActo(acto);
        }
    }


    // PUT
    @PutMapping("acto/presentar/{id}")
     public FrUsuarioActo presentarActo(@PathVariable("id") final Integer id){
         FrUsuarioActo acto = this.frUsuarioActoService.findById(id);
         acto.setFechaPresentacion(new Date());
         acto.setEstado("PR");    
         acto.setConsecutivoActo(setConsecutivo(acto, acto.getFkActoEntidad().getFkBcEntidad().getIdEntidad()));     
         return frUsuarioActoService.actualizarFrUsuarioActo(id, acto);         
     }
     
    @PutMapping("acto/anular/{id}")
     public FrUsuarioActo anularActo(@PathVariable("id") final Integer id){
         FrUsuarioActo acto = this.frUsuarioActoService.findById(id);
         acto.setFechaAnulado(new Date());
         acto.setEstado("AN");
         return frUsuarioActoService.actualizarFrUsuarioActo(id, acto);
     }
    
    @PutMapping("usuariosacto/{id}/{idEntidad}/{idActo}")
    public FrUsuarioActo updateFrUsuarioActo(@PathVariable("id") final Integer id, 
                                            @RequestBody FrUsuarioActo frUsuarioActo,
                                            @PathVariable("idEntidad") int idEntidad,
                                            @PathVariable("idActo") int idActo,
                                            @CurrentUser UserPrincipal usuario) {
                                                
                                                
       FrUsuarioActo acto = frUsuarioActoService.findById(id);    

        acto.setCorrecion(frUsuarioActo.getCorrecion());
        acto.setDescripcion(frUsuarioActo.getDescripcion());
        acto.setEstado("BO");
        acto.setFechaVencimientoActo(frUsuarioActo.getFechaVencimientoActo());
        acto.setFechaBorrador(new Date());
        acto.setRetencion(frUsuarioActo.getRetencion());
        acto.setFechaInicioActo(frUsuarioActo.getFechaInicioActo());
        acto.setValorApagar(calcularValorActo(idActo, idEntidad , (int) frUsuarioActo.getValorApagar()));
        acto.setFkEntidadContratantes(frUsuarioActo.getFkEntidadContratantes());
        acto.setTipoPeriodo(frUsuarioActo.getTipoPeriodo());
        acto.setNumeroActo(frUsuarioActo.getNumeroActo());
        acto.setValorActo(frUsuarioActo.getValorActo());
        return frUsuarioActoService.actualizarFrUsuarioActo(id, acto);
    }


    // OTHERS
    private String setConsecutivo(FrUsuarioActo frUsuarioActo, int identidad){
        Calendar calendario = Calendar.getInstance();
        String consecutivo;
        int año;
        Integer numerocon;
        List<Parametros> n = this.parametrosService.findByfkEntidadAndCodigo(identidad, "9998");
        Parametros nc = n.get(0);
        numerocon = nc.getValor().intValue() + 1;
        nc.setValor(new BigDecimal(numerocon));
        año = calendario.get(Calendar.YEAR);
        String codigoform;
        codigoform = this.bcActoEntidadService.findById(frUsuarioActo.getFkActoEntidad().getIdActoEntidad()).get().getCodigo();
        String consecutivocompleto;
        consecutivocompleto = String.format("%08d", numerocon);
        consecutivo = String.valueOf(año).concat(codigoform).concat("01").concat(consecutivocompleto);
        parametrosService.saveParametros(nc);
        return consecutivo;
    }



    private static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

    private static HWPFDocument replaceText(HWPFDocument doc, String findText, String replaceText) {
        Range r1 = doc.getRange();
        for (int i = 0; i < r1.numSections(); ++i) {
            Section s = r1.getSection(i);
            for (int x = 0; x < s.numParagraphs(); x++) {
                Paragraph p = s.getParagraph(x);
                for (int z = 0; z < p.numCharacterRuns(); z++) {
                    CharacterRun run = p.getCharacterRun(z);
                    String text = run.text();
                    if (text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                    }
                }
            }
        }
        return doc;
    }

    private static void saveWord(String filePath, HWPFDocument doc) throws FileNotFoundException, IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            doc.write(out);
        } finally {
            out.close();
        }
    }

}
