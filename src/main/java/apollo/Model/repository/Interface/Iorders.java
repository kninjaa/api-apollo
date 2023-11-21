package apollo.Model.repository.Interface;

import apollo.Model.beans.Client;
import apollo.Model.beans.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Iorders extends JpaRepository<Orders, String> {
}
