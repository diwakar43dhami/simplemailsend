import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

  public static void main(String[] args) throws Exception {
    String smtpServer = "smtp.gmail.com";
    int port = 587;
    final String username = "diwakar43.dhami@gmail.com";
    final String password = "nanocars";
    String contentType = "text/html";
    String subject = "test: bounce an email to a different address from the sender";
    String from = "diwakar43.dhami@gmail.com";
    String to = "xyz@fauxmail.com";
    String bounceAddr = "diwakar42.dhami@gmail.com";
    //  String bounceAddr  = "spshrestha01@gmail.com";
    String body = "Test: get message to bounce to a separate email address";
    Properties props = new Properties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", smtpServer);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.from", bounceAddr);
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

    //Session session = Session.getInstance(props);
    // Get runtime more runtime output when attempting to send an email
    //mailSession.setDebug(true);

    // Get the default Session object.
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });

    MimeMessage message = new MimeMessage(session);
    message.addFrom(InternetAddress.parse(from));
    message.setRecipients(Message.RecipientType.TO, to);
    message.setSubject(subject);
    message.setContent(body, contentType);

    Transport transport = session.getTransport();
    try {
      System.out.println("Sending ....");
      transport.connect(smtpServer, port, username, password);
      transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
      System.out.println("Sending done ...");
    } catch (Exception e) {
      System.err.println("Error Sending: ");
      e.printStackTrace();
    }
    transport.close();
  }

}







 /*  import javax.mail.*;
   import javax.mail.Message.RecipientType;
   import javax.mail.internet.InternetAddress;
   import javax.mail.internet.MimeMessage;
   import javax.mail.Session;
   import javax.mail.PasswordAuthentication;
   import java.util.Properties;

   public class SendEmail {
       public static void main(String [] args) {
           String to = "xyz@gmail.com";
           String from = "diwakar43.dhami@gmail.com";
           String host = "smtp.gmail.com";
           final String username = "diwakar43.dhami@gmail.com";
           final String password = "nanocars";
           //String bounceAddr  = "dhamibirendra@gmail.com";
           String bounceAddr  = "spshrestha01@gmail.com";
            String body= "Test: get message to bounce to a separate email address";
           String contentType = "text/html";

           // Get system properties
           Properties proops = System.getProperties();
           proops.put("mail.smtp.auth", true);//Enabling SMTP Authentication
           proops.put("mail.smtp.starttls.enable", true);
           proops.put("mail.smtp.port", 467);//SMTP Port
           proops.put("mail.smtp.host", host);//SMTP Host
           proops.put("mail.smtp.socketFactory.port", "465");//SSL Port
           proops.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory
            proops.put("mail.smtp.from", bounceAddr);

           // Get the default Session object.
           Session session = Session.getDefaultInstance(proops, new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication(username, password);
               }
           });

           try {
               MimeMessage message = new MimeMessage(session);//compose the message      // Create a default MimeMessage object.
               message.setFrom(new InternetAddress(from));  // Set From: header field of the header.
               message.addRecipient(Message.RecipientType.TO, // Set To: header field of the header.
                       new InternetAddress(to));
               message.setSubject("This is the Subject Line!"); // Set Subject: header field
               message.setText("hello"); // Now set the actual message
               message.setContent(body,contentType);
          //     message.setReplyTo(new javax.mail.Address[]
            //           {
            //                   new javax.mail.internet.InternetAddress("mnop@gmail.com")
             //          });
               Transport.send(message);// Send message
               System.out.println("Sent message successfully....");
           }catch (MessagingException mex) {
               System.err.println("Error Sending: ");
               mex.printStackTrace();
           }
       }
   }

*/