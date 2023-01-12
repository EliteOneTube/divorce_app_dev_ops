package gr.dit.hua.divorce.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="divorce_paper")
public class DivorcePaper {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @OneToMany(fetch= FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="member_info_taxNumber")
    private List<MemberInfo> members;

    @Column(name="created_at")
    private Date created_at;

    @Column(name="numberOfChildren")
    private Integer numberOfChildren;

    @Column(name="childSupport")
    private Integer childSupport;

    @Column(name="restoreName")
    private boolean restoreName;

    public DivorcePaper() {
    }

    public DivorcePaper(Integer numberOfChildren, Integer childSupport, boolean restoreName) {
        this.numberOfChildren = numberOfChildren;
        this.childSupport = childSupport;
        this.restoreName = restoreName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
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

    public boolean getRestoreName() {
        return restoreName;
    }

    public void setRestoreName(boolean restoreName) {
        this.restoreName = restoreName;
    }

    public List<MemberInfo> getMembers() {
        return members;
    }

    public void setMembers(List<MemberInfo> members) {
        this.members = members;
    }
}
