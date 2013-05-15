package de.shop.artikelverwaltung.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.artikelverwaltung.domain.Kategorie;
import de.shop.util.Log;
import de.shop.util.MockService;

@MockService(findAllArtikeln = null, findAllKunden = null, findKundeByEmail = null, findKundeById = null)
@Log
public class ArtikelServiceMock extends ArtikelService {
	private static final long serialVersionUID = -2919310633845009282L;
	
	
	
	@Override
	public Artikel FindArtikelById(Long artikelid,Locale locale) {
		final Artikel artikel = new Artikel();
		artikel.setId(artikelid);
		artikel.setBezeichnung("Bezeichnung_" + artikelid + "_Mock");
		return artikel;
	}
	
	@Override
	public Artikel Find(final Object id){
		Artikel artikel = new Artikel();
		artikel.setId((Long) id);
		artikel.setBezeichnung("Bezeichnung " + id);
		return artikel;
	}
	
	

}
