package apollo.Model.repository.Record.Response;

import apollo.Model.beans.Account;
import jakarta.validation.constraints.*;

import java.util.Date;

public record RresponseAccount(
        @NotNull int id,
        @NotNull int client,
        String login,
        String emailCorp,
        String password
) {
    public RresponseAccount(Account account){
        this(
                account.getId(),
                account.getClient().getId(),
                account.getLogin(),
                account.getEmailCorp(),
                account.getPassword()
        );
    }
}