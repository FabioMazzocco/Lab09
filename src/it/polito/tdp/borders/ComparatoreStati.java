package it.polito.tdp.borders;

import java.util.Comparator;

public class ComparatoreStati implements Comparator<String> {

	@Override
	public int compare(String first, String second) {
		int result = first.compareTo(second);
		return result;
	}

}
