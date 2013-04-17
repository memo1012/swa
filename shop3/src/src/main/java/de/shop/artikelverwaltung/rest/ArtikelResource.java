package de.shop.artikelverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import java.net.URI;
import java.util.Collection;
import java.util.Locale;

import de.shop.artikelverwaltung.domain.Artikel;


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

import de.shop.util.LocaleHelper;
import de.shop.util.Mock;
import de.shop.util.NotFoundException;

@Path("/artikeln")
@Produces(APPLICATION_JSON)
@Consumes
public class ArtikelResource {
	@Context
	private UriInfo uriInfo;
	
	@Context
	private HttpHeaders headers;

	@Inject
	private UriHelperArtikel uriHelperArtikel;
	
	@Inject
	private LocaleHelper localeHelper;
	
	
	@GET
	@Produces(TEXT_PLAIN)
	@Path("version")
	public String getVersion() {
		return "1.0";
	}
	
	@GET
	public Collection<Artikel> findArtikeln(){
		final Collection<Artikel> artikeln = Mock.findAllArtikeln();
		return artikeln;		
	}
	
	@POST
	@Consumes(APPLICATION_JSON)
	@Produces
	public Response createArtikel(Artikel artikel) {
		@SuppressWarnings("unused")
		final Locale locale = localeHelper.getLocale(headers);
		
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		artikel = Mock.createArtikel(artikel);
		final URI artikelUri = uriHelperArtikel.getUriArtikel(artikel, uriInfo);
		return Response.created(artikelUri).build();
	}
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Artikel findArtikelgById(@PathParam("id") Long id) {
		@SuppressWarnings("unused")
		final Locale locale = localeHelper.getLocale(headers);
		
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		final Artikel artikel = Mock.findArtikelById(id);
		if (artikel == null) {
			throw new NotFoundException("Keine Artikel mit der ID " + id + " gefunden.");
		}
		
		// URLs innerhalb der gefundenen Bestellung anpassen
		uriHelperArtikel.updateUriArtikel(artikel, uriInfo);
		return artikel;
	}
	
	
	//Update -> Hochladen
		@PUT
		@Consumes(APPLICATION_JSON)
		@Produces
		public Response updateArtikel(Artikel artikel) {
			@SuppressWarnings("unused")
			final Locale locale = localeHelper.getLocale(headers);
			
			// TODO Anwendungskern statt Mock, Verwendung von Locale
			Mock.updateArtikel(artikel);
			return Response.noContent().build();
		}
		
		//Wollen wir ehrlich entfernen oder nur die Verfügbarkeit ändern ?
	//	@DELETE
		//@Path("{id:[1-9][0-9]*}")
		//@Produces
		//public Response deleteArtikel(@PathParam("id") Long artikelId) {
			//@SuppressWarnings("unused")
			//final Locale locale = localeHelper.getLocale(headers);
			
			// TODO Anwendungskern statt Mock, Verwendung von Locale
			//Mock.deleteArtikel(artikelId);
			//return Response.noContent().build();
		//}
	
	//@GET
	//@Path("{id:[1-9][0-9]*}/bestellungen")
	//public Collection<Bestellung> findBestellungenByKundeId(@PathParam("id") Long kundeId) {
		//@SuppressWarnings("unused")
		//final Locale locale = localeHelper.getLocale(headers);
		
		//// TODO Anwendungskern statt Mock, Verwendung von Locale
		//final Collection<Bestellung> bestellungen = Mock.findBestellungenByKundeId(kundeId);
		//if (bestellungen.isEmpty()) {
		//	throw new NotFoundException("Zur ID " + kundeId + " wurden keine Bestellungen gefunden");
		//}
		
		//// URLs innerhalb der gefundenen Bestellungen anpassen
		//for (Bestellung bestellung : bestellungen) {
		//	uriHelperBestellung.updateUriBestellung(bestellung, uriInfo);
		//}
		
		//return bestellungen;
	//}
	
}
