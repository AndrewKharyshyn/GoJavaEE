CREATE DATABASE developerdb;

CREATE TABLE DEVELOPERS (
  DEVELOPER_ID INT AUTO_INCREMENT NOT NULL,
  DEV_NAME     VARCHAR(50)        NOT NULL,
  DEV_SURNAME  VARCHAR(50)        NOT NULL,
  COMPANY      INT                NOT NULL,
  PRIMARY KEY (DEVELOPER_ID)
);

CREATE TABLE SKILLS (
  SKILL_ID INT AUTO_INCREMENT NOT NULL,
  SKILL    VARCHAR(50)        NOT NULL,
  PRIMARY KEY (SKILL_ID)
);

CREATE TABLE PROJECTS (
  PROJECT_ID   INT AUTO_INCREMENT NOT NULL,
  PROJECT_NAME VARCHAR(150)       NOT NULL,
  PRIMARY KEY (PROJECT_ID)
);

CREATE TABLE COMPANIES (
  COMPANY_ID   INT AUTO_INCREMENT NOT NULL,
  COMPANY_NAME VARCHAR(150)       NOT NULL,
  PRIMARY KEY (COMPANY_ID)
);

CREATE TABLE CUSTOMERS (
  CUSTOMER_ID   INT AUTO_INCREMENT NOT NULL,
  CUSTOMER_NAME VARCHAR(150)       NOT NULL,
  PRIMARY KEY (CUSTOMER_ID)
);

CREATE TABLE DEVELOPER_SKILL (
  DEVELOPER_ID INT NOT NULL,
  SKILL_ID     INT NOT NULL,
  PRIMARY KEY (DEVELOPER_ID, SKILL_ID)
);

CREATE TABLE DEVELOPER_PROJECT (
  DEVELOPER_ID INT NOT NULL,
  PROJECT_ID   INT NOT NULL,
  PRIMARY KEY (DEVELOPER_ID, PROJECT_ID)
);

CREATE TABLE COMPANY_PROJECT (
  COMPANY_ID INT NOT NULL,
  PROJECT_ID INT NOT NULL,
  PRIMARY KEY (COMPANY_ID, PROJECT_ID)
);

CREATE TABLE CUSTOMER_PROJECT (
  CUSTOMER_ID INT NOT NULL,
  PROJECT_ID  INT NOT NULL,
  PRIMARY KEY (CUSTOMER_ID, PROJECT_ID)
);

ALTER TABLE DEVELOPERS
  ADD FOREIGN KEY (COMPANY) REFERENCES COMPANIES (COMPANY_ID);

ALTER TABLE DEVELOPER_PROJECT
  ADD FOREIGN KEY (PROJECT_ID) REFERENCES PROJECTS (PROJECT_ID),
  ADD FOREIGN KEY (DEVELOPER_ID) REFERENCES DEVELOPERS (DEVELOPER_ID);

ALTER TABLE COMPANY_PROJECT
  ADD FOREIGN KEY (PROJECT_ID) REFERENCES PROJECTS (PROJECT_ID),
  ADD FOREIGN KEY (COMPANY_ID) REFERENCES COMPANIES (COMPANY_ID);

ALTER TABLE DEVELOPER_SKILL
  ADD FOREIGN KEY (SKILL_ID) REFERENCES SKILLS (SKILL_ID),
  ADD FOREIGN KEY (DEVELOPER_ID) REFERENCES DEVELOPERS (DEVELOPER_ID);

ALTER TABLE CUSTOMER_PROJECT
  ADD FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS (CUSTOMER_ID),
  ADD FOREIGN KEY (PROJECT_ID) REFERENCES PROJECTS (PROJECT_ID);