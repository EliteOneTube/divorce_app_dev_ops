package gr.dit.hua.divorce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="member_info")
public class MemberInfo {
    @Id
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

    public MemberInfo(String taxNumber, String fullName, String email) {
        this.taxNumber = taxNumber;
        this.fullName = fullName;
        this.email = email;
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
