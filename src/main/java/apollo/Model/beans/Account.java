package apollo.Model.beans;

import apollo.Model.repository.Record.Request.RrequestAccount;
import apollo.Model.repository.Record.Response.RresponseAccount;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "TBACCOUNT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AC_INT_ID")
    private int id;

    @Column(name = "AC_STR_LOGIN")
    private String login;

    @Column(name = "AC_STR_EMAILCORP")
    private String emailCorp;

    @Column(name = "AC_STR_PASSWORD")
    private String password;

    @Column(name = "AC_DAT_START")
    private Date dateStart;

    @Column(name = "AC_DAT_END")
    private Date dateEnd;

    @OneToOne @JoinColumn(name = "CT_INT_ID", referencedColumnName = "CT_INT_ID")
    private Client client;

    public Account (RrequestAccount rRequestAccount, Client client){
        this.login = rRequestAccount.login();
        this.emailCorp = rRequestAccount.emailCorp();
        this.password = rRequestAccount.password();
        this.dateStart = rRequestAccount.dateStart();
        this.client = client;
    }

    public Account UpAccount (RresponseAccount rResponseAccount){
        Account accountUp  = new Account();
        if (rResponseAccount.emailCorp() != null) this.emailCorp = rResponseAccount.emailCorp();
        if (rResponseAccount.password() != null) this.password = rResponseAccount.password();

        return accountUp;
    }
}