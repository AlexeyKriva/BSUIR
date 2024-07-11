SELECT DISTINCT group_code_number
FROM teacher_teaches_subjects_in_groups
WHERE group_code_number IN (SELECT t1.group_code_number
							FROM student_group t1, teacher t2
							WHERE POSITION(t1.specialty IN t2.specialty) > 0)