package evaluator.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import evaluator.exception.InputValidationFailedException;
import evaluator.model.Domeniu;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.model.Test;
import evaluator.repository.Repository;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;

public class AppController {
	
	private Repository repository;
	
	public AppController() {
		repository = new Repository();
	}
	
	public Intrebare addNewIntrebare(String enunt, String varianta1, String varianta2, String varianta3,
									 String variantaCorecta, String domeniu) throws DuplicateIntrebareException, InputValidationFailedException {
		Intrebare intrebare = new Intrebare(enunt, varianta1, varianta2, varianta3, variantaCorecta, domeniu);
		repository.addIntrebare(intrebare);

		return intrebare;
	}
	
	public boolean exists(Intrebare intrebare){
		return repository.exists(intrebare);
	}
	
	public Test createNewTest() throws NotAbleToCreateTestException{
		
		if(repository.getIntrebari().size() < 5)
			throw new NotAbleToCreateTestException("Nu exista suficiente intrebari pentru crearea unui test!(5)");
		
		if(repository.getNumberOfDistinctDomains() < 5)
			throw new NotAbleToCreateTestException("Nu exista suficiente domenii cu intrebari pentru crearea unui test!(5)");
		
		List<Intrebare> testIntrebari = new LinkedList<Intrebare>();
		List<Domeniu> domenii = new LinkedList<Domeniu>();
		Intrebare intrebare;
		Test test = new Test();
		
		while(testIntrebari.size() != 5){
			intrebare = repository.pickRandomIntrebare();
			
			if(!domenii.contains(intrebare.getDomeniu())){
				testIntrebari.add(intrebare);
				domenii.add(intrebare.getDomeniu());
			}
			
		}
		
		test.setIntrebari(testIntrebari);
		test.setId(System.currentTimeMillis());

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("test-"+test.getId()));
			bw.append(test.getId().toString()+"\n");
			for(int nrIntrebare=0; nrIntrebare<5; nrIntrebare++) {
				Intrebare intrebareDeSalvat = testIntrebari.get(nrIntrebare);
				bw.append(intrebareDeSalvat.getEnunt() + "\n");
				bw.append(intrebareDeSalvat.getVarianta1() + "\n");
				bw.append(intrebareDeSalvat.getVarianta2() + "\n");
				bw.append(intrebareDeSalvat.getVarianta3() + "\n");
				bw.append(intrebareDeSalvat.getVariantaCorecta() + "\n");
				bw.append(intrebareDeSalvat.getDomeniu().toString() + "\n");
				bw.append("##" + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				bw.close();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}

		return test;
		
	}
	
	public void loadIntrebariFromFile(String f) throws InputValidationFailedException {
		repository.setIntrebari(repository.loadIntrebariFromFile(f));
	}
	
	public Statistica getStatistica() throws NotAbleToCreateStatisticsException{
		
		if(repository.getIntrebari().isEmpty())
			throw new NotAbleToCreateStatisticsException("Repository-ul nu contine nicio intrebare!");
		
		Statistica statistica = new Statistica();
		for(Domeniu domeniu : repository.getDistinctDomains()){
			statistica.add(domeniu, repository.getNumberOfIntrebariByDomain(domeniu));
		}
		
		return statistica;
	}

}
