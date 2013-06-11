------------------------------------------------------------------
--  TABLE BESTELLPOSITION
------------------------------------------------------------------

CREATE TABLE bestellposition
(
   id              NUMBER (19),
   anzahl          NUMBER (5),
   artikel_fk      NUMBER (19),
   bestellung_fk   NUMBER (19),
   idx             NUMBER (10)
)
TABLESPACE SYSTEM
NOCACHE
LOGGING;

ALTER TABLE bestellposition
  ADD CHECK ("ID" IS NOT NULL);

ALTER TABLE bestellposition
  ADD CHECK ("ANZAHL" IS NOT NULL);

ALTER TABLE bestellposition
  ADD CHECK ("ARTIKEL_FK" IS NOT NULL);

ALTER TABLE bestellposition
  ADD CHECK ("BESTELLUNG_FK" IS NOT NULL);

ALTER TABLE bestellposition
  ADD CHECK ("IDX" IS NOT NULL);

ALTER TABLE bestellposition
  ADD CHECK (anzahl>=1);

ALTER TABLE bestellposition
  ADD PRIMARY KEY (id)
  USING INDEX TABLESPACE SYSTEM;

ALTER TABLE bestellposition
  ADD CONSTRAINT fkaa81c06a43606bee
  FOREIGN KEY (artikel_fk)
  REFERENCES shop.artikel (id);

ALTER TABLE bestellposition
  ADD CONSTRAINT fkaa81c06a1aae988f
  FOREIGN KEY (bestellung_fk)
  REFERENCES shop.bestellung (id);