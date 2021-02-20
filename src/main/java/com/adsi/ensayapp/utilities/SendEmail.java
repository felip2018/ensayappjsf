package com.adsi.ensayapp.utilities;

import com.adsi.ensayapp.dto.EmailMessageDTO;
import com.adsi.ensayapp.model.Usuario;
import java.util.Properties;
import javax.mail.Address;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendEmail {
    
    private final Logger log = LogManager.getRootLogger();
    
    public String sendEmailMessage(EmailMessageDTO emailMessageDto) {
        String response;
        // Recipient's email ID needs to be mentioned.
        String to = emailMessageDto.getTo();

        String from = Constants.EMAIL_FROM;
        final String username = Constants.EMAIL_USERNAME;
        final String password = Constants.EMAIL_PASSWORD;

        // Assuming you are sending email through relay.jangosmtp.net
        String host = Constants.HOST;

        Properties props = new Properties();
        props.put("mail.smtp.auth", Constants.AUTH);
        props.put("mail.smtp.starttls.enable", Constants.START_TLS);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", Constants.PORT);

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            
            // Set To: header field of the header.
            
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            

            // Set Subject: header field
            message.setSubject(emailMessageDto.getSubject());

            // Now set the actual message
            message.setContent(emailMessageDto.getFullMessage(),"text/html");
            // Send message
            Transport.send(message);

            response = "Se ha enviado el correo electrónico correctamente.";
            
        } catch (MessagingException e) {
            //throw new RuntimeException(e);
            response = "Ha ocurrido un error: "+e.getMessage();
        }
        
        return response;
    }
}
