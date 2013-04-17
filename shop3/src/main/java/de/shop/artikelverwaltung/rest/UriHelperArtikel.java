package de.shop.artikelverwaltung.rest;

import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.rest.UriHelperKunde;


@ApplicationScoped
public class UriHelperArtikel {
	//@Inject
	//private UriHelperKunde uriHelperKunde;
	
	public void updateUriArtikel(Artikel artikel, UriInfo uriInfo) {
		// URL fuer Kunde setzen
		//final AbstractKunde kunde = bestellung.getKunde();
		//if (kunde != null) {
			//final URI kundeUri = uriHelperKunde.getUriKunde(bestellung.getKunde(), uriInfo);
			//bestellung.setKundeUri(kundeUri);
		//}
		
	}

	public URI getUriArtikel(Artikel artikel, UriInfo uriInfo) {
		final UriBuilder ub = uriInfo.getBaseUriBuilder()
		                             .path(ArtikelResource.class)
		                             .path(ArtikelResource.class, "findArtikelById");
		final URI uri = ub.build(artikel.getId());
		return uri;
	}
}
