package models;

public class LeadDTO {

    private String status;
    private String source;
    private String assigned;
    private String tag;
    private String leadName;
    private String address;
    private String position;
    private String city;
    private String emailAddress;
    private String state;
    private String website;
    private String country;
    private String phone;
    private String zipCode;
    private String leadValue;
    private String language;
    private String company;
    private String description;
    private String dateContacted;
    private int flag;
    private int flagEdit;

    public LeadDTO() {}


    public LeadDTO(LeadDTO leadData) {
        this.status = leadData.status;
        this.source = leadData.source;
        this.assigned = leadData.assigned;
        this.tag = leadData.tag;
        this.leadName = leadData.leadName;
        this.address = leadData.address;
        this.position = leadData.position;
        this.city = leadData.city;
        this.emailAddress = leadData.emailAddress;
        this.state = leadData.state;
        this.website = leadData.website;
        this.country = leadData.country;
        this.phone = leadData.phone;
        this.zipCode = leadData.zipCode;
        this.leadValue = leadData.leadValue;
        this.language = leadData.language;
        this.company = leadData.company;
        this.description = leadData.description;
        this.dateContacted = leadData.dateContacted;
        this.flag = leadData.flag;
        this.flagEdit = leadData.flagEdit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLeadValue() {
        return leadValue;
    }

    public void setLeadValue(String leadValue) {
        this.leadValue = leadValue;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateContacted() {
        return dateContacted;
    }

    public void setDateContacted(String dateContacted) {
        this.dateContacted = dateContacted;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getFlagEdit() {
        return flagEdit;
    }

    public void setFlagEdit(int flagEdit) {
        this.flagEdit = flagEdit;
    }
}
