package evaluator.repository;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import evaluator.exception.InputValidationFailedException;
import evaluator.model.Domeniu;
import evaluator.model.Intrebare;
import evaluator.exception.DuplicateIntrebareException;

public class Repository {
	
	private List<Intrebare> intrebari;
	private String fileName;
	
	public Repository() {
		setIntrebari(new LinkedList<Intrebare>());
	}
	
	public void addIntrebare(Intrebare i) throws DuplicateIntrebareException{
		if(exists(i))
			throw new DuplicateIntrebareException("Intrebarea deja exista!");
		intrebari.add(i);

		if(fileName!=null) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(fileName, true));
                bw.append(i.getEnunt() + "\n");
                bw.append(i.getVarianta1() + "\n");
                bw.append(i.getVarianta2() + "\n");
                bw.append(i.getVarianta3() + "\n");
                bw.append(i.getVariantaCorecta() + "\n");
                bw.append(i.getDomeniu().toString() + "\n");
                bw.append("##" + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bw.close();
                } catch (IOException e) {
                    // TODO: handle exception
                }
            }
        }
	}
	
	public boolean exists(Intrebare i){
		for(Intrebare intrebare : intrebari)
			if(intrebare.equals(i))
				return true;
		return false;
	}
	
	public Intrebare pickRandomIntrebare(){
		Random random = new Random();
		return intrebari.get(random.nextInt(intrebari.size()));
	}

	public int getNumberOfDistinctDomains(){
		return getDistinctDomains().size();

	}

	public Set<Domeniu> getDistinctDomains(){
		Set<Domeniu> domains = new TreeSet<Domeniu>();
		for(Intrebare intrebre : intrebari)
			domains.add(intrebre.getDomeniu());
		return domains;
	}
	
	public List<Intrebare> getIntrebariByDomain(Domeniu domain){
		List<Intrebare> intrebariByDomain = new LinkedList<Intrebare>();
		for(Intrebare intrebare : intrebari){
			if(intrebare.getDomeniu().equals(domain)){
				intrebariByDomain.add(intrebare);
			}
		}
		
		return intrebariByDomain;
	}
	
	public int getNumberOfIntrebariByDomain(Domeniu domain){
		return getIntrebariByDomain(domain).size();
	}
	
	public List<Intrebare> loadIntrebariFromFile(String f) throws InputValidationFailedException {
		fileName = f;

		List<Intrebare> intrebari = new LinkedList<Intrebare>();
		BufferedReader br = null; 
		String line = null;
		List<String> intrebareAux;
		Intrebare intrebare;
		
		try{
			br = new BufferedReader(new FileReader(f));
			line = br.readLine();
			while(line != null){
				intrebareAux = new LinkedList<String>();
				while(!line.equals("##")){
					intrebareAux.add(line);
					line = br.readLine();
				}
				intrebare = new Intrebare();
				intrebare.setEnunt(intrebareAux.get(0));
				intrebare.setVarianta1(intrebareAux.get(1));
				intrebare.setVarianta2(intrebareAux.get(2));
				intrebare.setVarianta3(intrebareAux.get(3));
				intrebare.setVariantaCorecta(intrebareAux.get(4));
				intrebare.setDomeniu(intrebareAux.get(5));
				intrebari.add(intrebare);
				line = br.readLine();
			}
		
		}
		catch (IOException e) {
			// TODO: handle exception
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
		
		return intrebari;
	}
	
	public List<Intrebare> getIntrebari() {
		return intrebari;
	}

	public void setIntrebari(List<Intrebare> intrebari) {
		this.intrebari = intrebari;
	}
	
}
