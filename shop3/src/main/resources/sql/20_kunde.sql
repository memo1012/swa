-- ===============================================================================
-- Jede SQL-Anweisung muss in genau 1 Zeile
-- Kommentare durch -- am Zeilenanfang
-- ===============================================================================


INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand_fk, geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (1,'Admin','Admin',to_date('01.01.2001','DD.MM.YYYY'),'F',NULL,NULL,1,'0.1',0,'admin@hska.de','1',to_timestamp('01.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('01.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand_fk, geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (101,'Alpha','Adrian',to_date('31.01.2001','DD.MM.YYYY'),'P',1,0,1,'0.1','1500.5','101@hska.de','101',to_timestamp('01.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('01.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand_fk, geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (102,'Alpha','Alfred',to_date('28.02.2002','DD.MM.YYYY'),'P',0,0,1,0,'500.5','102@hska.de','102',to_timestamp('02.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('02.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand_fk, geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (103,'Alpha','Anton',to_date('15.09.2003','DD.MM.YYYY'),'F',NULL,NULL,0,'0.1','0.5','103@hska.de','103',to_timestamp('03.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('03.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand_fk, geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (104,'Delta','Dirk',to_date('30.04.2004','DD.MM.YYYY'),'F',NULL,NULL,1,'0.15','1500.5','104@hska.de','104',to_timestamp('04.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('04.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand_fk, geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (105,'Epsilon','Emil',to_date('31.03.2005','DD.MM.YYYY'),'P',2,0,0,'0.0','1500.5','105@hska.de','105',to_timestamp('05.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('05.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
