------------------------------------------------------------------
--  TABLE WARTUNGSVERTRAG
------------------------------------------------------------------

CREATE TABLE wartungsvertrag
(
   datum          DATE,
   nr             NUMBER (19),
   aktualisiert   TIMESTAMP (6),
   erzeugt        TIMESTAMP (6),
   inhalt         VARCHAR2 (255 CHAR),
   kunde_fk       NUMBER (19),
   idx            NUMBER (10)
)
TABLESPACE SYSTEM
NOCACHE
LOGGING;

ALTER TABLE wartungsvertrag
  ADD CHECK ("DATUM" IS NOT NULL);

ALTER TABLE wartungsvertrag
  ADD CHECK ("NR" IS NOT NULL);

ALTER TABLE wartungsvertrag
  ADD CHECK ("AKTUALISIERT" IS NOT NULL);

ALTER TABLE wartungsvertrag
  ADD CHECK ("ERZEUGT" IS NOT NULL);

ALTER TABLE wartungsvertrag
  ADD CHECK ("INHALT" IS NOT NULL);

ALTER TABLE wartungsvertrag
  ADD CHECK ("KUNDE_FK" IS NOT NULL);

ALTER TABLE wartungsvertrag
  ADD CHECK ("IDX" IS NOT NULL);

ALTER TABLE wartungsvertrag
  ADD PRIMARY KEY (datum,nr)
  USING INDEX TABLESPACE SYSTEM;

ALTER TABLE wartungsvertrag
  ADD CONSTRAINT fk77996e568279ca99
  FOREIGN KEY (kunde_fk)
  REFERENCES shop.kunde (id);