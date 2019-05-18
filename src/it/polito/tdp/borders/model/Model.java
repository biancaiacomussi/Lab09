package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	Graph<Country, DefaultEdge> grafo;
	Map<Integer, Country> idMap;
	List<Border> confini;
	BordersDAO dao;
	Map<Country, Country> backvisit;
	List<Country> visitati;

	public Model() {
		
		idMap = new HashMap<>();
		dao = new BordersDAO();
		dao.loadAllCountries(idMap);
		visitati = new LinkedList<>();
		
	
	}
	
	public void creaGrafo(int anno) {
		
		confini = new ArrayList<>(dao.getCountryPairs(idMap, anno));
		grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		
		for(Border b : confini) {
			//aggiungo vertici e archi insieme a partire da una lista di archi
		Graphs.addEdgeWithVertices(grafo, b.getNazione1(), b.getNazione2());
		
		}
		
	}
	
	public String elencoVertici() {
		String res = "";
		for(Country c : grafo.vertexSet()) {
			res += c.getNome() +" "+ grafo.degreeOf(c)+"\n";
		}
		
		return res;
	}
	
	public int componentiConnesse() {
		int num=0;
		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<>(grafo);
		List<Set<Country>> connessi = ci.connectedSets();
		
		for(Set<Country> s : connessi) {
			num++;
		}
		return num;
	}

	public Set<Country> trovaVicini(Country nazione) {
		
		
		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<>(grafo);
		List<Set<Country>> connessi = ci.connectedSets();
		Set<Country> set = new HashSet<>();
		
		if(grafo.vertexSet().contains(nazione)) {
		
		for(Set<Country> s : connessi) {
			if(s.contains(nazione)) {
				set.addAll(s);
				set.remove(nazione);
			}
		}
		}
		else return null;
		return set;
	}
	
	
	
	public List<Country> trovaVicini2(Country nazione){
		List<Country> daVisitare = new ArrayList<>();
		List<Country> visitati = new ArrayList<>();
		
		daVisitare.add(nazione);
		
		while(!daVisitare.isEmpty()) {
			for(Country c : daVisitare) {
				daVisitare = Graphs.neighborListOf(grafo, nazione);
				nazione = c;
			}
		}
		
		for(Country c : Graphs.neighborListOf(grafo, nazione)) {
			if(!visitati.contains(c)) {
				daVisitare.add(c);
			}
		}
		
		return visitati;
		
	}
	
	public List<Country> trovaVicini3(Country nazione) {
		
		for(Country c : Graphs.neighborListOf(grafo, nazione)) {
			if(!visitati.contains(c)) {
				visitati.add(c);
				trovaVicini3(c);
			}
		}
		
		return visitati;
	}

	public Graph<Country, DefaultEdge> getGrafo() {
		return grafo;
	}

	public void setGrafo(Graph<Country, DefaultEdge> grafo) {
		this.grafo = grafo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
