package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CreateNewTestTest {
    AppController appController = new AppController();

    @Before
    public void setUp() {

    }

    @Test
    public void createNewTest_notEnoughIntrebari() {
        try {
            appController.loadIntrebariFromFile("intrebari_tc_01.txt");
        } catch (InputValidationFailedException e) {
            System.out.println(e.getMessage());
        }

        try {
            evaluator.model.Test t = null;
            t = appController.createNewTest();
            assertTrue("Method created test for invalid data!", t==null);
        } catch (NotAbleToCreateTestException e) {
            assertTrue("Got incorrect error message",
                    e.getMessage().compareTo("Nu exista suficiente intrebari pentru crearea unui test!(5)") == 0);
        }
    }

    @Test
    public void createNewTest_notEnoughDomenii() {
        try {
            appController.loadIntrebariFromFile("intrebari_tc_02.txt");
        } catch (InputValidationFailedException e) {
            System.out.println(e.getMessage());
        }

        try {
            evaluator.model.Test t = null;
            t = appController.createNewTest();
            assertTrue("Method created test for invalid data!", t==null);
        } catch (NotAbleToCreateTestException e) {
            assertTrue("Got incorrect error message",
                    e.getMessage().compareTo("Nu exista suficiente domenii cu intrebari pentru crearea unui test!(5)") == 0);
        }
    }

    @Test
    public void createNewTest_validInput1() {
        try {
            appController.loadIntrebariFromFile("intrebari_tc_03.txt");
        } catch (InputValidationFailedException e) {
            System.out.println(e.getMessage());
        }

        try {
            evaluator.model.Test t = null;
            t = appController.createNewTest();
            assertTrue("Method did not create test for valid data!", t!=null);
        } catch (NotAbleToCreateTestException e) {
            fail("Got NotAbleToCreateTestException for valid input!");
        }
    }

    @Test
    public void createNewTest_validInput2() {
        try {
            appController.loadIntrebariFromFile("intrebari_tc_04.txt");
        } catch (InputValidationFailedException e) {
            System.out.println(e.getMessage());
        }

        try {
            evaluator.model.Test t = null;
            t = appController.createNewTest();
            assertTrue("Method did not create test for valid data!", t!=null);
        } catch (NotAbleToCreateTestException e) {
            fail("Got NotAbleToCreateTestException for valid input!");
        }
    }
}