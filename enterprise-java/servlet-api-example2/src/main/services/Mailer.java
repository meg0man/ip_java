package main.services;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Created by admin on 14.04.2017.
 */
public class Mailer {
    private String username;
    private String password;
    private Properties props;

    // loggerffi1
    // loggerpwd
    public Mailer(String username, String password) {
        this.username = username;
        this.password = password;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

//        props.setProperty("mail.smtp.host", "smtp.gmail.com");
//        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.setProperty("mail.smtp.socketFactory.fallback", "false");
//        props.setProperty("mail.smtp.port", "465");
//        props.setProperty("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.debug", "true");
//        props.put("mail.store.protocol", "pop3");
//        props.put("mail.transport.protocol", "smtp");
    }

    public void SendMail(String destination, String messageSubject, String messageText, String fileName) {

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destination));
            message.setSubject(messageSubject);
            message.setText(messageText);

//            Multipart multipart = new MimeMultipart();
//
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText(messageText);
//
//            multipart.addBodyPart(messageBodyPart);
//
//            if ((!(fileName == null)) && (fileName.length() > 0)) {
//
//                String filename = fileName;
//                DataSource source = new FileDataSource(filename);
//
//                messageBodyPart = new MimeBodyPart();
//                messageBodyPart.setDataHandler(new DataHandler(source));
//                messageBodyPart.setFileName(filename);
//
//                multipart.addBodyPart(messageBodyPart);
//            }
//
//
//            message.setContent(multipart);

            Transport.send(message);

            //System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
