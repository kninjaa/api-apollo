package apollo.repository.Record;

import jakarta.validation.constraints.*;
import java.util.Date;

public record RrequestAccount (
        @NotBlank String login,
        @NotBlank String emailCorp,
        @NotBlank String password,
        @NotNull Date dateStart,
        @NotNull int client
){}