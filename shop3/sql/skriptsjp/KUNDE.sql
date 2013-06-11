------------------------------------------------------------------
--  TABLE KUNDE
------------------------------------------------------------------

CREATE TABLE kunde
(
   art                VARCHAR2 (1 CHAR),
   id                 NUMBER (19),
   aktualisiert       TIMESTAMP (6),
   email              VARCHAR2 (128 CHAR),
   erzeugt            TIMESTAMP (6),
   nachname           VARCHAR2 (32 CHAR),
   newsletter         NUMBER (1),
   password           VARCHAR2 (256 CHAR),
   rabatt             NUMBER (5, 4),
   seit               DATE,
   umsatz             NUMBER (15, 3),
   vorname            VARCHAR2 (32 CHAR),
   familienstand_fk   NUMBER (10),
   geschlecht_fk      NUMBER (10)
)
TABLESPACE SYSTEM
NOCACHE
LOGGING;

ALTER TABLE kunde
  ADD CHECK ("ART" IS NOT NULL);

ALTER TABLE kunde
  ADD CHECK ("ID" IS NOT NULL);

ALTER TABLE kunde
  ADD CHECK ("AKTUALISIERT" IS NOT NULL);

ALTER TABLE kunde
  ADD CHECK ("EMAIL" IS NOT NULL);

ALTER TABLE kunde
  ADD CHECK ("ERZEUGT" IS NOT NULL);

ALTER TABLE kunde
  ADD CHECK ("NACHNAME" IS NOT NULL);

ALTER TABLE kunde
  ADD CHECK ("NEWSLETTER" IS NOT NULL);

ALTER TABLE kunde
  ADD CHECK ("RABATT" IS NOT NULL);

ALTER TABLE kunde
  ADD CHECK ("UMSATZ" IS NOT NULL);

ALTER TABLE kunde
  ADD PRIMARY KEY (id)
  USING INDEX TABLESPACE SYSTEM;

ALTER TABLE kunde
  ADD CONSTRAINT uc_kunde_1
  UNIQUE (email);

ALTER TABLE kunde
  ADD CONSTRAINT kunde__geschlecht_fk
  FOREIGN KEY (geschlecht_fk)
  REFERENCES shop.geschlecht (id);

ALTER TABLE kunde
  ADD CONSTRAINT kunde__familienstand_fk
  FOREIGN KEY (familienstand_fk)
  REFERENCES shop.familienstand (id);