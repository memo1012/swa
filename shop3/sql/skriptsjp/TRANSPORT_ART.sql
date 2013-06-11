------------------------------------------------------------------
--  TABLE TRANSPORT_ART
------------------------------------------------------------------

CREATE TABLE transport_art
(
   id    NUMBER (1),
   txt   VARCHAR2 (8 BYTE)
)
TABLESPACE SYSTEM
CACHE
LOGGING;

ALTER TABLE transport_art
  ADD CHECK ("ID" IS NOT NULL);

ALTER TABLE transport_art
  ADD CHECK ("TXT" IS NOT NULL);

ALTER TABLE transport_art
  ADD PRIMARY KEY (id)
  USING INDEX TABLESPACE SYSTEM;

ALTER TABLE transport_art
  ADD UNIQUE (txt);