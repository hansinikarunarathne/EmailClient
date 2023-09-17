import java.util.ArrayList;

public class RecipientFactory {
    private FileHandler dataFile;
    private static ArrayList<Recipient> receivers=new ArrayList<>();

    public RecipientFactory(FileHandler dataFile) {
        this.dataFile = dataFile;
    }

    public void commandRecipient(String line){
        this.dataFile.updateFile(line);
        createRecipient(line);
    }
    private void createRecipient(String line){
        Recipient receiver=null;
        String[] type=line.split(":");
        String[] information=type[1].split(",");
        if(line.startsWith("Personal")){
            if(information.length==4){
                receiver=new PersonalFriends(information[0],information[1],information[2],information[3]);
            }
            else{
                receiver=new PersonalFriends(information[0],information[1],information[2]);

            }
        } else if (line.startsWith("Official")) {
                receiver=new Official(information[0],information[1],information[2]);
        } else if (line.startsWith("Office_friend")) {
                receiver=new OfficialFriends(information[0],information[1],information[2],information[3]);
        }
        receivers.add(receiver);
    }

    public ArrayList<Recipient> getReceivers(){
        for(String line:dataFile.readFile()){
            createRecipient(line);
        }
        return receivers;
    }
}
