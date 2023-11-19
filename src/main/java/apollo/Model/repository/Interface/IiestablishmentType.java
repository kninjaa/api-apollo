package apollo.Model.repository.Interface;

import apollo.Model.beans.op.EstablishmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IiestablishmentType  extends JpaRepository<EstablishmentType, String> {
    EstablishmentType findByEstablishmentType(String establishmentType);
}
