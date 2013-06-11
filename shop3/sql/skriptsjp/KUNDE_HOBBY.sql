------------------------------------------------------------------
--  TABLE KUNDE_HOBBY
------------------------------------------------------------------

CREATE TABLE kunde_hobby
(
   kunde_fk   NUMBER (19),
   hobby_fk   NUMBER (10)
)
TABLESPACE SYSTEM
NOCACHE
LOGGING;

ALTER TABLE kunde_hobby
  ADD CHECK ("KUNDE_FK" IS NOT NULL);

ALTER TABLE kunde_hobby
  ADD CHECK ("HOBBY_FK" IS NOT NULL);

ALTER TABLE kunde_hobby
  ADD PRIMARY KEY (kunde_fk,hobby_fk)
  USING INDEX TABLESPACE SYSTEM;

ALTER TABLE kunde_hobby
  ADD CONSTRAINT fkb163e4d82f4f579
  FOREIGN KEY (kunde_fk)
  REFERENCES shop.kunde (id);

ALTER TABLE kunde_hobby
  ADD CONSTRAINT kunde_hobby__hobby_fk
  FOREIGN KEY (hobby_fk)
  REFERENCES shop.hobby (id);