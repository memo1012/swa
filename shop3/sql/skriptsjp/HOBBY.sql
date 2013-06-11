------------------------------------------------------------------
--  TABLE HOBBY
------------------------------------------------------------------

CREATE TABLE hobby
(
   id    NUMBER (1),
   txt   VARCHAR2 (16 BYTE)
)
TABLESPACE SYSTEM
CACHE
LOGGING;

ALTER TABLE hobby
  ADD CHECK ("ID" IS NOT NULL);

ALTER TABLE hobby
  ADD CHECK ("TXT" IS NOT NULL);

ALTER TABLE hobby
  ADD PRIMARY KEY (id)
  USING INDEX TABLESPACE SYSTEM;

ALTER TABLE hobby
  ADD UNIQUE (txt);