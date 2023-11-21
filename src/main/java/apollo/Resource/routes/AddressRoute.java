package apollo.Resource.routes;

import apollo.Model.beans.Address;
import apollo.Model.beans.Client;
import apollo.Model.repository.Interface.Iaddress;
import apollo.Model.repository.Interface.Iclient;
import apollo.Model.repository.Record.Request.RresquestAddress;
import apollo.Model.repository.Record.Response.RresponseAddress;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/address")
public class AddressRoute {
    @Autowired
    private Iaddress iaddress;

    @Autowired
    private Iclient iclient;

    @Transactional
    @GetMapping
    public ResponseEntity GetAddress() {
        var address = iaddress.findAll();
        return ResponseEntity.ok(address);
    }
    @Transactional
    @GetMapping("/{client}")
    public ResponseEntity GetAddressClient(@PathVariable int client) {
        Optional<Address> optionalAddress = iaddress.findById(String.valueOf(client));
        if (!optionalAddress.isPresent()) throw new EntityNotFoundException();
        Address addressClient =  optionalAddress.get();
        return ResponseEntity.ok(addressClient);
    }

    @Transactional
    @PostMapping
    public ResponseEntity RegisterAddress(@RequestBody @Valid RresquestAddress data){
        Optional<Client> optionalClient = iclient.findById(String.valueOf(data.client()));
        if (optionalClient.isPresent()){
            Client client = optionalClient.get();
            if (!client.isSituation()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conta inativa.");
            Address newAddress = new Address(data, client);
            iaddress.save(newAddress);

            return ResponseEntity.ok("Endereço cadastrado.");
        }else throw new EntityNotFoundException();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity UpdataAddress(@RequestBody @Valid RresponseAddress upData){
        Optional<Address> optionalAddress = iaddress.findById(String.valueOf(upData.id()));
        if (!optionalAddress.isPresent()) throw new EntityNotFoundException();

        Address upAddress = optionalAddress.get();
        Optional<Client> optionalClient = iclient.findById(String.valueOf(upAddress.getClient().getId()));
        if (!optionalClient.isPresent()) throw new EntityNotFoundException();

        Client upAddressClient = optionalClient.get();
        if (!upAddressClient.isSituation())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Para alteração de endereços, o plano do cliente precisa esta inativo.");

        upAddress.UpAddress(upData);

        return ResponseEntity.ok("Atualizado com sucesso!");
    }

}
