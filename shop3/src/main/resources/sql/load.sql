-- ===============================================================================
-- Jede SQL-Anweisung muss in genau 1 Zeile
-- Kommentare durch -- am Zeilenanfang
-- ===============================================================================

-- ===============================================================================
-- Tabellen fuer Enum-Werte *einmalig* anlegen und jeweils Werte einfuegen
-- Beim ALLERERSTEN Aufruf die Zeilen mit "DROP TABLE ..." durch -- auskommentieren
-- ===============================================================================
DROP TABLE geschlecht;
CREATE TABLE geschlecht(id NUMBER(1) NOT NULL PRIMARY KEY, txt VARCHAR2(10) NOT NULL UNIQUE) CACHE;
INSERT INTO geschlecht VALUES (0, 'MAENNLICH');
INSERT INTO geschlecht VALUES (1, 'WEIBLICH');

DROP TABLE transport_art;
CREATE TABLE transport_art(id NUMBER(1) NOT NULL PRIMARY KEY, txt VARCHAR2(8) NOT NULL UNIQUE) CACHE;
INSERT INTO transport_art VALUES (0, 'STRASSE');
INSERT INTO transport_art VALUES (1, 'SCHIENE');
INSERT INTO transport_art VALUES (2, 'LUFT');
INSERT INTO transport_art VALUES (3, 'WASSER');

-- ===============================================================================
-- Fremdschluessel in den bereits *generierten* Tabellen auf die obigen "Enum-Tabellen" anlegen
-- ===============================================================================
ALTER TABLE kunde ADD CONSTRAINT kunde__geschlecht_fk FOREIGN KEY (geschlecht_fk) REFERENCES geschlecht;
ALTER TABLE lieferung ADD CONSTRAINT lieferung__transport_art_fk FOREIGN KEY (transport_art_fk) REFERENCES transport_art;

-- ===============================================================================
-- Indexe in den *generierten* Tabellen anlegen
-- ===============================================================================
CREATE INDEX adresse__kunde_index ON adresse(kunde_fk);
CREATE INDEX bestellung__kunde_index ON bestellung(kunde_fk);
CREATE INDEX bestpos__bestellung_index ON bestellposition(bestellung_fk);
CREATE INDEX bestpos__artikel_index ON bestellposition(artikel_fk);

