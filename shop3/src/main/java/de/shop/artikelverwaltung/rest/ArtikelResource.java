package de.shop.artikelverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static de.shop.util.Constants.KEINE_ID;





import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.Collection;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.artikelverwaltung.service.ArtikelService;
import de.shop.util.LocaleHelper;
import de.shop.util.Log;
import de.shop.util.NotFoundException;
import de.shop.util.Transactional;


@Path("/artikel")
@Produces({ APPLICATION_XML, TEXT_XML, APPLICATION_JSON })
@Consumes
@RequestScoped
@Transactional
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
	private LocaleHelper localeHelper;
	
	@Inject
	private UriHelperArtikel uriHelperArtikel;
	
	@PostConstruct
	private void postConstruct() {
		LOGGER.debugf("CDI-faehiges Bean %s wurde erzeugt", this);
	}
	
	@PreDestroy
	private void preDestroy() {
		LOGGER.debugf("CDI-faehiges Bean %s wird geloescht", this);
	}
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Artikel findArtikelbyId(@PathParam("id") Long id) {
		final Artikel artikel = as.findArtikelById(id);
		if (artikel == null) {
			final String msg = "Kein Artikel gefunden mit der ID " + id;
			throw new NotFoundException(msg);
		}

		return artikel;
	}
	@GET
	@Path ("/prefix/bezeichnung/{bezeichnung}")
	public Collection <String> findArtikelbybez(@PathParam("bezeichnung") String BezPrefix) {
		final Locale locale = localeHelper.getLocale(headers);
		final Collection <String> artikel= as.findArtikelByBezeichnung(BezPrefix,locale);
		if (artikel == null) {
			final String msg = "Kein Artikel gefunden mit der Bezeicnhng " + BezPrefix;
			throw new NotFoundException(msg);
		}
		return artikel;
	}

	/*@GET
	@Path ("/prefix/preis/{Preis}")
	public Collection <Artikel> findArtikelbyPreis(@PathParam("Preis") String PreisPrefix) {
		final Collection <Artikel> artikel= as.findArtikelByBezeichnung(PreisPrefix);
		if (artikel == null) {
			final String msg = "Kein Artikel f�r den Preis " + PreisPrefix + "gefunden!";
			throw new NotFoundException(msg);
	}
	return artikel;
}*/
	
	
	@POST
	@Consumes(APPLICATION_JSON)
	@Produces
	public Response createArtikel(Artikel artikel) {
		final Locale locale = localeHelper.getLocale(headers);
		
		artikel.setId(KEINE_ID);
		artikel.setBezeichnung(artikel.getBezeichnung());
		
		//LOGGER noch implementieren
		
		artikel = as.createArtikel(artikel, locale);
		
		final URI artikelUri = uriHelperArtikel.getUriArtikel(artikel, uriInfo);
		return Response.created(artikelUri).build();
	}
}
