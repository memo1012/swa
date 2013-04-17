package de.shop.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



//Anfang Code JP
import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.artikelverwaltung.domain.Kategorie;
//Ende Code JP

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.domain.Adresse;
import de.shop.kundenverwaltung.domain.Firmenkunde;
import de.shop.kundenverwaltung.domain.HobbyType;
import de.shop.kundenverwaltung.domain.Privatkunde;





/**
 * Emulation des Anwendungskerns
 */
public final class Mock {
	private static final int MAX_ID = 99;
	private static final int MAX_KUNDEN = 8;
	private static final int MAX_BESTELLUNGEN = 4;
	private static final long TEST_ID = 50;
	
	//JP
	private static final int MAX_ARTIKELN = 8;

	//Erstellt einen neue Kunde
	public static AbstractKunde findKundeById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final AbstractKunde kunde = id % 2 == 1 ? new Privatkunde() : new Firmenkunde();
		kunde.setId(id);
		kunde.setNachname("Nachname" + id);
		kunde.setEmail("" + id + "@hska.de");
		
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

	public static Collection<AbstractKunde> findAllKunden() {
		final int anzahl = MAX_KUNDEN;
		final Collection<AbstractKunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractKunde kunde = findKundeById(Long.valueOf(i));
			kunden.add(kunde);			
		}
		return kunden;
	}

	public static Collection<AbstractKunde> findKundenByNachname(String nachname) {
		final int anzahl = nachname.length();
		final Collection<AbstractKunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractKunde kunde = findKundeById(Long.valueOf(i));
			kunde.setNachname(nachname);
			kunden.add(kunde);			
		}
		return kunden;
	}
	
	
	public static Collection<Artikel> findAllArtikeln() {
		final int anzahl = MAX_ARTIKELN;
		final Collection<Artikel> artikeln = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Artikel artikel = findArtikelById(Long.valueOf(i));
			artikeln.add(artikel);			
		}
		return artikeln;
	}
	
	
	//Anfang JP
	public static Artikel findArtikelById(Long artikelid){
		if (artikelid> MAX_ARTIKELN)
			return null;	
		
		
		final Artikel artikel = new Artikel();
		artikel.setId(artikelid);
		artikel.setBezeichnung("blabla Artikel Beschreibung");
		artikel.setBreite(12);
		artikel.setHoehe(67);
		artikel.setGewicht(3.2);		
		artikel.setLaenge(22);
		artikel.setPreis(23.7);
		artikel.setVerfuegbarkeit(true);
		
		//Noch nicht verlangt
		final Kategorie kategorie = new Kategorie();
		kategorie.setId(1);
		kategorie.setBeschreibung("Holz Moebeln");
		
		
		artikel.setKategorie(kategorie);
		
		return artikel;
		
		
	}
	
	public static Artikel createArtikel(Artikel artikel) {

		//final int breite = artikel.getBreite();
		//inal int leange = artikel.getLaenge();

		final Kategorie kategorie = artikel.getKategorie();
		kategorie.setId(TEST_ID);
		kategorie.setBeschreibung("Neue Beschreibung fur neue Artikel");

		artikel.setKategorie(kategorie);

		artikel.setId(TEST_ID);

		System.out.println("Neuer artikel: " + artikel);

		return artikel;


		}
	
	public static Collection<Artikel> findArtikelnByBestellungId (Long bestellungid)
	{
		final Bestellung bestellung = findBestellungById(bestellungid);
		
		final int anzahl = bestellungid.intValue() % MAX_ARTIKELN + 1;  // 1, 2, 3 oder 4 Bestellungen
		final List<Artikel> artikeln = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Artikel artikel = findArtikelById(Long.valueOf(i));
			//bestellung.setKunde(kunde);
			artikeln.add(artikel);			
		}
		bestellung.setArtikeln(artikeln);
		
		return artikeln;
		
	}
	
	//Ende Code JP
	
	public static Bestellung createBestellung(Bestellung bestellung) {
		
		bestellung.setId(TEST_ID);
		bestellung.setAusgeliefert(false);

		System.out.println("Neue Bestellung: " + bestellung);
		return bestellung;
	}
	
	public static void updateBestellung(Bestellung bestellung) {
		// TODO Auto-generated method stub
		System.out.println("Aktualisierter Bestellung: " + bestellung);
		}
	
	//Macht ein Liste von fake bestellungen , und korigiert , sagt dass es zum richtigen Kunden gehort
	public static Collection<Bestellung> findBestellungenByKundeId(Long kundeId) {
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

	//Anfang JP
	//WTF ? keine echte Beziehungen ? Wir bekommen eine List von Artikeln mit
	public static Bestellung findBestellungById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		//Random ?
				
		final AbstractKunde kunde = findKundeById(id + 1);  // andere ID fuer den Kunden

		final Bestellung bestellung = new Bestellung();
		bestellung.setId(id);
		bestellung.setAusgeliefert(false);
		bestellung.setKunde(kunde);
				
		//Random Generation
		int lower = 1;
		int higher = 5;
		int randomartikelanzahl = (int)(Math.random() * (higher - lower)) + lower;
		//Ende Random
		final List<Artikel> artikeln = new ArrayList<>(randomartikelanzahl);
		
		for (int i=1; i<randomartikelanzahl;i++){
			
			final Artikel artikel = findArtikelById(Long.valueOf(i));
			artikeln.add(artikel);
		}
		
		return bestellung;
	}
	
	//Ende JP

	//Wahrscheinlich gibt es schon eine name oder kunde ?
	//ES fuhlt hier nur die infos
	public static AbstractKunde createKunde(AbstractKunde kunde) {
		// Neue IDs fuer Kunde und zugehoerige Adresse
		// Ein neuer Kunde hat auch keine Bestellungen
		final String nachname = kunde.getNachname();
		kunde.setId(Long.valueOf(nachname.length()));
		final Adresse adresse = kunde.getAdresse();
		adresse.setId((Long.valueOf(nachname.length())) + 1);
		adresse.setKunde(kunde);
		kunde.setBestellungen(null);
		
		System.out.println("Neuer Kunde: " + kunde);
		return kunde;
	}

	public static void updateKunde(AbstractKunde kunde) {
		System.out.println("Aktualisierter Kunde: " + kunde);
	}

	public static void deleteKunde(Long kundeId) {
		System.out.println("Kunde mit ID=" + kundeId + " geloescht");
	}
	
	public static void updateArtikel(Artikel artikel) {
		// TODO Auto-generated method stub
		System.out.println("Aktualisierter artikel: " + artikel);
		
	}

	private Mock() { /**/ }


	

	
}
