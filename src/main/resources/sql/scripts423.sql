SELECT student.name, student.age
FROM STUDENT
INNER JOIN FACULTY ON student.faculty_id = faculty.faculty_id;

SELECT student.name
FROM STUDENT
INNER JOIN AVATAR ON student.avatar_avatar_id = avatar_id;