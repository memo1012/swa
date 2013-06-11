------------------------------------------------------------------
--  TABLE FAMILIENSTAND
------------------------------------------------------------------

CREATE TABLE familienstand
(
   id    NUMBER (1),
   txt   VARCHAR2 (12 BYTE)
)
TABLESPACE SYSTEM
CACHE
LOGGING;

ALTER TABLE familienstand
  ADD CHECK ("ID" IS NOT NULL);

ALTER TABLE familienstand
  ADD CHECK ("TXT" IS NOT NULL);

ALTER TABLE familienstand
  ADD PRIMARY KEY (id)
  USING INDEX TABLESPACE SYSTEM;

ALTER TABLE familienstand
  ADD UNIQUE (txt);