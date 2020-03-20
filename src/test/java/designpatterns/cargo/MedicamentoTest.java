package designpatterns.cargo;

import com.es2.designpatterns.cargo.Medicamento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedicamentoTest {

    @Test
    void testCreateMedicamento() {

        Medicamento medicamento1 = new Medicamento();
        medicamento1.setName("Aspirina");
        medicamento1.setSize(1);
        medicamento1.setQuantity(10);
        medicamento1.setUnitValue(5);
    }

    @Test
    void testgetMedicineValue() {

        Medicamento medicamento2 = new Medicamento();
        medicamento2.setName("Aspirina");
        medicamento2.setSize(1);
        medicamento2.setQuantity(10);
        medicamento2.setUnitValue(5);
        assertEquals(medicamento2.getMedicineValue(), 50.0);
    }


}