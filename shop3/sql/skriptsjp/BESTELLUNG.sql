------------------------------------------------------------------
--  TABLE BESTELLUNG
------------------------------------------------------------------

CREATE TABLE bestellung
(
   id             NUMBER (19),
   aktualisiert   TIMESTAMP (6),
   erzeugt        TIMESTAMP (6),
   kunde_fk       NUMBER (19),
   idx            NUMBER (10)
)
TABLESPACE SYSTEM
NOCACHE
LOGGING;

ALTER TABLE bestellung
  ADD CHECK ("ID" IS NOT NULL);

ALTER TABLE bestellung
  ADD CHECK ("AKTUALISIERT" IS NOT NULL);

ALTER TABLE bestellung
  ADD CHECK ("ERZEUGT" IS NOT NULL);

ALTER TABLE bestellung
  ADD CHECK ("KUNDE_FK" IS NOT NULL);

ALTER TABLE bestellung
  ADD CHECK ("IDX" IS NOT NULL);

ALTER TABLE bestellung
  ADD PRIMARY KEY (id)
  USING INDEX TABLESPACE SYSTEM;

ALTER TABLE bestellung
  ADD CONSTRAINT fk5815590d8279ca99
  FOREIGN KEY (kunde_fk)
  REFERENCES shop.kunde (id);