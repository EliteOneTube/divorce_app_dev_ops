package gr.dit.hua.divorce.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="divorce_paper")
public class DivorcePaper {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="tax_number", insertable = false, updatable = false)
    private MemberInfo lawyer1;

    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="tax_number", insertable = false, updatable = false)
    private MemberInfo lawyer2;

    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="tax_number", insertable = false, updatable = false)
    private MemberInfo spouse1;

    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="tax_number", insertable = false, updatable = false)
    private MemberInfo spouse2;

    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="tax_number", insertable = false, updatable = false)
    private MemberInfo notary;

    @Column(name="created_at")
    private Date date;

    @Column(name="number_of_children")
    private Integer numberOfChildren;

    @Column(name="child_support")
    private Integer childSupport;

    @Column(name="restore_name")
    private Integer restoreName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MemberInfo getLawyer1() {
        return lawyer1;
    }

    public void setLawyer1(MemberInfo lawyer1) {
        this.lawyer1 = lawyer1;
    }

    public MemberInfo getLawyer2() {
        return lawyer2;
    }

    public void setLawyer2(MemberInfo lawyer2) {
        this.lawyer2 = lawyer2;
    }

    public MemberInfo getSpouse1() {
        return spouse1;
    }

    public void setSpouse1(MemberInfo spouse1) {
        this.spouse1 = spouse1;
    }

    public MemberInfo getSpouse2() {
        return spouse2;
    }

    public void setSpouse2(MemberInfo spouse2) {
        this.spouse2 = spouse2;
    }

    public MemberInfo getNotary() {
        return notary;
    }

    public void setNotary(MemberInfo notary) {
        this.notary = notary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public Integer getChildSupport() {
        return childSupport;
    }

    public void setChildSupport(Integer childSupport) {
        this.childSupport = childSupport;
    }

    public Integer getRestoreName() {
        return restoreName;
    }

    public void setRestoreName(Integer restoreName) {
        this.restoreName = restoreName;
    }
}
