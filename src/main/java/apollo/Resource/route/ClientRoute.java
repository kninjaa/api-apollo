package apollo.Resource.route;

import apollo.Model.beans.Client;
import apollo.Model.beans.op.EstablishmentType;
import apollo.Model.repository.Interface.Iclient;
import apollo.Model.repository.Interface.IiestablishmentType;
import apollo.Model.repository.Record.RrequestClient;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/client")
public class ClientRoute {
    @Autowired
    private Iclient iclient;

    @Autowired
    private IiestablishmentType iestablishmentType;

    @Transactional
    @GetMapping
    public ResponseEntity GetClient() {
        var client = iclient.findAll();
        return ResponseEntity.ok(client);
    }

    @Transactional
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity GetClientCnpj(@PathVariable String cnpj) {
        Optional<Client> optionalClientCnpj = iclient.findByCnpj(cnpj);
        if (optionalClientCnpj.isPresent()) {
            Client client = optionalClientCnpj.get();
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cnpj não encontrado.");
        }
    }

    @Transactional
    @GetMapping("/id/{id}")
    public ResponseEntity GetClientId(@PathVariable int id) {
        Optional<Client> optionalClientId = iclient.findById(String.valueOf(id));
        if (optionalClientId.isPresent()) {
            Client client = optionalClientId.get();
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado.");
        }
    }

    @Transactional
    @PostMapping
    public ResponseEntity RegisterClient(@RequestBody @Valid RrequestClient data) {
        String nameCorporateReason = data.nameCorporateReason();
        String cnpj = data.cnpj();

        List<Client> existingNameCorp = iclient.findByNameCorporateReason(nameCorporateReason);
        Optional<Client> existingCnpj = iclient.findByCnpj(cnpj);
        if (!existingNameCorp.isEmpty() || !existingCnpj.isEmpty()) {
            String errorMessage = "Já existe um cliente registrado com : ";
            if (existingNameCorp.stream().anyMatch(client -> client.getNameCorporateReason().equals(nameCorporateReason))) {
                errorMessage += "Razão Corporativa, ";
            }
            if (existingCnpj.stream().anyMatch(client -> client.getCnpj().equals(cnpj))) {
                errorMessage += "cnpj, ";
            }
            errorMessage = errorMessage.substring(0, errorMessage.length() - 2);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        EstablishmentType establishmentType = iestablishmentType.findByEstablishmentType(data.establishmentType());
        if(establishmentType != null){
            Client newClient = new Client(data);
            newClient.setEstablishmentType(establishmentType);
            iclient.save(newClient);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo do estabelecimento não especificado.");
        }
    }
}