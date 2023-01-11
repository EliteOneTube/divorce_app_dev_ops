package gr.dit.hua.divorce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="member_info")
public class MemberInfo {
    @Id
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="enabled")
    private boolean enabled;


    @Column(name="taxNumber")
    private String taxNumber;

    @Column(name="fullName")
    private String fullName;

    @Column(name="email")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;


    public MemberInfo() {
    }

    public MemberInfo(String username, String password, Boolean enabled, String tax_number, String fullName, String email) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.taxNumber = tax_number;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
