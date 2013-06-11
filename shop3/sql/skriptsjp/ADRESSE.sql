------------------------------------------------------------------
--  TABLE ADRESSE
------------------------------------------------------------------

CREATE TABLE adresse
(
   id             NUMBER (19),
   aktualisiert   TIMESTAMP (6),
   erzeugt        TIMESTAMP (6),
   hausnr         VARCHAR2 (4 CHAR),
   ort            VARCHAR2 (32 CHAR),
   plz            VARCHAR2 (5 CHAR),
   strasse        VARCHAR2 (32 CHAR),
   kunde_fk       NUMBER (19)
)
TABLESPACE SYSTEM
NOCACHE
LOGGING;

ALTER TABLE adresse
  ADD CHECK ("ID" IS NOT NULL);

ALTER TABLE adresse
  ADD CHECK ("AKTUALISIERT" IS NOT NULL);

ALTER TABLE adresse
  ADD CHECK ("ERZEUGT" IS NOT NULL);

ALTER TABLE adresse
  ADD CHECK ("ORT" IS NOT NULL);

ALTER TABLE adresse
  ADD CHECK ("PLZ" IS NOT NULL);

ALTER TABLE adresse
  ADD CHECK ("STRASSE" IS NOT NULL);

ALTER TABLE adresse
  ADD CHECK ("KUNDE_FK" IS NOT NULL);

ALTER TABLE adresse
  ADD PRIMARY KEY (id)
  USING INDEX TABLESPACE SYSTEM;

ALTER TABLE adresse
  ADD CONSTRAINT fkbc5730af8279ca99
  FOREIGN KEY (kunde_fk)
  REFERENCES shop.kunde (id);