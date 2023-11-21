package apollo.Resource.routes;


import apollo.Model.beans.*;
import apollo.Model.repository.Interface.Iaddress;
import apollo.Model.repository.Interface.Iclient;
import apollo.Model.repository.Interface.Iequipment;
import apollo.Model.repository.Interface.Iorders;
import apollo.Model.repository.Record.Request.RrequestOrders;
import apollo.Model.repository.Record.Response.RresponseOrders;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrdersRoute {
    @Autowired
    private Iorders iorders;

    @Autowired
    private Iclient iclient;

    @Autowired
    private Iequipment iequipment;

    @Autowired
    private Iaddress iaddress;
    private Equipment equipment;
    private Address address;

    @Transactional
    @GetMapping
    public ResponseEntity GetOrders() {
        var equipment = iorders.findAll();
        return ResponseEntity.ok(equipment);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity GetOrdersId(@PathVariable int id) {
        Optional<Orders> optionalOrders = iorders.findById(String.valueOf(id));
        if (!optionalOrders.isPresent()) throw new EntityNotFoundException();

        Orders orders = optionalOrders.get();
        return ResponseEntity.ok(orders);
    }

    @Transactional
    @PostMapping
    public ResponseEntity RegisterOrders(@RequestBody @Valid RrequestOrders data){
        Optional<Client> optionalClient = iclient.findById(String.valueOf(data.client()));
        if (!optionalClient.isPresent()) throw new EntityNotFoundException();
        Client client = optionalClient.get();
        if (!client.isSituation()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não esta com o plano ativo.");

        Optional<Equipment> optionalEquipment = iequipment.findById(String.valueOf(data.equipment()));
        if(!optionalEquipment.isPresent()) throw new EntityNotFoundException();
        Equipment equipment = optionalEquipment.get();

        Optional<Address> optionalAddress = iaddress.findById(String.valueOf(data.address()));
        if(!optionalAddress.isPresent()) throw new EntityNotFoundException();
        Address address = optionalAddress.get();

        Orders newOrders = new Orders(data, client, equipment, address);
        newOrders.setEquipment(equipment);
        newOrders.setAddress(address);
        iorders.save(newOrders);

        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity UpdataAccount(@RequestBody @Valid RresponseOrders upData){
        Optional<Orders> optionalOrders = iorders.findById(String.valueOf(upData.id()));
        if (!optionalOrders.isPresent()) throw new EntityNotFoundException();

        if(upData.equipment() != 0){
            Optional<Equipment> optionalEquipment = iequipment.findById(String.valueOf(upData.equipment()));
            equipment = optionalEquipment.get();
        }
        if(upData.address() != 0){
            Optional<Address> optionalAddress = iaddress.findById(String.valueOf(upData.address()));
            address = optionalAddress.get();
        }

        Orders upOrders = optionalOrders.get();
        upOrders.setShipping(upData.shipping());
        upOrders.UpOrders(upData, equipment, address);

        return ResponseEntity.ok(upOrders);
    }
}
