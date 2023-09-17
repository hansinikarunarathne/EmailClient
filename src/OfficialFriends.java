public class OfficialFriends extends Official implements Greeting {
    private String birthday;

    public OfficialFriends(String name, String email, String designation,String birthday) {
        super(name, email, designation);
        this.birthday=birthday;
    }
    @Override
    public String WayOfGreeting() {

        return "Wish you a Happy Birthday. - Hansini Karunarathne";
    }

    @Override
    public String getBirthday() {

        return birthday;
    }
}
