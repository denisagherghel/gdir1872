package evaluator.controller;

import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Domeniu;
import evaluator.model.Statistica;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GetStatisticaTest {
    AppController appController = new AppController();

    @Test
    public void getStatisticaValid() {
        try {
            appController.loadIntrebariFromFile("intrebari_tcs_01.txt");
        } catch (InputValidationFailedException e) {
            System.out.println(e.getMessage());
        }

        try {
            Statistica statistica = null;
            statistica = appController.getStatistica();
            Map<Domeniu, Integer> result = statistica.getNumarIntrebariDomenii();
            assertTrue("Invalid statistics!", result.get(Domeniu.AI)==1);
            assertTrue("Invalid statistics!", result.get(Domeniu.OOP)==2);
            assertTrue("Invalid statistics!", result.get(Domeniu.BazeDeDate)==1);
            assertTrue("Invalid statistics!", result.get(Domeniu.ProgramareWeb) == 0);
            assertTrue("Invalid statistics!", result.get(Domeniu.SistemeDeOperare)==0);
        } catch (NotAbleToCreateStatisticsException e) {
            fail("Got NotAbleToCreateStatisticsException for correct input!");
        }
    }

    @Test
    public void getStatisticaInalid() {
        try {
            appController.loadIntrebariFromFile("intrebari_tcs_02.txt");
        } catch (InputValidationFailedException e) {
            System.out.println(e.getMessage());
        }

        try {
            Statistica statistica = null;
            statistica = appController.getStatistica();
            assertTrue("Method created statistics for invalid data!", statistica == null);
        } catch (NotAbleToCreateStatisticsException e) {
            assertTrue("Got incorrect error message",
                    e.getMessage().compareTo("Repository-ul nu contine nicio intrebare!") == 0);
        }
    }
}
