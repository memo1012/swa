------------------------------------------------------------------
--  TABLE LIEFERUNG
------------------------------------------------------------------

CREATE TABLE lieferung
(
   id                 NUMBER (19),
   aktualisiert       TIMESTAMP (6),
   erzeugt            TIMESTAMP (6),
   liefernr           VARCHAR2 (12 CHAR),
   transport_art_fk   NUMBER (10)
)
TABLESPACE SYSTEM
NOCACHE
LOGGING;

ALTER TABLE lieferung
  ADD CHECK ("ID" IS NOT NULL);

ALTER TABLE lieferung
  ADD CHECK ("AKTUALISIERT" IS NOT NULL);

ALTER TABLE lieferung
  ADD CHECK ("ERZEUGT" IS NOT NULL);

ALTER TABLE lieferung
  ADD CHECK ("LIEFERNR" IS NOT NULL);

ALTER TABLE lieferung
  ADD PRIMARY KEY (id)
  USING INDEX TABLESPACE SYSTEM;

ALTER TABLE lieferung
  ADD CONSTRAINT uc_lieferung_1
  UNIQUE (liefernr);

ALTER TABLE lieferung
  ADD CONSTRAINT lieferung__transport_art_fk
  FOREIGN KEY (transport_art_fk)
  REFERENCES shop.transport_art (id);