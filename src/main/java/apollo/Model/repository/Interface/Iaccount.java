package apollo.Model.repository.Interface;

import apollo.Model.beans.Account;
import apollo.Model.beans.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface Iaccount extends JpaRepository<Account, String> {
    Optional<Account> findByLogin(String login);
    Optional<Account> findByClient(Client client);
    Optional<Account> findByEmailCorp(String emailCorp);
    Optional<Account> findByPassword(String password);
}
