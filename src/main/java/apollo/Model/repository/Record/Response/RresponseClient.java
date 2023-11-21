package apollo.Model.repository.Record.Response;

import apollo.Model.beans.Client;
import jakarta.validation.constraints.NotNull;

public record RresponseClient(
        int id,
        @NotNull boolean situation,
        String fantasyName,
        String contact,
        Float rating
) {
    public RresponseClient(Client client){
        this(
                client.getId(),
                client.isSituation(),
                client.getFantasyName(),
                client.getContact(),
                client.getRating()
        );
    }
}