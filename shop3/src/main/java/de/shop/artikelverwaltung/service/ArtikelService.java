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

	public Artikel findArtikelById(Long id) {
		// TODO id pruefen
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findArtikelById(id);
		
		//test
	}
	
	
	public Artikel findArtikelByBeschreibung(){
		
		return null;
	}
	
	
	public Artikel CreateArtikel(Artikel artikel, Locale locale){		
	
		
		if (artikel == null) {
			return artikel;
		}
		
		

		// Werden alle Constraints beim Einfuegen gewahrt?
		ValidateArtikel(artikel, locale, Default.class);

		// Pruefung, ob die Email-Adresse schon existiert
		// TODO Datenbanzugriffsschicht statt Mock
		//if (Mock.findArtikelByBezeichnung(artikel.getBezeichnung()) != null) {
			//throw new BezeichnungExistsException(artikel.getBezeichnung());
		//}

		artikel = Mock.createArtikel(artikel);

		return artikel;
		
		
		//return null;
	}

	
	private void ValidateArtikel(Artikel artikel, Locale locale, Class<?>... groups){
		
		// Werden alle Constraints beim Einfuegen gewahrt?
				final Validator validator = validatorProvider.getValidator(locale);
				
				final Set<ConstraintViolation<Artikel>> violations = validator.validate(artikel, groups);
				if (!violations.isEmpty()) {
					//throw new InvalidArtikelException(artikel, violations);
				}
		
	}
	
	public Artikel DeleteArtikel(Artikel artikel){
		return null;		
	}
	
	
	
}
