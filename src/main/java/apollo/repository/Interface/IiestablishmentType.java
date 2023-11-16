package apollo.repository.Interface;

import apollo.beans.Account;
import apollo.beans.Client;
import apollo.beans.op.EstablishmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IiestablishmentType  extends JpaRepository<EstablishmentType, String> {
    EstablishmentType findByEstablishmentType(String establishmentType);
}
