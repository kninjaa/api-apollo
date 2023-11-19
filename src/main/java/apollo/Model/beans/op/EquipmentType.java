package apollo.Model.beans.op;

import apollo.Model.beans.Equipment;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBEQUIPMENTTYPE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EquipmentType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EQT_INT_ID")
    private int id;

    @Column(name = "EQT_STR_EQUIPMENTTYPE")
    private String equipmentType;

    @OneToOne(mappedBy = "equipmentType", fetch = FetchType.EAGER)
    private Equipment equipment;
}
