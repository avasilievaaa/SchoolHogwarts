SELECT s.name, s.age, f.name, f.color
From student s
         inner join faculty f on f.id = s.faculty_id;

Select s.*
From student s
         inner JOIN avatar a on a.student_id = s.id and a.file_path is not null