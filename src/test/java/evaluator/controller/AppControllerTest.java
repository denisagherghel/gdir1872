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
    public void addNewIntrebare_validInput() {
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
    public void addNewIntrebare_invalidFormatForEnunt() {
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
    public void addNewIntrebare_invalidFormatForVarianta1() {
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
    public void addNewIntrebare_invalidStringForVariantaCorecta() {
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
    public void addNewIntrebare_invalidDomeniu() {
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
    public void addNewIntrebare_BVA_minAcceptedValueForVarianta1() {
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
    public void addNewIntrebare_BVA_minNotAcceptedValueForVarianta1() {
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
    public void addNewIntrebare_BVA_minPlusOneAcceptedValueForVarianta1() {
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
    public void addNewIntrebare_tooLongEnunt() {
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
    public void addNewIntrebare_tooLongVarianta1() {
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

    @Test
    public void createNewTest() {
    }
}