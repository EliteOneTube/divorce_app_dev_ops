package gr.dit.hua.divorce.entity;

import jakarta.persistence.*;

@Entity
@Table(name="divorce_paper")
public class DivorcePaper {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private String id;

    @OneToOne(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="member_info_id")
    private MemberInfo lawyer1;

    @OneToOne(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="member_info_id")
    private MemberInfo lawyer2;

    @OneToOne(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="member_info_id")
    private MemberInfo spouse1;

    @OneToOne(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="member_info_id")
    private MemberInfo spouse2;

    @OneToOne(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="member_info_id")
    private MemberInfo notary;

    @Column(name="date")
    private String date;

    @Column(name="number_of_children")
    private Integer numberOfChildren;

    @Column(name="child_support")
    private Integer childSupport;

    @Column(name="restore_name")
    private Integer restoreName;

    //TODO: add assets and debts
}
