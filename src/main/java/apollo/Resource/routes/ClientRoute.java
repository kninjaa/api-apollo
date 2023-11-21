package apollo.Resource.routes;

import apollo.Controller.bo.validation;
import apollo.Model.beans.Account;
import apollo.Model.beans.Address;
import apollo.Model.beans.Client;
import apollo.Model.beans.Orders;
import apollo.Model.beans.op.EstablishmentType;
import apollo.Model.repository.Interface.*;
import apollo.Model.repository.Record.Request.RrequestClient;
import apollo.Model.repository.Record.Response.RresponseClient;
import jakarta.persistence.EntityNotFoundException;
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
    private Iaccount iaccount;

    @Autowired
    private Iaddress iaddress;

    @Autowired
    private IiestablishmentType iestablishmentType;

    @Autowired
    private Iorders iorders;

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
        } else throw new EntityNotFoundException();
    }

    @Transactional
    @GetMapping("/idClient/{id}")
    public ResponseEntity GetClientId(@PathVariable int id) {
        Optional<Client> optionalClientId = iclient.findById(String.valueOf(id));
        if (optionalClientId.isPresent()) {
            Client client = optionalClientId.get();
            return ResponseEntity.ok(client);
        } else throw new EntityNotFoundException();
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
            if (existingNameCorp.stream().anyMatch(client -> client.getNameCorporateReason().equals(nameCorporateReason))) errorMessage += "Razão Corporativa, ";
            if (existingCnpj.stream().anyMatch(client -> client.getCnpj().equals(cnpj))) errorMessage += "cnpj, ";

            errorMessage = errorMessage.substring(0, errorMessage.length() - 2);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        EstablishmentType establishmentType = iestablishmentType.findByEstablishmentType(data.establishmentType());
        if(establishmentType != null){
            if(data.cnpj().length() == 14){
                Client newClient = new Client(data);
                newClient.setEstablishmentType(establishmentType);
                iclient.save(newClient);
                return ResponseEntity.ok("Registrado com sucesso!");
            }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cnpj invalido.");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo do estabelecimento não especificado.");
    }

    @Transactional
    @PutMapping("/{cnpj}")
    public ResponseEntity UpdataClient(@RequestBody @Valid RresponseClient upData){
        Optional<Client> OptionalClient = iclient.findById(String.valueOf(upData.id()));
        if (OptionalClient.isPresent()) {
            Client UpClient = OptionalClient.get();
            if (UpClient.isSituation()){
                UpClient.UpClient(upData);
                return ResponseEntity.ok(UpClient);
            }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente inativo.");
        } else throw new EntityNotFoundException();
    }

    @Transactional
    @DeleteMapping("/cnpj/{cnpj}")
    public ResponseEntity DelClientCnpj(@PathVariable String cnpj){
        Optional<Client> optionalClient = iclient.findByCnpj(cnpj);
        if (optionalClient.isPresent()){
            Date currentDate = new Date();
            Client client = optionalClient.get();
            Account clientAccount = client.getAccount();

            clientAccount.setDateEnd(currentDate);
            client.setSituation(false);
            iclient.save(client);

            return ResponseEntity.ok("Conta desativada!");
        }else throw new EntityNotFoundException();
    }

    @Transactional
    @DeleteMapping("/id/{id}")
    public ResponseEntity DelClient(@PathVariable int id){
        Optional<Client> optionalClient = iclient.findById(String.valueOf(id));
        if (optionalClient.isPresent()){
            Client client = optionalClient.get();
            if (!client.isSituation()){
                Account clientAccount = client.getAccount();
                List<Orders> clientOrders = client.getOrders();
                List<Address> clientAddress = client.getAddresses();
                validation validation = new validation();
                boolean isInactive = validation.isInactiveAfterOneYear(clientAccount);

                if (isInactive) {
                    iorders.deleteAll(clientOrders);
                    iaddress.deleteAll(clientAddress);
                    iaccount.delete(clientAccount);
                    iclient.deleteById(String.valueOf(client.getId()));

                    return ResponseEntity.ok("Excluido com sucesso!");
                } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Para a exclusão do cliente, a conta precisa estar inativa há mais de 1 ano.");
            }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Para a exclusão do cliente, o plano precisa esta inativo.");
        }else throw new EntityNotFoundException();
    }
}