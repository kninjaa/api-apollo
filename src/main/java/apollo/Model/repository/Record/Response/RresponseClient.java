package apollo.Model.repository.Record.Response;

import apollo.Model.beans.Client;
import jakarta.validation.constraints.NotNull;

public record RresponseClient(
        int id,
        @NotNull boolean situation,
        String nameCorporateReason,
        String fantasyName,
        String cnpj,
        String contact,
        Float rating
) {
    public RresponseClient(Client client){
        this(
                client.getId(),
                client.isSituation(),
                client.getNameCorporateReason(),
                client.getFantasyName(),
                client.getCnpj(),
                client.getContact(),
                client.getRating()
        );
    }
}