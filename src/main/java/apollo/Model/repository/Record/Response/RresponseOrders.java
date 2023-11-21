package apollo.Model.repository.Record.Response;

import apollo.Model.beans.Orders;
import jakarta.validation.constraints.*;

import java.util.Date;

public record RresponseOrders (
        @NotNull int id,
        int equipment,
        int address,
        String shipping
){
    public RresponseOrders(Orders orders){
        this(
                orders.getId(),
                orders.getEquipment().getId(),
                orders.getAddress().getId(),
                orders.getShipping()
        );
    }
}
