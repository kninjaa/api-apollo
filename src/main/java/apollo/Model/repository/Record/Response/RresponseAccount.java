package apollo.Model.repository.Record.Response;

import apollo.Model.beans.Account;
import jakarta.validation.constraints.*;

import java.util.Date;

public record RresponseAccount(
        int id,
        String login,
        String emailCorp,
        String password,
        Date dateStart
) {
    public RresponseAccount(Account account){
        this(
                account.getId(),
                account.getLogin(),
                account.getEmailCorp(),
                account.getPassword(),
                account.getDateStart()
        );
    }
}