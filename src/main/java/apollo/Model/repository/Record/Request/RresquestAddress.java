package apollo.Model.repository.Record.Request;

import jakarta.validation.constraints.*;

public record RresquestAddress (
        @NotBlank String street,
        @NotNull int number,
        @NotBlank String complement,
        @NotBlank String city,
        @NotBlank String cep,
        @NotNull int client
){ }