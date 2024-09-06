package org.cafevariome.model;

public class AccessRequest extends AppModel {
    private String requestID;
    private String firstName;
    private String lastName;
    private String email;
    private String affiliation;
    private String justification;

    public AccessRequest() {
    }

    public AccessRequest(String requestID,
                         String firstName,
                         String lastName,
                         String email,
                         String affiliation,
                         String justification) {
        this.requestID = requestID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.affiliation = affiliation;
        this.justification = justification;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }
}
