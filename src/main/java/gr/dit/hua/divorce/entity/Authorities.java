package gr.dit.hua.divorce.entity;

import jakarta.persistence.*;

@Entity
@Table(name="authorities")
public class Authorities {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="authority")
    private String authority;

    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="username")
    private Users user;

    public Authorities() {
    }
}
