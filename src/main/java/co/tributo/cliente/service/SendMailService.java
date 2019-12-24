package co.tributo.cliente.service;

import co.tributo.cliente.model.FrUsuario;
import co.tributo.cliente.model.FrUsuarioActo;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {
    
        @Autowired
        private JavaMailSender javaMailSender;
        
        
        @Autowired
        private FrUsuarioActoService frUsuarioActoService;
        
        public void sendMailVerification(FrUsuario usuario) throws MessagingException{
                MimeMessage message = javaMailSender.createMimeMessage();        
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("noreply.tributo@gmail.com");
            helper.setTo(usuario.getEmail());
            helper.setSubject("¡Solo queda un paso para completar su registro!");
            helper.setText("<html><head></head><body style=\"\n" +
                            "    text-align: center;\n" +
                            "\">\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "<img src=\"http://tributo.co/assets/img/theme/info_tributo_email.png\">\n" +
                            "<h2 style=\"\n" +
                            "    font-family: sans-serif;\n" +
                            "    font-weight: 800;\n" +
                            "    color: #1a1248;\n" +
                            "    \">SOLO TE QUEDA UN PASO!</h2>\n" +
                            "<h1 style=\"\n" +
                            "    font-family: sans-serif;\n" +
                            "    color: #1a1248;\n" +
                            "    margin-top: 0;\n" +
                            "	font-size : 1.5rem;\n" +
                            "	position: relative;\n" +
                            "	top: -1rem\n" +
                            "\">Solo dale click al boton para verificar tu cuenta</h1>\n" +
                            "\n" +
                            "\n" +
                            "	<a onmouseover=\"this.style.background='#372f6a'; this.style.cursor='pointer'\" onmouseout=\"this.style.background='#1a1248'; this.style.cursor='default'\" style=\"text-decoration: none; background: rgb(26, 18, 72); color: white; padding: 15px; font-size: medium; border-radius: 4px; font-family: sans-serif; cursor: default;\" href=\"http://www.tributo.co/verificar/"+usuario.getVerificaionCode()+"\">Verificar</a>\n" +
                            "\n" +
                            "\n" +
                            "<h2 style=\"\n" +
                            "    font-family: sans-serif;\n" +
                            "    font-weight: 800;\n" +
                            "    color: #1a1248;\n" +
                            "\">Fecha expiración</h2>\n" +
                            "<h3 style=\"\n" +
                            "    position: relative;\n" +
                            "    top: -1rem;\n" +
                            "    font-family: sans-serif;\n" +
                            "    color: #ff2770;\n" +
                            "\">"+format.format(usuario.getExpirationCode())+"</h3>\n" +
                            "\n" +
                            "\n" +
                            "</body></html>",true);
            
            javaMailSender.send(message );  
        }
        
        
         public void sendMailVerificaionSuccess(FrUsuario user) throws MessagingException{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            MimeMessage message = javaMailSender.createMimeMessage();            
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("noreply.tributo@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("¡Su cuenta ha sido verificada!");
            helper.setText("<html><head></head><body style=\"\n" +
                            "    text-align: center;\n" +
                            "\">\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "<img src=\"http://tributo.co/assets/img/theme/info_tributo_email.png\" >\n" +
                            "<h2 style=\"\n" +
                            "    font-family: sans-serif;\n" +
                            "    font-weight: 800;\n" +
                            "    color: #1a1248;\n" +
                            "    \">PERFECTO! TU CUENTA HA SIDO VERIFICADA CORRECTAMENTE</h2>\n" +
                            "	\n" +
                            "	<h3 style=\"\n" +
                            "    font-family: sans-serif;\n" +
                            "    font-weight: 800;\n" +
                            "    color: #1a1248;\n" +
                            "    \">Ahora puedes completar los datos de tu perfil tributario y tramitar con nosotros!</h3>\n" +
                            "\n" +
                            "<h2 style=\"\n" +
                            "    font-family: sans-serif;\n" +
                            "    font-weight: 800;\n" +
                            "    color: #1a1248;\n" +
                            "\">Fecha de verificacion</h2>\n" +
                            "<h3 style=\"\n" +
                            "    position: relative;\n" +
                            "    top: -1rem;\n" +
                            "    font-family: sans-serif;\n" +
                            "    color: #ff2770;\n" +
                            "\">"+format.format(user.getFechacreacion())+"</h3>\n" +
                            "\n" +
                            "\n" +
                            "</body></html>",true);
            
            javaMailSender.send(message );
        }
        
        
        
        public void sendMail(String to, String nombre, int idFrUsuarioActo) throws MessagingException{
        
            FrUsuarioActo tramite = this.frUsuarioActoService.findById(idFrUsuarioActo);
            String key = UUID.randomUUID().toString() + "_" + nombre.replace(" ", "");
            
            tramite.setKeycode(key);
                Calendar cal = Calendar.getInstance(); // creates calendar
                cal.setTime(new Date()); // sets calendar time/date
                cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
            tramite.setExpiracionKeycode(cal.getTime());
            
            frUsuarioActoService.saveFrUsuarioActo(tramite);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            MimeMessage message = javaMailSender.createMimeMessage();            
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("noreply.tributo@gmail.com");
            helper.setTo(to);
            helper.setSubject("¡Su llave generada está lista para usar!");
            helper.setText("<html><head></head><body style=\"\n" +
                                "    text-align: center;\n" +
                                "\">\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "<img src=\"http://tributo.co/assets/img/theme/info_tributo_email.png\">\n" +
                                "<h2 style=\"\n" +
                                "    font-family: sans-serif;\n" +
                                "    font-weight: 800;\n" +
                                "    color: #1a1248;\n" +
                                "    \">SE HA GENERADO SU LLAVE</h2>\n" +
                                "<h1 style=\"\n" +
                                "    font-family: sans-serif;\n" +
                                "    color: #ff2770;\n" +
                                "    margin-top: 0;\n" +
                                "	font-size : 1.5rem;\n" +
                                "	position: relative;\n" +
                                "	top: -1rem\n" +
                                "\">"+key+"</h1>\n" +
                                "\n" +
                                "<h2 style=\"\n" +
                                "    font-family: sans-serif;\n" +
                                "    font-weight: 800;\n" +
                                "    color: #1a1248;\n" +
                                "    \">TRAMITE</h2>\n" +
                                "<h3 style=\"\n" +
                                "    position: relative;\n" +
                                "    top: -1rem;\n" +
                                "    font-family: sans-serif;\n" +
                                "    color: #ff2770;\n" +
                                "\">"+tramite.getConsecutivoActo()+"</h3>\n" +
                                "\n" +
                                "<h2 style=\"\n" +
                                "    font-family: sans-serif;\n" +
                                "    font-weight: 800;\n" +
                                "    color: #1a1248;\n" +
                                "\">Fecha expiración</h2>\n" +
                                "<h3 style=\"\n" +
                                "    position: relative;\n" +
                                "    top: -1rem;\n" +
                                "    font-family: sans-serif;\n" +
                                "    color: #ff2770;\n" +
                                "\">"+format.format(tramite.getExpiracionKeycode())+"</h3>\n" +
                                "\n" +
                                "\n" +
                                "</body></html>",true);
            System.out.println("HASTA AQUÍ FUNCIONA");
            javaMailSender.send(message );
        }
    
}
