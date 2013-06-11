------------------------------------------------------------------
--  TABLE BESTELLUNG_LIEFERUNG
------------------------------------------------------------------

CREATE TABLE bestellung_lieferung
(
   bestellung_fk   NUMBER (19),
   lieferung_fk    NUMBER (19)
)
TABLESPACE SYSTEM
NOCACHE
LOGGING;

ALTER TABLE bestellung_lieferung
  ADD CHECK ("BESTELLUNG_FK" IS NOT NULL);

ALTER TABLE bestellung_lieferung
  ADD CHECK ("LIEFERUNG_FK" IS NOT NULL);

ALTER TABLE bestellung_lieferung
  ADD PRIMARY KEY (bestellung_fk,lieferung_fk)
  USING INDEX TABLESPACE SYSTEM;

ALTER TABLE bestellung_lieferung
  ADD CONSTRAINT fk75baf0513645dad9
  FOREIGN KEY (lieferung_fk)
  REFERENCES shop.lieferung (id);

ALTER TABLE bestellung_lieferung
  ADD CONSTRAINT fk75baf0511aae988f
  FOREIGN KEY (bestellung_fk)
  REFERENCES shop.bestellung (id);