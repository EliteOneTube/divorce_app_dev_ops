package gr.dit.hua.divorce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="member_info")
public class MemberInfo {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="afm")
    private String afm;

    @Column(name="surname")
    private String surname;

    @Column(name="accepted")
    private Integer accepted;
}
