package de.shop.bestellverwaltung.service;

import java.util.List;
import java.util.Locale;

import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.bestellverwaltung.domain.Lieferung;
import de.shop.kundenverwaltung.domain.Kunde;

public interface BestellungService {

	Bestellung findBestellungById(Long id);
	Bestellung findBestellungByIdMitLieferungen(Long id);
	Kunde findKundeById(Long id);
	List<Bestellung> findBestellungenByKunde(Kunde kunde);
	Bestellung createBestellung(Bestellung bestellung, Long kundeId, Locale locale);
	Bestellung createBestellung(Bestellung bestellung, Kunde kunde, Locale locale);
	List<Artikel> ladenhueter(int anzahl);
	List<Lieferung> findLieferungen(String nr);
	Lieferung createLieferung(Lieferung lieferung, List<Bestellung> bestellungen);
}
