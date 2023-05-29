package gr.dit.hua.divorce.templates;

import jakarta.persistence.Column;

public class UserDetails {
    private String username;

    private String password;

    private String role;

    private String taxNumber;

    private String fullName;

    private String email;

    public UserDetails() {
    }

    public UserDetails(String username, String password, String role, String taxNumber, String fullName, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.taxNumber = taxNumber;
        this.fullName = fullName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
