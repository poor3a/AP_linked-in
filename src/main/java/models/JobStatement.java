package models;

public class JobStatement
{
    private int id;
    private String jobTitle;
    private String companyName;
    private String location;
    private String workType;
    private byte isWorking;
    private String start;
    private String end;
    private String description;

    public JobStatement(int id, String jobTitle, String companyName, String location, String workType, byte isWorking, String start, String end, String description) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.location = location;
        this.workType = workType;
        this.isWorking = isWorking;
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public void setIsWorking(byte isWorking) {
        this.isWorking = isWorking;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLocation() {
        return location;
    }

    public String getWorkType() {
        return workType;
    }

    public byte getIsWorking() {
        return isWorking;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getDescription() {
        return description;
    }
}
