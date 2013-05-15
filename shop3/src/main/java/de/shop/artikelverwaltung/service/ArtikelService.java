package de.shop.artikelverwaltung.service;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.jboss.logging.Logger;

import de.shop.artikelverwaltung.domain.Artikel;
//import de.shop.kundenverwaltung.domain.AbstractKunde;
//import de.shop.kundenverwaltung.service.EmailExistsException;
//import de.shop.kundenverwaltung.service.InvalidKundeIdException;
//import de.shop.kundenverwaltung.service.InvalidNachnameException;
//import de.shop.kundenverwaltung.service.KundeDeleteBestellungException;
import de.shop.artikelverwaltung.service.ArtikelServiceException;
import de.shop.artikelverwaltung.service.ArtikelValidationException;
import de.shop.artikelverwaltung.service.InvalidArtikelException;
import de.shop.util.IdGroup;
import de.shop.util.Log;
import de.shop.util.Mock;
import de.shop.util.ValidatorProvider;

import java.util.List;
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
		final Artikel artikel =  Mock.findArtikelById(artikelid);
		return artikel;
		
	}
	
	
	public Artikel FindArtikelByBezeichnung(String bezeichnung, Locale locale){
		
		validateBezeichnung(bezeichnung, locale);
		
		// TODO Datenbanzugriffsschicht statt Mock
		Artikel artikel = Mock.findArtikelByBezeichnung(bezeichnung);
		return artikel;
		
	}
	
	
	public Artikel CreateArtikel(Artikel artikel, Locale locale){	
	//null prufung
	//Validierung
	//Gibt es schon die gleiche Bezeichnung ?
	//Generiert Objekt
		
		if (artikel == null) {
			return artikel;
		}
		
		
		// Werden alle Constraints beim Einfuegen gewahrt?
		ValidateArtikel(artikel, locale, Default.class);

		// Pruefung, ob die Bezeichnung schon existiert
		// TODO Datenbanzugriffsschicht statt Mock
		if (Mock.findArtikelByBezeichnung(artikel.getBezeichnung()) != null) {
			throw new BezeichnungExistsException(artikel.getBezeichnung());
		}

		artikel = Mock.createArtikel(artikel);

		return artikel;
		
	}

	
	private void ValidateArtikelId(long artikelId,Locale locale){
		
		final Validator validator = validatorProvider.getValidator(locale);
		final Set<ConstraintViolation<Artikel>> violations = validator.validateValue(Artikel.class,
				                                                                           "id",
				                                                                           artikelId,
				                                                                           IdGroup.class);
		if (!violations.isEmpty())
			throw new InvalidArtikelIdException(artikelId, violations);
	}
	
	
	
	private void ValidateArtikel(Artikel artikel, Locale locale, Class<?>... groups){
		
		// Werden alle Constraints beim Einfuegen gewahrt?
				final Validator validator = validatorProvider.getValidator(locale);
				
				final Set<ConstraintViolation<Artikel>> violations = validator.validate(artikel, groups);
				if (!violations.isEmpty()) {
					throw new InvalidArtikelException(artikel, violations);
				}
		
	}
	
	private void validateBezeichnung(String bezeichnung, Locale locale) {
		final Validator validator = validatorProvider.getValidator(locale);
		final Set<ConstraintViolation<Artikel>> violations = validator.validateValue(Artikel.class,
				                                                                           "bezeichnung",
				                                                                           bezeichnung,
				                                                                           Default.class);
		if (!violations.isEmpty())
			throw new InvalidBezeichnungException(bezeichnung, violations);
	}
	
	
	
	public Artikel UpdateArtikel(Artikel artikel,Locale locale){
		if(artikel == null){
			return null;
		}
		
		ValidateArtikel(artikel,locale,Default.class, IdGroup.class);
		
		// Pruefung, ob die Bezeichnung schon existiert
		final Artikel vorhandenerartikel = Mock.findArtikelByBezeichnung(artikel.getBezeichnung());
		
		// Gibt es die Email-Adresse bei einem anderen, bereits vorhandenen Kunden?
				if (vorhandenerartikel.getId().longValue() != artikel.getId().longValue()) {
					throw new BezeichnungExistsException(artikel.getBezeichnung());
				}
				
				// TODO Datenbanzugriffsschicht statt Mock
				Mock.updateArtikel(artikel);
				
				return artikel;
		
	}
	
	public void DeleteArtikel(Long artikelId, Locale locale){
			ValidateArtikelId(artikelId,locale);
			
			final Artikel artikel = FindArtikelById(artikelId,locale);
			if(artikel==null){
				return;}
			
			// TODO Datenbanzugriffsschicht statt Mock
			Mock.DeleteArtikel(artikel);
			
	}

	public Artikel Find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}
			
	
	
	
	
	
}
