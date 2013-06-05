package de.shop.util;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;

import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.artikelverwaltung.domain.ArtikelFarbeType;
import de.shop.bestellverwaltung.domain.Bestellposition;
import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.domain.Adresse;
import de.shop.kundenverwaltung.domain.Firmenkunde;
import de.shop.kundenverwaltung.domain.HobbyType;
import de.shop.kundenverwaltung.domain.Privatkunde;

/**
 * Emulation der Datenbankzugriffsschicht
 */
public final class Mock {
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

	private static final int MAX_ID = 100;
	private static final int MAX_KUNDEN = 9;
	private static final int MAX_BESTELLUNGEN = 5;
	private static final int JAHR = 2002;
	private static final int MONAT = 0; // bei Calendar werden die Monate von 0 bis 11 gezaehlt
	private static final int TAG = 31;  // bei Calendar die Monatstage ab 1 gezaehlt
	private static final int MAX_ARTIKEL = 13;
	private static final int TEST_WERT = 3;

	public static AbstractKunde findKundeById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final AbstractKunde kunde = id % 2 == 1 ? new Privatkunde() : new Firmenkunde();
		kunde.setId(id);
		kunde.setNachname("Nachname");
		kunde.setEmail("" + id + "@hska.de");
		final GregorianCalendar seitCal = new GregorianCalendar(JAHR, MONAT, TAG);
		final Date seit = seitCal.getTime();
		kunde.setSeit(seit);
		
		final Adresse adresse = new Adresse();
		adresse.setId(id + 1);        // andere ID fuer die Adresse
		adresse.setPlz("12345");
		adresse.setOrt("Testort");
		adresse.setKunde(kunde);
		kunde.setAdresse(adresse);
		
		if (kunde.getClass().equals(Privatkunde.class)) {
			final Privatkunde privatkunde = (Privatkunde) kunde;
			final Set<HobbyType> hobbies = new HashSet<>();
			hobbies.add(HobbyType.LESEN);
			hobbies.add(HobbyType.REISEN);
			privatkunde.setHobbies(hobbies);
		}
		
