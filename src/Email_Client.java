// 200295J
import java.io.FileInputStream;
import java.util.Properties;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//import libraries
public class Email_Client {

    public static void main(String[] args) {
                
        String envFilePath = "src\\.env";

        Properties properties = new Properties(); 
        try (FileInputStream fis = new FileInputStream(envFilePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter option type: \n"
                + "1 - Adding a new recipient\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays\n"
                + "4 - Printing out details of all the emails sent\n"
                + "5 - Printing out the number of recipient objects in the application");

        String filename="Details.txt";
        String path = "logs";
        File logFile = new File(path);
        if (!logFile.exists()){
            try {
                logFile.createNewFile();
            } catch (IOException ioException) {
                System.out.println("Error occurs when creating the file");
            }
        }
        

        String email = properties.getProperty("EMAIL");
        String password = properties.getProperty("PASSWORD");
        System.out.println("EMAIL"+ email);
         System.out.println("PASSWORD"+ password
         );
        MailHandler mailHandler = new MailHandler(email,password);
        FileHandler file = new FileHandler(filename);
        RecipientFactory recipientFactory = new RecipientFactory(file);
        ArrayList<Greeting> bdayfriends = BirthdayFriends.getBdyFriends(recipientFactory.getReceivers());
        mailHandler.begin(bdayfriends);

        loop:while (true){
            int option;
            try {
                option = scanner.nextInt();
            }
            catch (Exception e){
                option = 0;
            }
            scanner.nextLine();
            switch(option){
                case 1:
                    System.out.println("Enter recipient details:");
                    String record = scanner.nextLine();
                    recipientFactory.commandRecipient(record.trim());
                    // input format - Official: nimal,nimal@gmail.com,ceo
                    break ;
                case 2:
                    System.out.println("Enter email details:");
                    String[] emailDetails = scanner.nextLine().strip().split(",");
                    mailHandler.sendEmail(emailDetails[0],emailDetails[1],emailDetails[2]);
                    // input format - email, subject, content
                    break;
                case 3:
                    System.out.println("Enter date:");
                    String date = scanner.nextLine();
                    ArrayList<String> recipientNames = BirthdayFriends.currentDayBdyFriends(date.trim());
                    for (String name: recipientNames) {
                        System.out.println(name);
                    }
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    break;
                case 4:
                    System.out.println("Enter date:");
                    String dateOfLog = scanner.nextLine();
                    ArrayList<EmailLog> logs = mailHandler.getSendEmailDetails(dateOfLog);
                    for (EmailLog log : logs){
                        System.out.println(log.getTo() +" | "+log.getSubject());
                    }
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    break;
                case 5:
                    System.out.println("No of recipients: "+ Recipient.getNoOfRecipient());
                    break;
                case -1:
                    mailHandler.endClient();
                    break loop; // terminate the aplication
                default:
                    System.out.println("Invalid input");

            }
            System.out.println("---------------------------------------------------------");
        }

        // start email client
        // code to create objects for each recipient in clientList.txt
        // use necessary variables, methods and classes

    }
}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)

