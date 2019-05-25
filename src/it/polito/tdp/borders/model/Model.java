package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	Graph<Country, DefaultEdge> grafo;
	Map<Country, Integer> mappa;
	Map<Integer, Country> countries;
	BordersDAO dao;
	
	public Model() {
		dao = new BordersDAO();
		countries = new HashMap<Integer, Country>();
		grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		mappa = new HashMap<Country, Integer>();
	}

	public String calcolaRisultato(int anno) {
		//Resetto tutto prima di iniziare
		grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		mappa.clear();
		//Aggiungo i vertici al grafo 
		if(countries.size()==0) {
			//Cerco tutte le nazioni solo se necessario
			for(Country c : dao.loadAllCountries()) {
				countries.put(c.getCcode(), c);
			}
		}
		Graphs.addAllVertices(grafo, countries.values());
		//Aggiungo gli archi
		List<Border> listaArchi = new LinkedList<Border>(dao.getCountryPairs(anno));
		for(Border b : listaArchi) {
			grafo.addEdge(b.getA(), b.getB());
			addBorder(b);
		}
		if(mappa.size()==0)
			return "Nessuno stato trovato";
		String result ="";
		for(Country c : mappa.keySet()) {
			result += c.getStateName()+" - "+mappa.get(c)+"\n";
		}
		return result;
	}
	
	public void addBorder(Border bord) {
		Country a = countries.get(bord.getA().getCcode());
		Country b = countries.get(bord.getB().getCcode());
		if(!mappa.containsKey(a))
			mappa.put(a, 1);
		else
			mappa.put(a, mappa.get(a)+1);
		if(!mappa.containsKey(b))
			mappa.put(b,1);
		else
			mappa.put(b, mappa.get(b)+1);
	}
	
	public Collection<Country> getCountries(){
		return countries.values();
	}

	public int getNumberOfConnectedComponents() {
		int numb = 0;
		for(Integer i : mappa.values()) {
			if(i>0)
				numb++;
		}
		return numb;
	}

}
