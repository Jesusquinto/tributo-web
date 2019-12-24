/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.repository;


import co.tributo.cliente.model.FrUsuarioActo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ricardo
 */
public interface FrUsuarioActoRepository extends JpaRepository<FrUsuarioActo, Integer>{
    
    @Query("SELECT f FROM FrUsuarioActo f WHERE f.fkUsuario.idUsuario = :idUsuario AND f.estado != 'BO' AND f.estado != 'AN' order by f.fechaCrea desc")
    public List<FrUsuarioActo> listarByIdUsuario(@Param("idUsuario") Long idUsuario);    
   
    @Query("SELECT f FROM FrUsuarioActo f WHERE (f.fkUsuario.idUsuario = :idUsuario OR :idUsuario = 0) AND (f.fkEntidadContratantes.idEntidadContratantes = :idEntidadContratantes OR :idEntidadContratantes = 0 ) and (f.fkActoEntidad.fkBcEntidad.idEntidad = :idEntidad OR :idEntidad = 0 ) AND (f.fkActoEntidad.fkBcActo.idActo = :idActo OR :idActo = 0 ) AND (f.estado = :estado OR :estado = '0') order by f.fechaCrea desc ")
    public List<FrUsuarioActo> filterByVarios(@Param("idUsuario") Long idUsuario, @Param("idEntidadContratantes") int idEntidadContratantes, @Param("idEntidad") int idEntidad, @Param("idActo") int idActo, @Param("estado") String estado);


    @Query(value="select  'presentados' as estado,ISNULL( SUM(valor_apagar),0 ) as valor from fr_usuario_acto where estado = 'PR' and fk_usuario = :idUsuario and fecha_crea  BETWEEN  DATEADD(DAY,-7,CURRENT_TIMESTAMP) and CURRENT_TIMESTAMP\n" +
                    "UNION\n" +
                    "select  'borradores' as estado, ISNULL( count(*),0 ) as valor from fr_usuario_acto where estado = 'BO' and fk_usuario = :idUsuario and fecha_crea BETWEEN DATEADD(DAY,-7,CURRENT_TIMESTAMP) and CURRENT_TIMESTAMP\n" +
                    "UNION\n" +
                    "select  'pagados' as estado,ISNULL( SUM(valor_apagar),0 ) as valor from fr_usuario_acto where estado = 'PA' and fk_usuario = :idUsuario and fecha_crea  BETWEEN  DATEADD(DAY,-7,CURRENT_TIMESTAMP) and CURRENT_TIMESTAMP\n" +
                    "UNION\n" +
                    "select  'contratos' as estado,ISNULL( count(*),0 ) as valor from fr_usuario_acto where fk_usuario = :idUsuario and fecha_crea  BETWEEN  DATEADD(DAY,-7,CURRENT_TIMESTAMP) and CURRENT_TIMESTAMP\n"
                    , nativeQuery = true)
    public List<Object> getEstadisticas(@Param("idUsuario") Long idUsuario);
    
    
    @Query(value="select CASE SUBSTRING ( CONVERT(VARCHAR(6), fecha_crea, 112),5,2)\n" +
"                WHEN '01' THEN '01-ENERO'\n" +
"                WHEN '02' THEN '02-FEBRERO'\n" +
"                WHEN '03' THEN '03-MARZO'\n" +
"                WHEN '04' THEN '04-ABRIL'\n" +
"                WHEN '05' THEN '05-MAYO'\n" +
"                WHEN '06' THEN '06-JUNIO'\n" +
"                WHEN '07' THEN '07-JULIO'\n" +
"                WHEN '08' THEN '08-AGOSTO'\n" +
"                WHEN '09' THEN '09-SEPTIEMBRE'\n" +
"                WHEN '10' THEN '10-OCTUBRE'\n" +
"                WHEN '11' THEN '11-NOVIEMBRE'\n" +
"                WHEN '12' THEN '12-DICIEMBRE'\n" +
"        ELSE 'N/A'\n" +
"             END\n" +
"    AS mes,estado, ISNULL( SUM(valor_apagar),0 ) as valor, ISNULL(COUNT(*), 0) as tramites,\n" +
"                 (select nombre from bc_entidad where id_entidad = (select fk_bc_entidad from bc_acto_entidad where id_acto_entidad =\n" +
"                            (select distinct TOP 1 fk_acto_entidad from fr_usuario_acto where fk_usuario = :idUsuario  ))) as frecuente from fr_usuario_acto where  fk_usuario = :idUsuario and estado = :estado\n" +
"   and CONVERT(VARCHAR(4), fecha_crea, 112)  = :anno\n" +
"group by SUBSTRING ( CONVERT(VARCHAR(6), fecha_crea, 112) ,5,2 ), estado, fk_acto_entidad\n" +
"order by  mes, estado", nativeQuery = true)
    public List<Object> getEstadisticasGraficas(@Param("anno") Integer anno, @Param("idUsuario") Long idUsuario, @Param("estado") String estado);
    
    

    @Query(value="select * from fr_usuario_acto ", nativeQuery=true)
    public List<FrUsuarioActo> getEstadisticasGraficas(@Param("idUsuario") Long idUsuario);

    
    
    
}
