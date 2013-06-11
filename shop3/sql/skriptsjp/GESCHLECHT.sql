------------------------------------------------------------------
--  TABLE GESCHLECHT
------------------------------------------------------------------

CREATE TABLE geschlecht
(
   id    NUMBER (1),
   txt   VARCHAR2 (10 BYTE)
)
TABLESPACE SYSTEM
CACHE
LOGGING;

ALTER TABLE geschlecht
  ADD CHECK ("ID" IS NOT NULL);

ALTER TABLE geschlecht
  ADD CHECK ("TXT" IS NOT NULL);

ALTER TABLE geschlecht
  ADD PRIMARY KEY (id)
  USING INDEX TABLESPACE SYSTEM;

ALTER TABLE geschlecht
  ADD UNIQUE (txt);