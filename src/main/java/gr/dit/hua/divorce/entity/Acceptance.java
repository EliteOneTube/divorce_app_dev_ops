package gr.dit.hua.divorce.entity;

import jakarta.persistence.*;

@Entity
@Table(name="acceptance")
public class Acceptance {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="acceptance")
    private boolean acceptance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private DivorcePaper divorcePaper;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tax_number")
    private MemberInfo memberInfo;

    public Acceptance() {
    }

    public Acceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAcceptance() {
        return acceptance;
    }

    public void setAcceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }

    public DivorcePaper getDivorcePaper() {
        return divorcePaper;
    }

    public void setDivorcePaper(DivorcePaper divorcePaper) {
        this.divorcePaper = divorcePaper;
    }

    public MemberInfo getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }
}
