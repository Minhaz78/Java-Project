package cqu.mavenproject1;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SimpleEmail {

    public static void sendEmail(String fromEmail, String username, String subject, String body) {
        try {
            String smtpHostServer = "0.0.0.0";
            String emailID = "responder@example.com";
            int smtpPort = 1025;

            Properties props = System.getProperties();
            props.put("mail.smtp.host", smtpHostServer);
            props.put("mail.smtp.port", smtpPort);

            Session session = Session.getInstance(props, null);

            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, username));

            msg.setReplyTo(InternetAddress.parse(fromEmail, false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new java.util.Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailID, false));
            Transport.send(msg);

            System.out.println("Email Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
