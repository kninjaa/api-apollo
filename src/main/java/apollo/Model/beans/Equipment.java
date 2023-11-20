package apollo.Model.beans;

import apollo.Model.beans.op.EquipmentType;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "TBEQUIPMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "orders"})
public class Equipment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EQ_INT_ID")
    private int id;

    @Column(name = "EQ_STR_NAME")
    private String name;

    @OneToOne @JoinColumn(name = "EQT_STR_EQUIPMENTTYPE", referencedColumnName = "EQT_STR_EQUIPMENTTYPE")
    private EquipmentType equipmentType;

    @OneToMany(mappedBy = "equipment", fetch = FetchType.EAGER)
    private List<Orders> orders;
}
