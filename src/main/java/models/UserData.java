package models;


public class UserData  {

    public Profile profile;
    public JobStatement jobStatement;
    public ContactInfo contactInfo;
    public Schooling schooling;

    public UserData(Profile profile, JobStatement jobStatement, ContactInfo contactInfo, Schooling schooling)
    {
        this.profile = profile;
        this.jobStatement = jobStatement;
        this.contactInfo = contactInfo;
        this.schooling = schooling;
    }
}
