import java.util.ArrayList;

public class BirthdayFriends {
    private static ArrayList<Greeting> BdyFriends=new ArrayList<>();
    private static ArrayList<String> CurDayBdyFriends =new ArrayList<>();
    public static ArrayList<Greeting> getBdyFriends(ArrayList<Recipient> recipientArrayList){
        for(Recipient friends:recipientArrayList){
            if(friends instanceof Greeting){
                BdyFriends.add((Greeting)friends);
            }
        }
        return BdyFriends;
    }
    public static ArrayList<String> currentDayBdyFriends(String date){
        boolean partyDay=false;
        for(Greeting receiver:BdyFriends){
            if((receiver.getBirthday().substring(5)).equals(date.substring(5))){
                partyDay=true;
                CurDayBdyFriends.add(((Recipient)receiver).getName());
            }
        }
        if(!partyDay){
            return new ArrayList<>();
        }
        return CurDayBdyFriends;
    }
}
