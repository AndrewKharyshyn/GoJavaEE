ALTER TABLE PROJECTS
  ADD COLUMN COST INT;

UPDATE PROJECTS
SET COST = 1700
WHERE PROJECT_ID = 1;
UPDATE PROJECTS
SET COST = 500
WHERE PROJECT_ID = 2;
UPDATE PROJECTS
SET COST = 3800
WHERE PROJECT_ID = 3;
UPDATE PROJECTS
SET COST = 11500
WHERE PROJECT_ID = 4;
UPDATE PROJECTS
SET COST = 8500
WHERE PROJECT_ID = 5;
UPDATE PROJECTS
SET COST = 1400
WHERE PROJECT_ID = 6;
UPDATE PROJECTS
SET COST = 10000
WHERE PROJECT_ID = 7;
UPDATE PROJECTS
SET COST = 1650
WHERE PROJECT_ID = 8;