package com.es2.designpatterns.cargo;

import static org.junit.jupiter.api.Assertions.*;
import com.es2.designpatterns.cargo.Medicamento;
import org.junit.jupiter.api.Test;

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

        Medicamento medicamento1 = new Medicamento();
        medicamento1.setName("Aspirina");
        medicamento1.setSize(1);
        medicamento1.setQuantity(10);
        medicamento1.setUnitValue(5);
    }
}