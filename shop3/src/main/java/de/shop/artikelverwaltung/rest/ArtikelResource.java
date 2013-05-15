package de.shop.artikelverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.artikelverwaltung.service.ArtikelService;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.rest.UriHelperKunde;
import de.shop.util.LocaleHelper;
import de.shop.util.Log;
import de.shop.util.Mock;
import de.shop.util.NotFoundException;

@Path("/artikel")
@Produces(APPLICATION_JSON)
@Consumes
@RequestScoped
@Log
public class ArtikelResource {
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@Context
	private UriInfo uriInfo;
	
	@Context
	private HttpHeaders headers;
	
	@Inject
	private ArtikelService as;
	
	@Inject
	private UriHelperArtikel uriHelperartikel;
	
	
	
	@Inject
	private LocaleHelper localeHelper;
	
	@PostConstruct
	private void postConstruct() {
		LOGGER.debugf("CDI-faehiges Bean %s wurde erzeugt", this);
	}
	
	@PreDestroy
	private void preDestroy() {
		LOGGER.debugf("CDI-faehiges Bean %s wird geloescht", this);
	}
	
	@GET
	public Collection<Artikel> findArtikeln() {
		//@SuppressWarnings("unused")
		
		final Collection<Artikel> artikeln = Mock.findAllArtikeln();
		return artikeln;
	}
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Artikel findArtikelById(@PathParam("id") Long id, @Context UriInfo uriInfo) {
		//@SuppressWarnings("unused")
		final Locale locale = localeHelper.getLocale(headers);
		final Artikel artikel = as.FindArtikelById(id,locale);
		if (artikel == null) {
			final String msg = "Kein Artikel gefunden mit der ID " + id;
			throw new NotFoundException(msg);
		}

		return artikel;
	}
	
	@GET
	public Artikel findArtikelByBezeichnung(@QueryParam("bezeichnung") @DefaultValue("") String bezeichnung) {
		@SuppressWarnings("unused")
		final Locale locale = localeHelper.getLocale(headers);
		
		Artikel artikel = null;
		Collection<Artikel> artikeln = null;
		if ("".equals(bezeichnung)) {
			// TODO Anwendungskern statt Mock, Verwendung von Locale
			artikeln = Mock.findAllArtikeln();
			if (artikeln==null) {
				throw new NotFoundException("Es gibt kein vorhandene Artikel");
			}
		}
		else {
			// TODO Anwendungskern statt Mock, Verwendung von Locale
			artikel = Mock.findArtikelByBezeichnung(bezeichnung);
			if (artikel==null) {
				throw new NotFoundException("Kein Artikel mit Bezeichnung " + bezeichnung + " gefunden.");
			}
		}
		
		
		uriHelperartikel.updateUriArtikel(artikel, uriInfo);
		
		
		return artikel;
	}
}
