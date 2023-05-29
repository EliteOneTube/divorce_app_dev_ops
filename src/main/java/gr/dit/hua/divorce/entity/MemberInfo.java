package gr.dit.hua.divorce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

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

    @Column(name="username")
    private String username;


    @ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="divorce_paper_member_info",
            joinColumns=@JoinColumn(name="member_info_taxNumber"),
            inverseJoinColumns=@JoinColumn(name="divorce_paper_id")
            )
    @JsonBackReference
    private List<DivorcePaper> divorcePapers;

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

    public List<DivorcePaper> getDivorcePapers() {
        return divorcePapers;
    }

    public void setDivorcePapers(List<DivorcePaper> divorcePapers) {
        this.divorcePapers = divorcePapers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
