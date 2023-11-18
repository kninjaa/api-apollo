package apollo.route;

import apollo.beans.Account.Account;
import apollo.beans.Client;
import apollo.repository.Interface.Iaccount;
import apollo.repository.Interface.Iclient;
import apollo.repository.Record.RrequestAccount;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/account")
public class AccountRoute {
    @Autowired
    private Iaccount iaccount;

    @Autowired
    private Iclient iclient;

    @Transactional
    @GetMapping("/{client}")
    public ResponseEntity GetAccount(@PathVariable int client) {
        Optional<Client> optionalClient = iclient.findById(String.valueOf(client));
        if (optionalClient.isPresent()) {
            Client clientId = optionalClient.get();
            Optional<Account> optionalAccount = iaccount.findByClient(clientId);
            if (optionalAccount.isPresent()) {
                Account account = optionalAccount.get();
                return ResponseEntity.ok(account);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conta não encontrada.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado.");
        }
    }

    /*@Transactional
    @PostMapping
    public ResponseEntity RegisterAccount(@RequestBody @Valid RrequestAccount data){
        String login = data.login();
        String email = data.emailCorp();
        List<Account> existingLogin = iaccount.findByLogin(login);
        List<Account> existingEmail = iaccount.findByEmailCorp(email);

        if (!existingLogin.isEmpty() || !existingEmail.isEmpty()){
            String errorMessage = "Já existe ";
            if (existingLogin.stream().anyMatch(client -> client.getLogin().equals(login))) {
                errorMessage += "login registrado";
            } else if (existingEmail.stream().anyMatch(client -> client.getEmailCorp().equals(email))) {
                errorMessage += "email registrado";
            }
            errorMessage = errorMessage.substring(0, errorMessage.length() - 2);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        Optional<Client> optionalClient = iclient.findById(String.valueOf(data.client()));
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            if (client.isSituation()){
                Account newAccount = new Account(data, client);
                iaccount.save(newAccount);

                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não esta com o plano ativo.");
            }
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado.");
        }
    }*/
}
