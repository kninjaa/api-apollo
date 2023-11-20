package apollo.Model.repository.Interface;

import apollo.Model.beans.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Iorders extends JpaRepository<Orders, String> {
}
