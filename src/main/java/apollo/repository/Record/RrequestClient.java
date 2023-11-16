package apollo.repository.Record;

import jakarta.validation.constraints.*;

public record RrequestClient(
        @NotBlank String nameCorporateReason,
        @NotBlank String fantasyName,
        @NotBlank String cnpj,
        @NotBlank String contact,
        @NotNull Float rating,
        @NotBlank String establishmentType
) { }