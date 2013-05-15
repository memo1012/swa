package de.shop.util;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Stereotype;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.jboss.logging.Logger;

import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.kundenverwaltung.domain.AbstractKunde;






@Alternative
@Stereotype
@Target({ TYPE, METHOD, FIELD })
@Retention(RUNTIME)
public  @interface MockService {
	static AbstractKunde findKundeById(Long id);

	List<AbstractKunde> findAllKunden();

	AbstractKunde findKundeByEmail(String email);

	List<Artikel> findAllArtikeln();
}

