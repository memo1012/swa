------------------------------------------------------------------
--  TABLE ARTIKEL
------------------------------------------------------------------

CREATE TABLE artikel
(
   id             NUMBER (19),
   aktualisiert   TIMESTAMP (6),
   ausgesondert   NUMBER (1),
   bezeichnung    VARCHAR2 (32 CHAR),
   erzeugt        TIMESTAMP (6),
   preis          FLOAT
)
TABLESPACE SYSTEM
NOCACHE
LOGGING;

ALTER TABLE artikel
  ADD CHECK ("ID" IS NOT NULL);

ALTER TABLE artikel
  ADD CHECK ("AKTUALISIERT" IS NOT NULL);

ALTER TABLE artikel
  ADD CHECK ("AUSGESONDERT" IS NOT NULL);

ALTER TABLE artikel
  ADD CHECK ("BEZEICHNUNG" IS NOT NULL);

ALTER TABLE artikel
  ADD CHECK ("ERZEUGT" IS NOT NULL);

ALTER TABLE artikel
  ADD CHECK ("PREIS" IS NOT NULL);

ALTER TABLE artikel
  ADD PRIMARY KEY (id)
  USING INDEX TABLESPACE SYSTEM;