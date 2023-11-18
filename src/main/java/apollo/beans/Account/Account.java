package apollo.beans.Account;

import apollo.beans.Client;
import apollo.beans.op.EstablishmentType;
import apollo.repository.Record.RrequestAccount;
import apollo.repository.Record.RrequestClient;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TBACCOUNT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", ""})
public class Account implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AC_INT_ID")
    private int id;

    @Column(name = "AC_STR_LOGIN")
    private String login ;

    @Column(name = "AC_STR_EMAILCORP")
    private String emailCorp;

    @Column(name = "AC_STR_PASSWORD")
    private String password;

    @Column(name = "AC_STR_ROLE")
    private EaccountRole role;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == EaccountRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_ACCOUNT"));
        else return List.of(new SimpleGrantedAuthority("ROLE_ACCOUNT"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
