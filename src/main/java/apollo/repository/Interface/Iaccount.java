package apollo.repository.Interface;

import apollo.beans.Account.Account;
import apollo.beans.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface Iaccount extends JpaRepository<Account, String> {
    UserDetails findByLogin(String login);



    Optional<Account> findByClient(Client client);
    List<Account> findByEmailCorp(String emailCorp);
}
