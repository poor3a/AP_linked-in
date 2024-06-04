package models;

public class ContactInfo {
    private int id;
    private String address;
    private String email;
    private String phoneNumber_home;
    private String phoneNumber_mobile;
    private String phoneNumber_work;
    private String socialMedia1;
    private String socialMedia2;
    private String socialMedia3;
    private String website;

    public ContactInfo(int id, String address, String email, String phoneNumber_home, String phoneNumber_mobile, String phoneNumber_work, String socialMedia1, String socialMedia2, String socialMedia3, String website) {
        this.id = id;
        this.address = address;
        this.email = email;
        this.phoneNumber_home = phoneNumber_home;
        this.phoneNumber_mobile = phoneNumber_mobile;
        this.phoneNumber_work = phoneNumber_work;
        this.socialMedia1 = socialMedia1;
        this.socialMedia2 = socialMedia2;
        this.socialMedia3 = socialMedia3;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber_home() {
        return phoneNumber_home;
    }

    public void setPhoneNumber_home(String phoneNumber_home) {
        this.phoneNumber_home = phoneNumber_home;
    }

    public String getPhoneNumber_mobile() {
        return phoneNumber_mobile;
    }

    public void setPhoneNumber_mobile(String phoneNumber_mobile) {
        this.phoneNumber_mobile = phoneNumber_mobile;
    }

    public String getPhoneNumber_work() {
        return phoneNumber_work;
    }

    public void setPhoneNumber_work(String phoneNumber_work) {
        this.phoneNumber_work = phoneNumber_work;
    }

    public String getSocialMedia1() {
        return socialMedia1;
    }

    public void setSocialMedia1(String socialMedia1) {
        this.socialMedia1 = socialMedia1;
    }

    public String getSocialMedia2() {
        return socialMedia2;
    }

    public void setSocialMedia2(String socialMedia2) {
        this.socialMedia2 = socialMedia2;
    }

    public String getSocialMedia3() {
        return socialMedia3;
    }

    public void setSocialMedia3(String socialMedia3) {
        this.socialMedia3 = socialMedia3;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
