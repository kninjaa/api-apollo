package apollo.beans;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "tbUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", ""})
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_int_id")
    private int id;

    @Column(name = "us_str_login")
    private String login ;

    @Column(name = "us_str_email")
    private String email;

    @Column(name = "us_str_password")
    private String password;

    @Column(name = "us_dat_start")
    private Date DateStart;

    @Column(name = "us_dat_end")
    private Date DateEnd;

    @OneToOne @JoinColumn(name = "ct_int_id", referencedColumnName = "ct_int_id")
    private Client client;
}
