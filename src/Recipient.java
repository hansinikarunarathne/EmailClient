public abstract class Recipient {
    protected String name;
    protected String email;
    private static int NoOfRecipient=0;

    public Recipient(String name,String email){
        this.name=name;
        this.email=email;
        NoOfRecipient++;
    }

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }

    public static int getNoOfRecipient() {
        return NoOfRecipient;
    }
}
