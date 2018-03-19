package evaluator.model;

import java.util.HashMap;
import java.util.Map;

public class Statistica {

	private Map<Domeniu, Integer> numarIntrebariDomenii;
	
	public Statistica() {
		numarIntrebariDomenii = new HashMap<Domeniu, Integer>();
	}
	
	public void add(Domeniu key, Integer value){
		numarIntrebariDomenii.put(key, value);
	}

	public Map<Domeniu, Integer> getNumarIntrebariDomenii() {
		return numarIntrebariDomenii;
	}

	public void setNumarIntrebariDomenii(Map<Domeniu, Integer> numarIntrebariDomenii) {
		this.numarIntrebariDomenii = numarIntrebariDomenii;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(Domeniu domeniu : numarIntrebariDomenii.keySet()){
			sb.append(domeniu.toString() + ": " + numarIntrebariDomenii.get(domeniu) + "\n");
		}
		
		return sb.toString();
	}

}
