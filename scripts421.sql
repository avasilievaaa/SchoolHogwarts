ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK (age >= 16);

ALTER TABLE student
    ADD UNIQUE (name);

ALTER TABLE student
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE faculty
    ADD CONSTRAINT login_pass_unique UNIQUE (color, name);

ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20;