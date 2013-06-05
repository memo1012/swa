package de.shop.artikelverwaltung.domain;

import static de.shop.util.Constants.MIN_ID;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.shop.util.IdGroup;

public class Artikel implements Serializable  {
	
	private static final long serialVersionUID = 161835922543423714L;
	
	public static final int ARTIKELBEZEICHNUNG_LENGTH_MIN = 2;
	public static final int ARTIKELBEZEICHNUNG_LENGTH_MAX = 32;
	
	@Min(value = MIN_ID, message = "{artikelverwaltung.artikel.id.min}", groups = IdGroup.class)
	private Long id;
	
	@NotNull(message = "{artikelverwaltung.artikel.artikelbezeichnung.notNull}")
	@Size(min = ARTIKELBEZEICHNUNG_LENGTH_MIN, max = ARTIKELBEZEICHNUNG_LENGTH_MAX,
	      message = "{artikelverwaltung.artikel.artikelbezeichnung.length}")
	private String artikelBezeichnung;
	
	@NotNull(message = "{artikelverwaltung.artikel.preis.notNull}")
	@DecimalMin(value = "0.0", message = "{artikelverwaltung.artikel.preis.min}")
	private BigDecimal preis;
	
	@NotNull(message = "{artikelverwaltung.artikel.farbe.notNull}")
	private Set<ArtikelFarbeType> farbe;
	
	@NotNull(message = "{artikelverwaltung.artikel.verfuegbarkeit.notNull}")
	private boolean verfuegbarkeit;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getArtikelBezeichnung() {
		return artikelBezeichnung;
	}
	public void setArtikelBezeichnung(String artikelBezeichnung) {
		this.artikelBezeichnung = artikelBezeichnung;
	}
	public BigDecimal getPreis() {
		return preis;
	}
	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}
	public Set<ArtikelFarbeType> getFarbe() {
		return farbe;
	}
	public void setFarbe(Set<ArtikelFarbeType> farbe) {
		this.farbe = farbe;
	}
	public boolean getVerfuegbarkeit() {
		return verfuegbarkeit;
	}
	public void setVerfuegbarkeit(boolean verfuegbarkeit) {
		this.verfuegbarkeit = verfuegbarkeit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		final Artikel other = (Artikel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Artikel [id=" + id + ", artikelBezeichnung="
				+ artikelBezeichnung + ", preis=" + preis + ", farbe=" + farbe
				+ ", verfuegbarkeit=" + verfuegbarkeit + "]";
	}
	//Testatkk
	
}