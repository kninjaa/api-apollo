package apollo.Model.beans;

import apollo.Model.repository.Record.Request.RrequestOrders;
import apollo.Model.repository.Record.Response.RresponseOrders;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.ast.Or;

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

    public Orders(RrequestOrders rRequestOrders, Client client, Equipment equipment, Address address){
        this.request = rRequestOrders.request();
        this.requestDate = rRequestOrders.requestDate();
        this.client = client;
        this.equipment = equipment;
        this.address = address;
        this.shipping = rRequestOrders.shipping();
    }

    public Orders UpOrders(RresponseOrders rResponseOrders, Equipment equipment, Address address){
        Orders orders = new Orders();
        this.equipment = equipment;
        this.address = address;
        if(rResponseOrders.shipping() != null) this.shipping = rResponseOrders.shipping();

        return orders;
    }
}
