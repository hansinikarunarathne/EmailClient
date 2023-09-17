import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class MailHandler {
    private String email;
    private String password;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private static Date date = new Date();
    private ArrayList<EmailLog> emailLogs;

    public MailHandler(String email, String password) {
        this.email = email;
        this.password = password;
        this.emailLogs = SerializeDeserialize.deserialize();
    }

    public ArrayList<EmailLog> getSendEmailDetails(String date){
        ArrayList<EmailLog> logs = new ArrayList<>();
        for(EmailLog log:emailLogs){
            if (log.getDate().equals(date)){
                logs.add(log);
            }
        }
        return logs;
    }

    public void begin(ArrayList<Greeting> bdyFriends) {
        if (this.getSendEmailDetails(simpleDateFormat.format(date)).size() > 0){
            return;
        }

        for (Greeting friend: bdyFriends) {
            if ((friend.getBirthday().substring(5)).equals(simpleDateFormat.format(date).substring(5))){
                this.sendEmail(((Recipient)friend).getEmail(),"Birthday Greetings",friend.WayOfGreeting());
            }
        }
    }

    public void sendEmail(String to, String subject, String msg) {
        String email = this.email;
        String password = this.password;
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);
            EmailLog log = new EmailLog(to,subject,date);
            emailLogs.add(log);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void endClient(){
        SerializeDeserialize.serialize(emailLogs);
    }

}
