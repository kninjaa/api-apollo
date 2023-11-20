package apollo.Model.repository.Interface;

import apollo.Model.beans.Address;
import apollo.Model.beans.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Iaddress extends JpaRepository<Address, String> {
}
