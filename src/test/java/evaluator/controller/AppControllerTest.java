package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppControllerTest {
    AppController appController = new AppController();

    String enunt = "Ce este OOP?";
    String varianta1 = "1)dlja";
    String varianta2 = "2)ldlasn";
    String varianta3 = "3)kdnsa";
    String variantaCorecta = "1";
    String domeniu = "OOP";

    @Before
    public void setUp() {
         enunt = "Ce este OOP?";
         varianta1 = "1)dlja";
         varianta2 = "2)ldlasn";
         varianta3 = "3)kdnsa";
         variantaCorecta = "1";
         domeniu = "OOP";
    }

    @Test
    public void addNewIntrebare1() {
        try {
            Intrebare i = null;
            i = appController.addNewIntrebare(enunt, varianta1,varianta2,varianta3,variantaCorecta,domeniu);
            assertTrue("Method did not create question for correct input!", i!=null);
        } catch (DuplicateIntrebareException e) {
            fail("Got DuplicateIntrebareException for correct input!");
        } catch (InputValidationFailedException e) {
            fail("Got InputValidationFailedException for correct input!");
        }
    }

    @Test
    public void addNewIntrebare2() {
        try {
            Intrebare i = null;
            enunt = "Ce este oop";
            i = appController.addNewIntrebare(enunt, varianta1,varianta2,varianta3,variantaCorecta,domeniu);
            assertTrue("Method created question for incorrect input!", i==null);
        } catch (DuplicateIntrebareException e) {
            fail("Got DuplicateIntrebareException for different question input!");
        } catch (InputValidationFailedException e) {
            assertTrue("Got incorrect error message",
                    e.getMessage().compareTo("Ultimul caracter din enunt nu e '?'!") == 0);
        }
    }

    @Test
    public void addNewIntrebare3() {
        try {
            Intrebare i = null;
            varianta1 = "x)dlja";
            i = appController.addNewIntrebare(enunt, varianta1,varianta2,varianta3,variantaCorecta,domeniu);
            assertTrue("Method created question for incorrect input!", i==null);
        } catch (DuplicateIntrebareException e) {
            fail("Got DuplicateIntrebareException for different question input!");
        } catch (InputValidationFailedException e) {
            assertTrue("Got incorrect error message",
                    e.getMessage().compareTo("Varianta1 nu incepe cu '1)'!") == 0);
        }
    }

    @Test
    public void addNewIntrebare4() {
        try {
            Intrebare i = null;
            variantaCorecta = "10";
            i = appController.addNewIntrebare(enunt, varianta1,varianta2,varianta3,variantaCorecta,domeniu);
            assertTrue("Method created question for incorrect input!", i==null);
        } catch (DuplicateIntrebareException e) {
            fail("Got DuplicateIntrebareException for different question input!");
        } catch (InputValidationFailedException e) {
            assertTrue("Got incorrect error message",
                    e.getMessage().compareTo("Varianta corecta nu este unul dintre caracterele {'1', '2', '3'}") == 0);
        }
    }

    @Test
    public void addNewIntrebare5() {
        try {
            Intrebare i = null;
            domeniu = "OOPS";
            i = appController.addNewIntrebare(enunt, varianta1,varianta2,varianta3,variantaCorecta,domeniu);
            assertTrue("Method created question for incorrect input!", i==null);
        } catch (DuplicateIntrebareException e) {
            fail("Got DuplicateIntrebareException for different question input!");
        } catch (InputValidationFailedException e) {
            assertTrue("Got incorrect error message",
                    e.getMessage().compareTo("Nu exista domeniul introdus") == 0);
        }
    }

    @Test
    public void addNewIntrebare6() {
        try {
            Intrebare i = null;
            varianta1 = "1)a";
            i = appController.addNewIntrebare(enunt, varianta1,varianta2,varianta3,variantaCorecta,domeniu);
        } catch (DuplicateIntrebareException e) {
            fail("Got DuplicateIntrebareException for different question input!");
        } catch (InputValidationFailedException e) {
            fail("Got InputValidationFailedException for different question input!");
        }
    }

    @Test
    public void addNewIntrebare7() {
        try {
            Intrebare i = null;
            varianta1 = "1)";
            i = appController.addNewIntrebare(enunt, varianta1,varianta2,varianta3,variantaCorecta,domeniu);
            assertTrue("Method created question for incorrect input!", i==null);
        } catch (DuplicateIntrebareException e) {
            fail("Got DuplicateIntrebareException for different question input!");
        } catch (InputValidationFailedException e) {
            assertTrue("Got incorrect error message",
                    e.getMessage().compareTo("Varianta1 nu are lungimea necesara!") == 0);
        }
    }

    @Test
    public void addNewIntrebare8() {
        try {
            Intrebare i = null;
            varianta1 = "1)ab";
            i = appController.addNewIntrebare(enunt, varianta1,varianta2,varianta3,variantaCorecta,domeniu);
        } catch (DuplicateIntrebareException e) {
            fail("Got DuplicateIntrebareException for different question input!");
        } catch (InputValidationFailedException e) {
            fail("Got InputValidationFailedException for different question input!");
        }
    }

    @Test
    public void addNewIntrebare9() {
        try {
            Intrebare i = null;
            enunt = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ornare, ex tristique tempus fringilla, lorem ipsum amet?";
            i = appController.addNewIntrebare(enunt, varianta1,varianta2,varianta3,variantaCorecta,domeniu);
            assertTrue("Method created question for incorrect input!", i==null);
        } catch (DuplicateIntrebareException e) {
            fail("Got DuplicateIntrebareException for different question input!");
        } catch (InputValidationFailedException e) {
            assertTrue("Got incorrect error message",
                    e.getMessage().compareTo("Lungimea enuntului depaseste 100 de caractere!") == 0);
        }
    }

    @Test
    public void addNewIntrebare10() {
        try {
            Intrebare i = null;
            varianta1 = "1)Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ornare, ex tristique tempus fringilla, lorem ipsum amet?";
            i = appController.addNewIntrebare(enunt, varianta1,varianta2,varianta3,variantaCorecta,domeniu);
            assertTrue("Method created question for incorrect input!", i==null);
        } catch (DuplicateIntrebareException e) {
            fail("Got DuplicateIntrebareException for different question input!");
        } catch (InputValidationFailedException e) {
            assertTrue("Got incorrect error message",
                    e.getMessage().compareTo("Lungimea variantei1 depaseste 50 de caractere!") == 0);
        }
    }
}