package it.polito.tdp.borders.model;

public class Border {
	
	private Country nazione1;
	private Country nazione2;
	
	public Border(Country nazione1, Country nazione2) {
		super();
		this.nazione1 = nazione1;
		this.nazione2 = nazione2;
	}
	
	
	public Country getNazione1() {
		return nazione1;
	}


	public void setNazione1(Country nazione1) {
		this.nazione1 = nazione1;
	}


	public Country getNazione2() {
		return nazione2;
	}


	public void setNazione2(Country nazione2) {
		this.nazione2 = nazione2;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nazione1 == null) ? 0 : nazione1.hashCode());
		result = prime * result + ((nazione2 == null) ? 0 : nazione2.hashCode());
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
		Border other = (Border) obj;
		if (nazione1 == null) {
			if (other.nazione1 != null)
				return false;
		} else if (!nazione1.equals(other.nazione1))
			return false;
		if (nazione2 == null) {
			if (other.nazione2 != null)
				return false;
		} else if (!nazione2.equals(other.nazione2))
			return false;
		return true;
	}
	
	

}
