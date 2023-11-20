package apollo.Model.beans;

import apollo.Model.repository.Record.Request.RresquestAddress;
import apollo.Model.repository.Record.Response.RresponseAddress;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "orders"})
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

    @JsonIgnoreProperties
    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER)
    private Orders orders;

    public Address (RresquestAddress rResquestAddress, Client client){
        this.street = rResquestAddress.street();
        this.number = rResquestAddress.number();
        this.complement = rResquestAddress.complement();
        this.city = rResquestAddress.city();
        this.cep = rResquestAddress.cep();
        this.client = client;
    }

    public Address UpAddress(RresponseAddress rResponseAddress){
        Address address = new Address();
        if (rResponseAddress.street() != null) this.street = rResponseAddress.street();
        if (rResponseAddress.number() > -1) this.number = rResponseAddress.number();
        if (rResponseAddress.complement() != null) this.complement = rResponseAddress.complement();
        if (rResponseAddress.city() != null) this.city = rResponseAddress.city();
        if (rResponseAddress.cep() != null) this.cep = rResponseAddress.cep();

        return address;
    }
}
