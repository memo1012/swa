-- ===============================================================================
-- Jede SQL-Anweisung muss in genau 1 Zeile
-- Kommentare durch -- am Zeilenanfang
-- ===============================================================================


INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand_fk, geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (1,'Admin','Admin','01.01.2001','F',NULL,NULL,1,'0,1',0,'admin@hska.de','1','01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand_fk, geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (101,'Alpha','Adrian','31.01.2001','P',1,0,1,'0,1','1500,5','101@hska.de','101','01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand_fk, geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (102,'Alpha','Alfred','28.02.2002','P',0,0,1,0,'500,5','102@hska.de','102','02.08.2006 00:00:00','02.08.2006 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand_fk, geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (103,'Alpha','Anton','15.09.2003','F',NULL,NULL,0,'0,1','0,5','103@hska.de','103','03.08.2006 00:00:00','03.08.2006 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand_fk, geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (104,'Delta','Dirk','30.04.2004','F',NULL,NULL,1,'0,15','1500,5','104@hska.de','104','04.08.2006 00:00:00','04.08.2006 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand_fk, geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (105,'Epsilon','Emil','31.03.2005','P',2,0,0,'0,0','1500,5','105@hska.de','105','05.08.2006 00:00:00','05.08.2006 00:00:00');
