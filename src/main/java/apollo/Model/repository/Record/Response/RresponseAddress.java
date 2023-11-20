package apollo.Model.repository.Record.Response;

import apollo.Model.beans.Address;
import jakarta.validation.constraints.*;

public record RresponseAddress(
        @NotNull int id,
        @NotNull int client,
        String street,
        int number,
        String complement,
        String city,
        String cep
) {
    public RresponseAddress(Address address){
        this(
                address.getId(),
                address.getClient().getId(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getCity(),
                address.getCep()
        );
    }
}
