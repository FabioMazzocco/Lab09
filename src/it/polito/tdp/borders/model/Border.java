package it.polito.tdp.borders.model;

import org.jgrapht.graph.DefaultEdge;

public class Border{
	
	Country a;
	Country b;
	int year;
	
	public Border(Country a, Country b, int year) {
		super();
		this.a = a;
		this.b = b;
		this.year = year;
	}
	
	public Border(int a, int b, int year) {
		this.a = new Country(a, null, null);
		this.b = new Country(b, null, null);
		this.year = year;
	}

	@Override
	public String toString() {
		return "\nConfine: tra "+a.getCcode()+" e "+b.getCcode()+" - "+year;
	}

	public Country getA() {
		return a;
	}

	public Country getB() {
		return b;
	}

	public int getYear() {
		return year;
	}
	
	
	
}
