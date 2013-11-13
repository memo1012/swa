package de.shop.bestellverwaltung.domain;

import static de.shop.util.Constants.ERSTE_VERSION;
import static de.shop.util.Constants.KEINE_ID;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Index;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.logging.Logger;

import de.shop.kundenverwaltung.domain.Kunde;

@Entity
@Table(name = "bestellung", indexes = { @Index(columnList = "kunde_fk")})
@NamedQueries({
		@NamedQuery(name = Bestellung.FIND_BESTELLUNGEN_BY_KUNDEID, query = "SELECT b"
				+ " FROM   Bestellung b"
				+ " WHERE  b.kunde = :"
				+ Bestellung.PARAM_KUNDEID),
		@NamedQuery(name = Bestellung.FIND_BESTELLUNG_BY_ID_FETCH_LIEFERUNGEN, query = "SELECT DISTINCT b"
				+ " FROM   Bestellung b LEFT JOIN FETCH b.lieferungen"
				+ " WHERE  b.id = :" + Bestellung.PARAM_ID),
		@NamedQuery(name = Bestellung.FIND_BESTELLUNGEN_BY_KUNDEID_FETCH_LIEFERUNGEN, query = "SELECT DISTINCT b"
				+ " FROM   Bestellung b LEFT JOIN FETCH b.lieferungen"
				+ " WHERE  b.kunde.id = :" + Bestellung.PARAM_KUNDEID),
		@NamedQuery(name = Bestellung.FIND_KUNDE_BY_ID, query = "SELECT b.kunde"
				+ " FROM   Bestellung b"
				+ " WHERE  b.id = :"
				+ Bestellung.PARAM_ID) })
