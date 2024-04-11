-- liquibase formatted sql

-- changeset atimurovich:1
CREATE INDEX student_name_index ON student (name);

-- changeset atimurovich:2
CREATE INDEX faculty_cn_index ON faculty (name, color);
