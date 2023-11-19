package apollo.Model.beans.op;

import apollo.Model.beans.Client;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBESTABLISHMENTTYPE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EstablishmentType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ET_INT_ID")
    private int id;

    @Column(name = "ET_STR_ESTABLISHMENTTYPE")
    private String establishmentType;

    @OneToOne(mappedBy = "establishmentType", fetch = FetchType.EAGER)
    private Client client;
}
