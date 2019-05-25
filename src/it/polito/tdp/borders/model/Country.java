package it.polito.tdp.borders.model;

public class Country {
	
	int ccode;
	String stateAbb;
	String stateName;
	
	public Country(int ccode, String stateAbb, String stateName) {
		super();
		this.ccode = ccode;
		this.stateAbb = stateAbb;
		this.stateName = stateName;
	}
	
	public int getCcode() {
		return ccode;
	}
	
	public String getStateAbb() {
		return stateAbb;
	}
	
	public String getStateName() {
		return stateName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ccode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (ccode != other.ccode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n"+ccode+" - "+stateAbb+" - "+stateName;
	}
	
	
	
}
