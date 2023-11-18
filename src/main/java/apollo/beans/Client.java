package apollo.beans;

import apollo.beans.Account.Account;
import apollo.beans.op.EstablishmentType;
import apollo.repository.Record.RrequestClient;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "TBCLIENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CT_INT_ID")
    private int id;

    @Column(name = "CT_BOOL_SITUATION")
    private boolean situation;

    @Column(name = "CT_STR_NAMECORPORATEREASON")
    private String nameCorporateReason;

    @Column(name = "CT_STR_FANTASYNAME")
    private String fantasyName;

    @Column(name = "CT_STR_CNPJ")
    private String cnpj;

    @Column(name = "CT_STR_CONTACT")
    private String contact;

    @Column(name = "CT_FLO_RATING")
    private Float rating;

    @OneToOne(mappedBy = "client", fetch = FetchType.EAGER)
    private Account account;

    @JsonManagedReference
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Address> addresses;

    @OneToOne @JoinColumn(name = "ET_STR_ESTABLISHMENTTYPE", referencedColumnName = "ET_STR_ESTABLISHMENTTYPE")
    private EstablishmentType establishmentType;

    @JsonManagedReference
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Orders> orders;

    public Client (RrequestClient rRequestClient){
        this.situation = true;
        this.nameCorporateReason = rRequestClient.nameCorporateReason();
        this.fantasyName = rRequestClient.fantasyName();
        this.cnpj = rRequestClient.cnpj();
        this.contact = rRequestClient.contact();
        this.rating = rRequestClient.rating();
        this.establishmentType = new EstablishmentType();
    }
}