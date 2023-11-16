package apollo.repository.Interface;

import apollo.beans.Account;
import apollo.beans.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Iaccount extends JpaRepository<Account, String> {
    Optional<Account> findByClient(Client client);
    List<Account> findByLogin(String login);
    List<Account> findByEmailCorp(String emailCorp);
}
