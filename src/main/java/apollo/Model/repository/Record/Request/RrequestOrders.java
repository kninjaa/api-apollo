package apollo.Model.repository.Record.Request;

import jakarta.validation.constraints.*;
import java.util.Date;

public record RrequestOrders(
        @NotBlank String request,
        @NotNull Date requestDate,
        @NotNull int client,
        @NotNull int equipment,
        @NotNull int address,
        @NotBlank String shipping

) { }