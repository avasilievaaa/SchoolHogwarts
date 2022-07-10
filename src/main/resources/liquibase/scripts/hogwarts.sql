--liquibase formatted sql

--changeset avasilievaaa: 1
CREATE INDEX student_idx ON student (name);

--changeset avasilievaaa: 2
CREATE INDEX faculty_idx ON faculty (name, color);