@Cacheable
@XmlRootElement
public class Bestellung implements Serializable {
	private static final long serialVersionUID = 7560752199018702446L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles
			.lookup().lookupClass());

	private static final String PREFIX = "Bestellung.";
	public static final String FIND_BESTELLUNGEN_BY_KUNDEID = PREFIX
			+ "findBestellungenByKundeId";
	public static final String FIND_BESTELLUNG_BY_ID_FETCH_LIEFERUNGEN = PREFIX
			+ "findBestellungenByIdFetchLieferungen";
	public static final String FIND_KUNDE_BY_ID = PREFIX
			+ "findBestellungKundeById";

	public static final String FIND_BESTELLUNGEN_BY_KUNDEID_FETCH_LIEFERUNGEN = PREFIX
			+ "findBestellungenByKundeIdFetchLieferungen";
	public static final String PARAM_KUNDEID = "kundeId";
	public static final String PARAM_ID = "id";

	@Id
	@GeneratedValue
	@Column(nullable = false, updatable = false)
	private Long id = KEINE_ID;

	@ManyToOne
	@JoinColumn(name = "kunde_fk", nullable = false, insertable = false, updatable = false)
	// @NotNull(message = "{bestellverwaltung.bestellung.kunde.notNull}", groups
	// = PreExistingGroup.class)
	@XmlTransient
	private Kunde kunde;

	@Transient
	private URI kundeUri;

	@Version
	@Basic(optional = false)
	private int version = ERSTE_VERSION;

	@OneToMany(fetch = EAGER, cascade = { PERSIST, REMOVE })
	@JoinColumn(name = "bestellung_fk", nullable = false)
	@OrderColumn(name = "idx", nullable = false)
	@NotEmpty(message = "{bestellverwaltung.bestellung.bestellpositionen.notEmpty}")
	@Valid
	private List<Bestellposition> bestellpositionen;

	@ManyToMany
	@JoinTable(name = "bestellung_lieferung", joinColumns = @JoinColumn(name = "bestellung_fk"), inverseJoinColumns = @JoinColumn(name = "lieferung_fk"))
	@XmlTransient
	private Set<Lieferung> lieferungen;

	@Basic(optional = false)
	@Temporal(TIMESTAMP)
	@XmlElement(name = "datum")
	private Date erzeugt;

	@Basic(optional = false)
	@Temporal(TIMESTAMP)
	@XmlTransient
	private Date aktualisiert;

	public Bestellung() {
		super();
	}

	public Bestellung(Kunde kunde, List<Bestellposition> bestellpositionen) {
		super();
		this.kunde = kunde;
		this.bestellpositionen = bestellpositionen;
	}

	@PrePersist
	private void prePersist() {
		erzeugt = new Date();
		aktualisiert = new Date();
	}

	@PostPersist
	private void postPersist() {
		LOGGER.debugf("Neue Bestellung mit ID=%d", id);
	}

	@PreUpdate
	private void preUpdate() {
		aktualisiert = new Date();
	}

	@PostUpdate
	private void postUpdate() {
		LOGGER.debugf("Bestellung mit ID=%d aktualisiert: version=%d", id,
				version);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Bestellposition> getBestellpositionen() {
		if (bestellpositionen == null) {
			return null;
		}

		return Collections.unmodifiableList(bestellpositionen);
	}

	public void setBestellpositionen(List<Bestellposition> bestellpositionen) {
		if (this.bestellpositionen == null) {
			this.bestellpositionen = bestellpositionen;
			return;
		}

		// Wiederverwendung der vorhandenen Collection
		this.bestellpositionen.clear();
		if (bestellpositionen != null) {
			this.bestellpositionen.addAll(bestellpositionen);
		}
	}

	public Bestellung addBestellposition(Bestellposition bestellposition) {
		if (bestellpositionen == null) {
			bestellpositionen = new ArrayList<>();
		}
		bestellpositionen.add(bestellposition);
		return this;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public URI getKundeUri() {
		return kundeUri;
	}

	public void setKundeUri(URI kundeUri) {
		this.kundeUri = kundeUri;
	}

	public Set<Lieferung> getLieferungen() {
		return lieferungen == null ? null : Collections
				.unmodifiableSet(lieferungen);
	}

	public void setLieferungen(Set<Lieferung> lieferungen) {
		if (this.lieferungen == null) {
			this.lieferungen = lieferungen;
			return;
		}

		// Wiederverwendung der vorhandenen Collection
		this.lieferungen.clear();
		if (lieferungen != null) {
			this.lieferungen.addAll(lieferungen);
		}
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void addLieferung(Lieferung lieferung) {
		if (lieferungen == null) {
			lieferungen = new HashSet<>();
		}
		lieferungen.add(lieferung);
	}

	@XmlTransient
	public List<Lieferung> getLieferungenAsList() {
		return lieferungen == null ? null : new ArrayList<>(lieferungen);
	}

	public void setLieferungenAsList(List<Lieferung> lieferungen) {
		this.lieferungen = lieferungen == null ? null : new HashSet<>(
				lieferungen);
	}

	public Date getErzeugt() {
		return erzeugt == null ? null : (Date) erzeugt.clone();
	}

	public void setErzeugt(Date erzeugt) {
		this.erzeugt = erzeugt == null ? null : (Date) erzeugt.clone();
	}

	public String getErzeugt(String format) {
		final Format formatter = new SimpleDateFormat(format,
				Locale.getDefault());
		return formatter.format(erzeugt);
	}

	public Date getAktualisiert() {
		return aktualisiert == null ? null : (Date) aktualisiert.clone();
	}

	public void setAktualisiert(Date aktualisiert) {
		this.aktualisiert = aktualisiert == null ? null : (Date) aktualisiert
				.clone();
	}

	@Override
	public String toString() {
		return "Bestellung [id=" + id + ", version=" + version + ", kundeUri="
				+ kundeUri + ", erzeugt=" + erzeugt + ", aktualisiert="
				+ aktualisiert + ']';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((bestellpositionen == null) ? 0 : bestellpositionen
						.hashCode());
		result = prime * result + version;
		result = prime * result + ((erzeugt == null) ? 0 : erzeugt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Bestellung other = (Bestellung) obj;
		if (bestellpositionen == null) {
			if (other.bestellpositionen != null) {
				return false;
			}
		} else if (!bestellpositionen.equals(other.bestellpositionen)) {
			return false;
		}
		if (version != other.version) {
			return false;
		}
		if (erzeugt == null) {
			if (other.erzeugt != null) {
				return false;
			}
		} else if (!erzeugt.equals(other.erzeugt)) {
			return false;
		}
		return true;
	}
}
