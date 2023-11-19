package apollo.Model.beans;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "TBORDERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Orders {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORD_INT_ID")
    private int id;

    @Column(name = "ORD_STR_REQUEST")
    private String request;

    @Column(name = "ORD_DAT_REQUESTDATE")
    private Date requestDate;

    @ManyToOne @JoinColumn(name = "CT_INT_ID")
    private Client client;

    @ManyToOne @JoinColumn(name = "EQ_INT_ID")
    private Equipment equipment;

    @JsonManagedReference
    @OneToOne @JoinColumn(name = "AD_INT_ID", referencedColumnName = "AD_INT_ID")
    private Address address;

    @Column(name = "ORD_STR_SHIPPING")
    private String shipping;
}
