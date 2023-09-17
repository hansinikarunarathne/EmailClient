import java.io.*;
import java.util.ArrayList;

public class SerializeDeserialize {

    public static void serialize(ArrayList<EmailLog> emailLogs){
        try{
//            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("logs",true)){
//                protected void writeStreamHeader() throws IOException {
//                    reset();
//                }
//            };
//            objectOutputStream.writeObject(emailLog);
//            objectOutputStream.close();

            FileOutputStream fileOutputStream = new FileOutputStream("Email_logs.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(emailLogs);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("Can't serialize the object");
        }
    }
    public static ArrayList<EmailLog> deserialize(){
        ArrayList<EmailLog> logs=new ArrayList<>();
        try{
            FileInputStream fileInputStream = new FileInputStream("Email_logs.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            logs = (ArrayList) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e){
            System.out.println("Error Occur in deserializing");
        }

//        try{
//            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("logs"));
//            while (true){
//                try{
//                     logs.add((EmailLog) objectInputStream.readObject());
//                }catch (Exception error){
//                    break;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found");
//        } catch (IOException e) {
//            System.out.println("error occur in input and output");
//        }
        return logs;
    }
}
