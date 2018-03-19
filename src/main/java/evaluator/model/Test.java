package evaluator.model;

import java.util.LinkedList;
import java.util.List;

public class Test  {
	private Long id;
	private List<Intrebare> intrebari;
	
	public Test() {
		intrebari = new LinkedList<Intrebare>(); 
	}

	public Test(Long id) {
		intrebari = new LinkedList<Intrebare>();
		this.id = id;
	}
	
	public List<Intrebare> getIntrebari() {
		return intrebari;
	}
	
	public void setIntrebari(List<Intrebare> intrebari) {
		this.intrebari = intrebari;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
