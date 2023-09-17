import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmailLog implements Serializable {
    String to;
    String Subject;
    String date;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    public EmailLog(String to, String subject,Date date) {
        this.to = to;
        this.Subject = subject;
        this.date= dateFormat.format(date);
    }

    public String getDate() {
        return date;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return Subject;
    }
}
