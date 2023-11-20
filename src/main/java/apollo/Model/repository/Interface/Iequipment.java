package apollo.Model.repository.Interface;

import apollo.Model.beans.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Iequipment extends JpaRepository <Equipment, String> {
}
