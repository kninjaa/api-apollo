package apollo.repository.Interface;

import apollo.beans.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface Iclient extends JpaRepository<Client, String> {
    Optional<Client> findByCnpj(String cnpj);

    List<Client> findByNameCorporateReason(String nameCorporateReason);
}
