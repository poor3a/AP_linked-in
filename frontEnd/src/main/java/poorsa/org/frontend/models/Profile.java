package poorsa.org.frontend.models;

public class Profile
{
    private String firstName;
    private String lastName;
    private String additionalName;
    private String birthDate;
    private String profilePicture;
    private String bg_picture;
    private String title;
    private String place;
    private String career;
    private String jobAiming;

    public Profile(String firstName, String lastName, String additionalName, String birthDate, String profilePicture, String bg_picture, String title, String place, String career, String jobAiming) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.additionalName = additionalName;
        this.birthDate = birthDate;
        this.profilePicture = profilePicture;
        this.bg_picture = bg_picture;
        this.title = title;
        this.place = place;
        this.career = career;
        this.jobAiming = jobAiming;
    }

    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdditonalName() {
        return additionalName;
    }

    public void setAdditonalName(String additionalName) {
        this.additionalName = additionalName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBg_picture() {
        return bg_picture;
    }

    public void setBg_picture(String bg_picture) {
        this.bg_picture = bg_picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getJobAiming() {
        return jobAiming;
    }

    public void setJobAiming(String jobAiming) {
        this.jobAiming = jobAiming;
    }
}
