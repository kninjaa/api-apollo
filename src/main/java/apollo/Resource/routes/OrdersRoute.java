package apollo.Resource.routes;


import apollo.Model.beans.Account;
import apollo.Model.beans.Client;
import apollo.Model.beans.Equipment;
import apollo.Model.beans.Orders;
import apollo.Model.repository.Interface.Iclient;
import apollo.Model.repository.Interface.Iequipment;
import apollo.Model.repository.Interface.Iorders;
import apollo.Model.repository.Record.Request.RrequestOrders;
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

    @Transactional
    @GetMapping
    public ResponseEntity GetOrders() {
        List<Orders> equipmentList = iorders.findAll();
        if (equipmentList == null || equipmentList.isEmpty()) throw new EntityNotFoundException();

        return ResponseEntity.ok(equipmentList);
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

        Orders newOrders = new Orders(data, client);
        newOrders.setEquipment(equipment);
        iorders.save(newOrders);

        return ResponseEntity.ok().build();
    }
}
