package apollo.repository.Interface;

import apollo.beans.op.EstablishmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IiestablishmentType  extends JpaRepository<EstablishmentType, String> {
    EstablishmentType findByEstablishmentType(String establishmentType);
}
