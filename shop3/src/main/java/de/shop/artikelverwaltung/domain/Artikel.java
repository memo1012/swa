package de.shop.artikelverwaltung.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Artikel implements Serializable {
	private static final long serialVersionUID = 1472129607838538329L;
	
	// TODO Bean Validation
	private Long id;
	
	// TODO Bean Validation
	private String bezeichnung;
	
	private Kategorie kategorie;
	private int laenge; //in mm
	private int breite;
	private int hoehe;
	private double gewicht; //in KG
	private BigDecimal preis;
	private boolean verfuegbarkeit;
//	private int ka;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
		
	public Kategorie getKategorie() {
		return kategorie;
	}
	public void setKategorie(Kategorie kategorie) {
		this.kategorie = kategorie;
	}
		
	public int getLaenge() {
		return laenge;
	}
	public void setLaenge(int laenge) {
		this.laenge = laenge;
	}
	public int getBreite() {
		return breite;
	}
	public void setBreite(int breite) {
		this.breite = breite;
	}
	public int getHoehe() {
		return hoehe;
	}
	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}
	public double getGewicht() {
		return gewicht;
	}
	public void setGewicht(double gewicht) {
		this.gewicht = gewicht;
	}
	public BigDecimal getPreis() {
		return preis;
	}
	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}
	public boolean isVerfuegbarkeit() {
		return verfuegbarkeit;
	}
	public void setVerfuegbarkeit(boolean verfuegbarkeit) {
		this.verfuegbarkeit = verfuegbarkeit;
	}
	
	//Nicht auf Preis und verfugbarkeit
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
		result = prime * result + breite;
		long temp;
		temp = Double.doubleToLongBits(gewicht);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + hoehe;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((kategorie == null) ? 0 : kategorie.hashCode());
		result = prime * result + laenge;
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
		final Artikel other = (Artikel) obj;
		if (bezeichnung == null) {
			if (other.bezeichnung != null)
				return false;
		} 
		else if (!bezeichnung.equals(other.bezeichnung))
			return false;
		if (breite != other.breite)
			return false;
		if (Double.doubleToLongBits(gewicht) != Double
				.doubleToLongBits(other.gewicht))
			return false;
		if (hoehe != other.hoehe)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} 
		else if (!id.equals(other.id))
			return false;
		if (kategorie == null) {
			if (other.kategorie != null)
				return false;
		} 
		else if (!kategorie.equals(other.kategorie))
			return false;
			
		if (laenge != other.laenge)
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Artikel [id=" + id + ", bezeichnung=" + bezeichnung
				+ ", laenge=" + laenge
				+ ", breite=" + breite + ", hoehe=" + hoehe + ", gewicht="
				+ gewicht + ", preis=" + preis + ", verfuegbarkeit="
				+ verfuegbarkeit + ",Kategorie=" + kategorie + "]";
	}
	
}
	
	