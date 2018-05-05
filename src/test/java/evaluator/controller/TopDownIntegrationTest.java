package evaluator.controller;

import evaluator.main.StartUI;
import evaluator.model.Domeniu;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class TopDownIntegrationTest {
    AppControllerTest moduleA = new AppControllerTest();

    @Before
    public void setUp() {

    }

    @Test
    public void unitTestA_addNewIntrebare() {
        moduleA.setUp();
        moduleA.addNewIntrebare_validInput();
        moduleA.setUp();
        moduleA.addNewIntrebare_invalidDomeniu();
        moduleA.setUp();
        moduleA.addNewIntrebare_invalidFormatForEnunt();
        moduleA.setUp();
        moduleA.addNewIntrebare_invalidFormatForVarianta1();
        moduleA.setUp();
        moduleA.addNewIntrebare_invalidStringForVariantaCorecta();
        moduleA.setUp();
        moduleA.addNewIntrebare_tooLongEnunt();
        moduleA.setUp();
        moduleA.addNewIntrebare_tooLongVarianta1();
    }

    @Test
    public void integrationTestPAB() {
        String[] args = null;
        try {
            InputStream original = System.in;
            FileInputStream fips = new FileInputStream(new File("integration_tc_topdown_PAB.txt"));
            System.setIn(fips);
            StartUI.main(args);

            assertTrue("Created question for invalid input",StartUI.newIntrebare == null);
            assertTrue("Invalid error message",
                    StartUI.intrebareException.getMessage().compareTo("Varianta2 nu incepe cu '2)'!") == 0);

            assertTrue("Error creating test for valid input",StartUI.newTest != null);

            System.setIn(original);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void integrationTestPABC() {
        String[] args = null;
        try {
            InputStream original = System.in;
            FileInputStream fips = new FileInputStream(new File("integration_tc_topdown_PABC.txt"));
            System.setIn(fips);
            StartUI.main(args);

            assertTrue("Created question for invalid input",StartUI.newIntrebare == null);
            assertTrue("Invalid error message",
                    StartUI.intrebareException.getMessage()
                            .compareTo("Varianta corecta nu este unul dintre caracterele {'1', '2', '3'}") == 0);

            assertTrue("Error creating test for valid input",StartUI.newTest != null);

            assertTrue("Invalid value for AI statistics",
                    StartUI.statistica.getNumarIntrebariDomenii().get(Domeniu.OOP) == 7);

            System.setIn(original);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}