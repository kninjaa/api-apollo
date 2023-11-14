package apollo.beans;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "tbClient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ct_int_id")
    private int id;

    @Column(name = "ct_bool_situation")
    private boolean situation;

    @Column(name = "ct_str_name")
    private String name;

    @Column(name = "ct_str_cpf")
    private String cpf;

    @Column(name = "ct_str_cnpj")
    private String cnpj;

    @Column(name = "ct_str_birth")
    private Date birth;

    @Column(name = "ct_bool_deficient")
    private boolean deficient;

    @OneToOne(mappedBy = "client", fetch = FetchType.EAGER)
    private Account account;
}
