package de.shop.artikelverwaltung.service;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.jboss.logging.Logger;

import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.service.KundeDeleteBestellungException;
import de.shop.artikelverwaltung.service.ArtikelServiceException;
import de.shop.artikelverwaltung.service.ArtikelValidationException;
import de.shop.artikelverwaltung.service.InvalidArtikelException;

import de.shop.util.Log;
import de.shop.util.Mock;
import de.shop.util.ValidatorProvider;

import java.util.Locale;
import java.util.Set;


@Log
public class ArtikelService implements Serializable {
	private static final long serialVersionUID = -5105686816948437276L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@Inject
	private ValidatorProvider validatorProvider;
	
	@PostConstruct
	private void postConstruct() {
		LOGGER.debugf("CDI-faehiges Bean %s wurde erzeugt", this);
	}
	
	@PreDestroy
	private void preDestroy() {
		LOGGER.debugf("CDI-faehiges Bean %s wird geloescht", this);
	}

	public Artikel FindArtikelById(Long artikelid, Locale locale) {
		// TODO id pruefen
		ValidateArtikelId(artikelid,locale);
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findArtikelById(artikelid);
		
		
	}
	
	
	public Artikel FindArtikelByBeschreibung(){
		
		return null;
	}
	
	
	public Artikel CreateArtikel(Artikel artikel, Locale locale){		
	
		
		if (artikel == null) {
			return artikel;
		}
		
		

		// Werden alle Constraints beim Einfuegen gewahrt?
		ValidateArtikel(artikel, locale, Default.class);

		// Pruefung, ob die Bezeichnung schon existiert
		// TODO Datenbanzugriffsschicht statt Mock
		//if (Mock.findArtikelByBezeichnung(artikel.getBezeichnung()) != null) {
			//throw new BezeichnungExistsException(artikel.getBezeichnung());
		//}

		artikel = Mock.createArtikel(artikel);

		return artikel;
		
		
		//return null;
	}

	
	private void ValidateArtikelId(long artikelid,Locale locale){
		
		
	}
	
	private void ValidateArtikel(Artikel artikel, Locale locale, Class<?>... groups){
		
		// Werden alle Constraints beim Einfuegen gewahrt?
				final Validator validator = validatorProvider.getValidator(locale);
				
				final Set<ConstraintViolation<Artikel>> violations = validator.validate(artikel, groups);
				if (!violations.isEmpty()) {
					throw new InvalidArtikelException(artikel, violations);
				}
		
	}
	
	
	
	public Artikel UpdateArtikel(Artikel artikel){
		return null;		
	}
	
	public void DeleteArtikel(Long artikelId, Locale locale){
			ValidateArtikelId(artikelId,locale);
			
			final Artikel artikel = FindArtikelById(artikelId,locale);
			if(artikel==null){
				return;}
			
			// TODO Datenbanzugriffsschicht statt Mock
			Mock.deleteArtikel(artikel);
			
	}
			
	//	public void deleteKunde(Long kundeId, Locale locale) {
		//	validateKundeId(kundeId, locale);
			//final AbstractKunde kunde = findKundeById(kundeId, locale);
			//if (kunde == null) {
				//return;
			//}

			// Gibt es Bestellungen?
			//if (!kunde.getBestellungen().isEmpty()) {
				//throw new KundeDeleteBestellungException(kunde);
			//}
			
			////  Datenbanzugriffsschicht statt Mock
			//Mock.deleteKunde(kunde);
		//}
	
	
	
	
}
