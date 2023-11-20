package apollo.Resource.routes;

import apollo.Model.beans.Equipment;
import apollo.Model.repository.Interface.Iequipment;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/equipment")
public class EquipmentRoute {
    @Autowired
    private Iequipment iequipment;

    @Transactional
    @GetMapping
    public ResponseEntity GetEquipment() {
        var equipment = iequipment.findAll();
        return ResponseEntity.ok(equipment);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity GetEquipmentId(@PathVariable int id) {
        Optional<Equipment> optionalEquipment = iequipment.findById(String.valueOf(id));
        if (!optionalEquipment.isPresent()) throw new EntityNotFoundException();

        Equipment equipment = optionalEquipment.get();
        return ResponseEntity.ok(equipment);
    }


}