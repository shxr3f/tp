package seedu.duke.helper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import seedu.duke.assets.MedicineList;
import seedu.duke.assets.PatientList;
import seedu.duke.assets.DoctorList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Command command = new Command();
    private PatientList patients = new PatientList();
    private DoctorList doctors = new DoctorList();
    private MedicineList medicines = new MedicineList();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void addPatientTest() {
        String userInput = "S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, 2021-02-15";
        command.addPatient(patients,userInput);
        assertEquals("HalpMi: The patient above has been added.\r\n", outContent.toString());
    }

    @Test
    public void viewPatientTest() {
        command.viewPatient(patients,"S1234567A");
        assertEquals("HalpMi: There is no such patient\r\n",outContent.toString());
    }

    @Test
    public void deletePatientTest() {
        String userInput = "S1234567A";
        command.deletePatient(patients,userInput);
        assertEquals("HalpMi: Please input a positive number up to 0 only.\r\n"
                + "        Here is an example:\r\n" + "        delete patient /info 1\r\n", outContent.toString());
    }

    @Test
    public void addDoctorTest() {
        String userInput = "S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, Emergency Medicine";
        command.addDoctor(doctors,userInput);
        assertEquals("HalpMi: The doctor above has been added.\r\n",outContent.toString());
    }

    @Test
    public void deleteDoctorTest() {
        command.deleteDoctor(doctors,"");
        assertEquals("HalpMi: Please input a positive number up to 0 only.\r\n"
                + "        Here is an example:\r\n" + "        delete doctor /info 1\r\n",outContent.toString());
    }

    @Test
    public void addMedicineTest() {
        String userInput = "Paracetamol";
        command.addMedicine(medicines,userInput);
        assertEquals("HalpMi: There is one or more parameters missing\r\n"
                + "HalpMi: Invalid format. Please follow the below example and try again.\r\n"
                + "        add medicine /info Paracetamol,500,2023-12-12,Headaches,10\r\n",outContent.toString());
    }

    @Test
    public void deleteMedicineTest() {
        command.deleteMedicine(medicines,"1");
        assertEquals("HalpMi: Please input a positive number up to 0 only.\r\n"
                + "        Here is an example:\r\n" + "        delete patient /info 1\r\n",outContent.toString());
    }


}