package de.shop.artikelverwaltung.domain;

public class Kategorie {
	//private static final long serialVersionUID = -3029272617931844501L;
	private Object id;
	private String beschreibung;
	
	public Object getId() {
		return id;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	
	
	//Sollen die �ffentlicht sein ?
	public void setId(Object id) {
		this.id = id;
	}	
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	
	//Hashcode and Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((beschreibung == null) ? 0 : beschreibung.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Kategorie other = (Kategorie) obj;
		if (beschreibung == null) {
			if (other.beschreibung != null)
				return false;
		} else if (!beschreibung.equals(other.beschreibung))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	//ToString Methode
	@Override
	public String toString() {
		return "Kategorie [id=" + id + ", beschreibung=" + beschreibung + "]";
	}
	
	
	//Die anderen klassen haben kein Konstruktor ?

	
	
	

}
