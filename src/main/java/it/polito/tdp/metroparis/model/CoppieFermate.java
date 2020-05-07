package it.polito.tdp.metroparis.model;

public class CoppieFermate {

	private Fermata p; 
	private Fermata a;
	
	
	public CoppieFermate(Fermata p, Fermata a) {
	
		this.p = p;
		this.a = a;
	}
	public Fermata getP() {
		return p;
	}
	public void setP(Fermata p) {
		this.p = p;
	}
	public Fermata getA() {
		return a;
	}
	public void setA(Fermata a) {
		this.a = a;
	} 
	
	
}