		return kunde;
	}

	public static List<AbstractKunde> findAllKunden() {
		final int anzahl = MAX_KUNDEN;
		final List<AbstractKunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractKunde kunde = findKundeById(Long.valueOf(i));
			kunden.add(kunde);			
		}
		return kunden;
	}

	public static List<AbstractKunde> findKundenByNachname(String nachname) {
		final int anzahl = nachname.length();
		final List<AbstractKunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractKunde kunde = findKundeById(Long.valueOf(i));
			kunde.setNachname(nachname);
			kunden.add(kunde);			
		}
		return kunden;
	}
	
	public static AbstractKunde findKundeByEmail(String email) {
		if (email.startsWith("x") || email.length() < 0) {
			return null;
		}
		
		final AbstractKunde kunde = email.length() % 2 == 1 ? new Privatkunde() : new Firmenkunde();
		//kunde.setId(Long.valueOf(email.length()));
		kunde.setId(Long.valueOf(1));
		kunde.setNachname("Nachname");
		kunde.setEmail(email);
		final GregorianCalendar seitCal = new GregorianCalendar(JAHR, MONAT, TAG);
		final Date seit = seitCal.getTime();
		kunde.setSeit(seit);
		
		final Adresse adresse = new Adresse();
		adresse.setId(kunde.getId() + 1);        // andere ID fuer die Adresse
		adresse.setPlz("12345");
		adresse.setOrt("Testort");
		adresse.setKunde(kunde);
		kunde.setAdresse(adresse);
		
		if (kunde.getClass().equals(Privatkunde.class)) {
			final Privatkunde privatkunde = (Privatkunde) kunde;
			final Set<HobbyType> hobbies = new HashSet<>();
			hobbies.add(HobbyType.LESEN);
			hobbies.add(HobbyType.REISEN);
			privatkunde.setHobbies(hobbies);
		}
		
		//return kunde;
		return null;
	}
	
	public static List<Bestellung> findBestellungenByKundeId(Long kundeId) {
		final AbstractKunde kunde = findKundeById(kundeId);
		
		// Beziehungsgeflecht zwischen Kunde und Bestellungen aufbauen
		final int anzahl = kundeId.intValue() % MAX_BESTELLUNGEN + 1;  // 1, 2, 3 oder 4 Bestellungen
		final List<Bestellung> bestellungen = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Bestellung bestellung = findBestellungById(Long.valueOf(i));
			bestellung.setKunde(kunde);
			bestellungen.add(bestellung);			
		}
		kunde.setBestellungen(bestellungen);
		
		return bestellungen;
	}

	public static Bestellung findBestellungById(Long id) {
		if (id > MAX_ID) {
			return null;
		}

		final AbstractKunde kunde = findKundeById(id + 1);  // andere ID fuer den Kunden

		final Bestellung bestellung = new Bestellung();
		bestellung.setId(id);
		bestellung.setAusgeliefert(false);
		bestellung.setKunde(kunde);
		final List<Bestellposition> bestellpositionen = new ArrayList<>();
		
		for (int i = 0; i <= TEST_WERT; i++) {
			final Bestellposition bestellposition = new Bestellposition();
			bestellposition.setAnzahl(Long.valueOf(i + 2));
			bestellposition.setArtikel(findArtikelById(Long.valueOf(i + TEST_WERT)));
			bestellposition.setPositionId(Long.valueOf(i + 1));
			bestellpositionen.add(bestellposition);
		}
	
		bestellung.setBestellpositionen(bestellpositionen);
		
		return bestellung;
	}

	public static AbstractKunde createKunde(AbstractKunde kunde) {
		// Neue IDs fuer Kunde und zugehoerige Adresse
		// Ein neuer Kunde hat auch keine Bestellungen
		final String nachname = kunde.getNachname();
		kunde.setId(Long.valueOf(nachname.length()));
		final Adresse adresse = kunde.getAdresse();
		adresse.setId((Long.valueOf(nachname.length())) + 1);
		adresse.setKunde(kunde);
		kunde.setBestellungen(null);
		
		LOGGER.infof("Neuer Kunde: %s", kunde);
		return kunde;
	}

	public static void updateKunde(AbstractKunde kunde) {
		LOGGER.infof("Aktualisierter Kunde: %s", kunde);
	}

	public static void deleteKunde(AbstractKunde kunde) {
		LOGGER.infof("Geloeschter Kunde: %s", kunde);
	}

	public static Bestellung createBestellung(Bestellung bestellung, 
			AbstractKunde kunde, List<Bestellposition> bestellpositionen) {
		LOGGER.infof("Neue Bestellung: %s fuer Kunde %s mit Bestellpositionen %s angelegt", 
				bestellung, kunde, bestellpositionen);
		final String nachname = kunde.getNachname();
		bestellung.setId(Long.valueOf(nachname.length()));
		return bestellung;
	}

	public static Artikel findArtikelById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final Artikel artikel = new Artikel();
		final String bezeichnung;
				
		if (id % TEST_WERT == 2) {
					bezeichnung = "Wohnzimmerschrank";
				}
				else if (id % TEST_WERT == 1) {
					bezeichnung = "Ledercouch";
				}
				else {
					bezeichnung = "Wohnzimmertisch";
				}
			
		artikel.setId(id);
		artikel.setArtikelBezeichnung("" + bezeichnung);
		artikel.setVerfuegbarkeit(true);
		artikel.setPreis(new BigDecimal(id));
		final Set<ArtikelFarbeType> farben = new HashSet<>();
		farben.add(ArtikelFarbeType.BLAU);
		farben.add(ArtikelFarbeType.SCHWARZ);
		farben.add(ArtikelFarbeType.WEISS);
		artikel.setFarbe(farben);
		
		return artikel;
	}
	
	public static List<Artikel> findArtikelByBezeichnung(String bezeichnung) {
		final int anzahl = bezeichnung.length();
		final List<Artikel> artikelliste = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Artikel artikel = findArtikelById(Long.valueOf(i));
			artikel.setArtikelBezeichnung(bezeichnung);
			artikelliste.add(artikel);			
		}
		return artikelliste;
	}
	
	public static List<Artikel> findAllArtikel() {
		final int anzahl = MAX_ARTIKEL;
		final List<Artikel> artikelliste = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Artikel artikel = findArtikelById(Long.valueOf(i));
			artikelliste.add(artikel);			
		}
		return artikelliste;
	}

	public static Bestellposition findBestellpositionById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		
		final Artikel artikel = findArtikelById(id); 
		final Bestellposition bestellposition = new Bestellposition();
		bestellposition.setAnzahl(id + 2);
		bestellposition.setArtikel(artikel);
		bestellposition.setPositionId(id);
		
		return bestellposition;
	}
	
	public static Bestellposition createBestellposition(Bestellposition bestellposition, Bestellung bestellung) {
		LOGGER.infof("Neue Bestellposition: %s fuer Bestellung: %s", bestellposition, bestellung);
		return bestellposition;
	}
	
	public static Artikel createArtikel(Artikel artikel) {
		LOGGER.infof("Neuer Artikel: %s hinzugefuegt", artikel);
		return artikel;
	}
	
	public static void updateBestellposition(Bestellposition bestellposition) {
		LOGGER.infof("Aktualisierte Bestellposition: %s", bestellposition);
	}
	
	public static void updateBestellung(Bestellung bestellung) {
		LOGGER.infof("Aktualisierter Kunde: %s" + bestellung);
	}
	
	public static Artikel updateArtikel(Artikel artikel) {
		LOGGER.infof("Aktualisierter Artikel: %s", artikel);
		return artikel;
	}
	
	public static void deleteBestellposition(Bestellposition bestellposition) {
		LOGGER.infof("Geloeschte Bestellposition: %s", bestellposition);
	}
	
	public static void deleteArtikel(Artikel artikel) {
		LOGGER.infof("Geloeschter Artikel: %s", artikel);
	}

	private Mock() { /**/ }
}
