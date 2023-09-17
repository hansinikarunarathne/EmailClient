public class PersonalFriends extends Recipient implements Greeting {
    private String birthday;
    private String nickname;
    public PersonalFriends(String name, String email,String nickname,String birthday) {
        super(name, email);
        this.birthday=birthday;
        this.nickname=nickname;
    }

    public PersonalFriends(String name, String email, String birthday) {
        super(name, email);
        this.birthday = birthday;
    }

    @Override
    public String WayOfGreeting() {
        return "hugs and love on your birthday - Hansini Karunarathne";
    }

    @Override
    public String getBirthday() {
        return birthday;
    }
}
