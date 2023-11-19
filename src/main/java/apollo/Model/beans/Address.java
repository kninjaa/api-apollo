package apollo.Model.beans;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBADDRESS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", ""})
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AD_INT_ID")
    private int id;

    @Column(name = "AD_STR_STREET")
    private String street;

    @Column(name = "AD_INT_NUMBER")
    private int number;

    @Column(name = "AD_STR_COMPLEMENT")
    private String complement;

    @Column(name = "AD_STR_CITY")
    private String city;

    @Column(name = "AD_STR_CEP")
    private String cep;

    @ManyToOne @JoinColumn(name = "CT_INT_ID")
    private Client client;

    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER)
    private Orders orders;
}
