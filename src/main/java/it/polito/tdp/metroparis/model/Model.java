package it.polito.tdp.metroparis.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.metroparis.db.MetroDAO;

public class Model {
	
	//defaultedge perchè grafo non pesato
	private Graph<Fermata, DefaultEdge> graph; 
	private List<Fermata> fermate; 
	private Map<Integer,Fermata> fermateIdMap;
	
	public Model() {
		this.graph= new SimpleDirectedGraph<>(DefaultEdge.class);
		
		MetroDAO dao= new MetroDAO();
		
		//CREAZIONE DEI VERTICI
		
		this.fermate= dao.getAllFermate();
		this.fermateIdMap = new HashMap<>(); 
		
		for(Fermata f: fermate) {
			fermateIdMap.put(f.getIdFermata(), f);
		}
		
		//per aggiungere le fermate al grafo faccio un ciclo sui vertici oppure:
		
		Graphs.addAllVertices(this.graph, this.fermate); 
		
		//System.out.println(this.graph);
		
		//CREAZIONE DEGLI ARCHI - metodo 1 
		/*
		for (Fermata p: this.fermate) {
			for(Fermata a : this.fermate) {
				if(dao.fermateConnesse(p, a))
					this.graph.addEdge(a, p);
			}
		}
		*/
		//CREAZINE DEGLI ARCHI - METODO 2 (da un vertice, trova tutti i connessi)
		
		/*for(Fermata p: this.fermate) {
			//List<Fermata> connesse = tutte le fermate adiacenti a p
			List<Fermata> connesse = dao.fermateSuccessive(p, fermateIdMap);
			for(Fermata a: connesse) {
				this.graph.addEdge(p, a);
			}
		    
		}
		
		//System.out.println(this.graph);
		
	     */
	
		//CREAZIONE ARCHI - metodo 3- il db mi da gli archi
		List<CoppieFermate> coppie = dao.coppieFermate(this.fermateIdMap); 
		
		for(CoppieFermate c: coppie) {
			this.graph.addEdge(c.getP(), c.getA());
		}
	    
		System.out.format("Grafo caricato con %d vertici %d archi", this.graph.vertexSet().size(), this.graph.edgeSet().size());
	
	
	}

	public static void main(String args[]) {
		Model m = new Model();
	}
}
