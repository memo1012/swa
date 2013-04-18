package de.shop.bestellverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;
import java.util.Collection;
import java.util.Locale;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.util.LocaleHelper;
import de.shop.util.Mock;
import de.shop.util.NotFoundException;

//JP Code
import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.artikelverwaltung.rest.UriHelperArtikel;

//Ende JP

@Path("/bestellungen")
@Produces(APPLICATION_JSON)
@Consumes
public class BestellungResource {
	@Context
	private UriInfo uriInfo;
	
	@Context
	private HttpHeaders headers;

	@Inject
	private UriHelperBestellung uriHelperBestellung;
	
		//Anfang JP	Warum gibt es hier eine Bemerkung ?
		@Inject 
		private UriHelperArtikel uriHelperArtikel;
		//Ende JP
	
	
	
	@Inject
	private LocaleHelper localeHelper;
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Bestellung findBestellungById(@PathParam("id") Long id) {
		@SuppressWarnings("unused")
		final Locale locale = localeHelper.getLocale(headers);
		
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		final Bestellung bestellung = Mock.findBestellungById(id);
		if (bestellung == null) {
			throw new NotFoundException("Keine Bestellung mit der ID " + id + " gefunden.");
		}
		
		// URLs innerhalb der gefundenen Bestellung anpassen
		uriHelperBestellung.updateUriBestellung(bestellung, uriInfo);
		return bestellung;
	}
	

	@PUT
	@Consumes(APPLICATION_JSON)
	@Produces
	public Response updateBestellung(Bestellung bestellung) {
		@SuppressWarnings("unused")
		final Locale locale = localeHelper.getLocale(headers);

		// TODO Anwendungskern statt Mock, Verwendung von Locale
		Mock.updateBestellung(bestellung);
		return Response.noContent().build();
	}
	
	@POST 
	@Consumes(APPLICATION_JSON)
	@Produces
	public Response createBestellung(Bestellung bestellung) {

	@SuppressWarnings("unused")
	final Locale locale = localeHelper.getLocale(headers);

	// TODO Anwendungskern statt Mock, Verwendung von Locale
	bestellung = Mock.createBestellung(bestellung);
	final URI bestellungUri = uriHelperBestellung.getUriBestellung(bestellung, uriInfo);
	return Response.created(bestellungUri).build();
	}
	
	
	//Anfang JP Code
	@GET
	@Path("{id:[1-9][0-9]*}/artikeln")
	public Collection<Artikel> findArtikelnByBestellungId(@PathParam("id") Long bestellungId) {
		
		
		
		@SuppressWarnings("unused")
		final Locale locale = localeHelper.getLocale(headers);
		
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		final Collection<Artikel> artikeln = Mock.findArtikelnByBestellungId(bestellungId);
		if (artikeln.isEmpty()) {
			throw new NotFoundException("Zur Bestellung ID " + bestellungId + " wurden keine Artikeln gefunden");
		}
		
		// URLs innerhalb der gefundenen Bestellungen anpassen
		for (Artikel artikel : artikeln) {
			uriHelperArtikel.updateUriArtikel(artikel, uriInfo);
		}
		
		return artikeln;
	}
	//Ende JP Code
	
	
	
}
