package org.cafevariome.model;

public class AccessRequest {
    public String requestID;
    public String firstName;
    public String lastName;
    public String email;
    public String affiliation;
    public String justification;

    public AccessRequest() {}

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
}
