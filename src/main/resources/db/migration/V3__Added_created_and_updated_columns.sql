ALTER TABLE department
    DROP COLUMN IF EXISTS persist_date;
ALTER TABLE employee
    DROP COLUMN IF EXISTS persist_date;

ALTER TABLE department
    ADD IF NOT EXISTS created timestamp;
ALTER TABLE department
    ADD IF NOT EXISTS updated timestamp;

ALTER TABLE employee
    ADD IF NOT EXISTS created timestamp;
ALTER TABLE employee
    ADD IF NOT EXISTS updated timestamp;

UPDATE department
SET created = now()
WHERE department.created IS NULL;

UPDATE department
SET updated = now()
WHERE department.updated IS NULL;

UPDATE employee
SET created = now()
WHERE employee.created IS NULL;

UPDATE employee
SET updated = now()
WHERE employee.updated IS NULL;