INSERT INTO kunde (id, version, nachname, vorname, seit,  geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES(1,1,'Admin','Admin','01.01.2001',0,1,'0,1',0,'admin@hska.de','1','01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, version,	nachname, vorname, seit,  geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (101,1,'Alpha','Adrian','31.01.2001',0,1,'0,1','1500,5','101@hska.de','101','01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, version,	nachname, vorname, seit,  geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (102,1,'Alpha','Alfred','28.02.2002',0,1,0,'500,5','102@hska.de','102','02.08.2006 00:00:00','02.08.2006 00:00:00');
INSERT INTO kunde (id, version,	nachname, vorname, seit,  geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (103,1,'Alpha','Anton','15.09.2003',0,0,'0,1','0,5','103@hska.de','103','03.08.2006 00:00:00','03.08.2006 00:00:00');
INSERT INTO kunde (id, version,	nachname, vorname, seit,  geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (104,1,'Delta','Dirk','30.04.2004',0,1,'0,15','1500,5','104@hska.de','104','04.08.2006 00:00:00','04.08.2006 00:00:00');
INSERT INTO kunde (id, version,	nachname, vorname, seit,  geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (105,1,'Epsilon','Emil','31.03.2005',0,0,'0,0','1500,5','105@hska.de','105','05.08.2006 00:00:00','05.08.2006 00:00:00');
INSERT INTO kunde (id, version,	nachname, vorname, seit,  geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (106,1,'Jan','Jan','02.03.2005',0,0,'0,0','1500,5','106@hska.de','105','05.08.2006 00:00:00','05.08.2006 00:00:00');
INSERT INTO kunde (id, version,	nachname, vorname, seit,  geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (107,1,'Jan','Nick','02.04.2005',0,0,'0,0','1500,5','107@hska.de','105','05.08.2006 00:00:00','05.08.2006 00:00:00');
INSERT INTO kunde (id, version,	nachname, vorname, seit,  geschlecht_fk, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (108,1,'Mueller','Marina','02.04.2005',1,0,'0,0','1500,5','108@hska.de','105','05.08.2006 00:00:00','05.08.2006 00:00:00');

INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (1,'admin');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (1,'mitarbeiter');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (1,'abteilungsleiter');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (1,'kunde');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (101,'admin');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (101,'mitarbeiter');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (101,'kunde');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (102,'mitarbeiter');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (102,'kunde');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (103,'mitarbeiter');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (103,'kunde');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (104,'kunde');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (105,'kunde');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (106,'kunde');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (107,'kunde');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (108,'kunde');
INSERT INTO kunde_rolle (kunde_fk, rolle) VALUES (108,'mitarbeiter');

INSERT INTO adresse (id, plz, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (200,'76133','Karlsruhe','Moltkestrasse','30',1,to_timestamp('01.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('01.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO adresse (id, plz, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (201,'76133','Karlsruhe','Moltkestrasse','31',101,to_timestamp('02.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('02.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO adresse (id, plz, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (202,'76133','Karlsruhe','Moltkestrasse','32',102,to_timestamp('03.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('03.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO adresse (id, plz, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (203,'76133','Karlsruhe','Moltkestrasse','33',103,to_timestamp('04.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('04.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO adresse (id, plz, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (204,'76133','Karlsruhe','Moltkestrasse','34',104,to_timestamp('05.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('05.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO adresse (id, plz, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (205,'76133','Karlsruhe','Moltkestrasse','35',105,to_timestamp('06.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('06.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));


INSERT INTO artikel (id, version, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (300,1,'Tisch ''Oval''',80,0,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO artikel (id, version, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (301,1,'Stuhl ''Sitz bequem''',10,0,'02.08.2006 00:00:00','02.08.2006 00:00:00');
INSERT INTO artikel (id, version, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (302,1,'Tür ''Hoch und breit''',300,0,'03.08.2006 00:00:00','03.08.2006 00:00:00');
INSERT INTO artikel (id, version, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (303,1,'Fenster ''Glasklar''',150,0,'04.08.2006 00:00:00','04.08.2006 00:00:00');
INSERT INTO artikel (id, version, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (304,1,'Spiegel ''Mach mich schöner''',60,0,'05.08.2006 00:00:00','05.08.2006 00:00:00');
INSERT INTO artikel (id, version, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (305,1,'Kleiderschrank ''Viel Platz''',500,0,'06.08.2006 00:00:00','06.08.2006 00:00:00');
INSERT INTO artikel (id, version, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (306,1,'Bett ''Mit Holzwurm''',600,0,'07.08.2006 00:00:00','07.08.2006 00:00:00');
INSERT INTO artikel (id, version, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (307,1,'Couch ''Ohne Holzwurm''',750,0,'07.08.2006 00:00:00','07.08.2006 00:00:00');
INSERT INTO artikel (id, version, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (308,1,'Sofa ''Mit Sitzmulde''',450,0,'07.08.2006 00:00:00','07.08.2006 00:00:00');


INSERT INTO bestellung (id, version, kunde_fk, idx, erzeugt, aktualisiert) VALUES (400,1,101,0,to_timestamp('01.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('01.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO bestellung (id, version, kunde_fk, idx, erzeugt, aktualisiert) VALUES (401,1,101,1,to_timestamp('02.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('02.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO bestellung (id, version, kunde_fk, idx, erzeugt, aktualisiert) VALUES (402,1,102,0,to_timestamp('03.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('03.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO bestellung (id, version, kunde_fk, idx, erzeugt, aktualisiert) VALUES (403,1,102,1,to_timestamp('04.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('04.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO bestellung (id, version, kunde_fk, idx, erzeugt, aktualisiert) VALUES (404,1,104,0,to_timestamp('05.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('05.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));


INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, idx) VALUES (500,400,300,1,0);
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, idx) VALUES (501,400,301,4,1);
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, idx) VALUES (502,401,302,5,0);
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, idx) VALUES (503,402,303,3,0);
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, idx) VALUES (504,402,304,2,1);
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, idx) VALUES (505,403,305,1,0);
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, idx) VALUES (506,404,300,5,0);
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, idx) VALUES (507,404,301,2,1);
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, idx) VALUES (508,404,302,8,2);


INSERT INTO lieferung (id, liefernr, transport_art_fk, erzeugt, aktualisiert) VALUES (600,'20051005-001',0,to_timestamp('01.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('01.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO lieferung (id, liefernr, transport_art_fk, erzeugt, aktualisiert) VALUES (601,'20051005-002',1,to_timestamp('02.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('02.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO lieferung (id, liefernr, transport_art_fk, erzeugt, aktualisiert) VALUES (602,'20051005-003',2,to_timestamp('03.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('03.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));
INSERT INTO lieferung (id, liefernr, transport_art_fk, erzeugt, aktualisiert) VALUES (603,'20051008-001',3,to_timestamp('04.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'),to_timestamp('04.08.2006 00:00:00','DD.MM.YYYY HH24:MI:SS'));


INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (400,600);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (401,600);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (402,601);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (402,602);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (403,602);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (404,603);

