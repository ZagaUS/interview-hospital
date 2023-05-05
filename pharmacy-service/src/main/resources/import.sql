-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


-- CREATE TABLE medication (id SERIAL PRIMARY KEY,
--     medication_name VARCHAR(255) NOT NULL,
--     dosage VARCHAR(255) NOT NULL,
--     stock NUMERIC(10,2) NOT NULL);

INSERT INTO medications (id,medication_name, dosage, stock) 
VALUES (1,'Aspirin', '100mg', 500);

INSERT INTO medications (id,medication_name, dosage, stock) 
VALUES (2,'Ibuprofen', '200mg', 750);

INSERT INTO medications (id,medication_name, dosage, stock) 
VALUES (3,'Acetaminophen', '500mg', 1000);

INSERT INTO medications (id,medication_name, dosage, stock) 
VALUES (50,'string', 'string', 500);