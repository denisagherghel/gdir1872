package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.main.StartUI;
import evaluator.model.Domeniu;
import evaluator.model.Intrebare;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class BigBangIntegrationTest {
    StartUI startUI = new StartUI();

    AppControllerTest moduleA = new AppControllerTest();
    CreateNewTestTest moduleB = new CreateNewTestTest();
    GetStatisticaTest moduleC = new GetStatisticaTest();

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
    public void unitTestB_createNewTest() {
        moduleB.createNewTest_validInput1();
        moduleB.createNewTest_validInput2();
        moduleB.createNewTest_notEnoughDomenii();
        moduleB.createNewTest_notEnoughIntrebari();
    }

    @Test
    public void unitTestC_getStatistica() {
        moduleC.getStatisticaInalid();
        moduleC.getStatisticaValid();
    }

    @Test
    public void integrationTest_BigBang() {
        String[] args = null;
        try {
            InputStream original = System.in;
            FileInputStream fips = new FileInputStream(new File("integration_tc_bigbang.txt"));
            System.setIn(fips);
            StartUI.main(args);

            assertTrue("Created question for invalid input",StartUI.newIntrebare == null);
            assertTrue("Invalid error message",
                    StartUI.intrebareException.getMessage().compareTo("Intrebarea deja exista!") == 0);

            assertTrue("Error creating test for valid input",StartUI.newTest != null);

            assertTrue("Invalid value for AI statistics",
                    StartUI.statistica.getNumarIntrebariDomenii().get(Domeniu.OOP) == 7);


            System.setIn(original);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void BigBang() {
        unitTestA_addNewIntrebare();
        unitTestB_createNewTest();
        unitTestC_getStatistica();
        integrationTest_BigBang();
    }
}