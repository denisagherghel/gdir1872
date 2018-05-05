package evaluator.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;

import evaluator.controller.AppController;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.model.Test;

//functionalitati
//i.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//ii.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//iii.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartUI {
	private static final String file = "intrebari.txt";
	private static String optiune;
	private static boolean activ;

	public static Intrebare newIntrebare;
	public static Exception intrebareException;
    public static Test newTest;
    public static Exception testException;
    public static Statistica statistica;
    public static Exception statisticaException;

    public static void setActiv(boolean activ) {
        StartUI.activ = activ;
    }

    public static void setOptiune(String optiune) {
        StartUI.optiune = optiune;
    }

    public static void main(String[] args) throws IOException {
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		AppController appController = new AppController();
		
		activ = true;
		optiune = null;

		try {
			appController.loadIntrebariFromFile(file);
		} catch (InputValidationFailedException e) {
			System.out.println(e.getMessage());
		}

		while(activ){
			System.out.println("");
			System.out.println("1.Adauga intrebare");
			System.out.println("2.Creeaza test");
			System.out.println("3.Statistica");
			System.out.println("4.Exit");
			System.out.println("");
			
			optiune = console.readLine();
			
			switch(optiune){
			case "1" :
				System.out.print("enunt:");
				String enunt = console.readLine();
				System.out.print("varianta1:");
				String varianta1 = console.readLine();
				System.out.print("varianta2:");
				String varianta2 = console.readLine();
				System.out.print("varianta3:");
				String varianta3 = console.readLine();
				System.out.print("variantaCorecta:");
				String variantaCorecta = console.readLine();
				System.out.print("domeniul:");
				String domeniul = console.readLine();

				try {
                    intrebareException = null;
				    newIntrebare = null;
					newIntrebare = appController.addNewIntrebare(enunt, varianta1, varianta2, varianta3, variantaCorecta, domeniul);
				} catch (DuplicateIntrebareException e) {
					System.out.println(e.getMessage());
					intrebareException = e;
				} catch (InputValidationFailedException e) {
					System.out.println(e.getMessage());
					intrebareException = e;
				}
				break;

			case "2" :
				try {
				    testException = null;
				    newTest = null;
					newTest = appController.createNewTest();
				} catch (NotAbleToCreateTestException e) {
					System.out.println(e.getMessage());
					testException = e;
				}
				break;
			case "3" :
				try {
				    statisticaException = null;
				    statistica = null;
					statistica = appController.getStatistica();
					System.out.println(statistica);
				} catch (NotAbleToCreateStatisticsException e) {
				    statisticaException = e;
					System.out.println(e.getMessage());
				}
				
				break;
			case "4" :
				activ = false;
				break;
			default:
				break;
			}
		}
		
	}

}
