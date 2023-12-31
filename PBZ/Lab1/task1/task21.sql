SELECT specialty
FROM student_group
WHERE group_code_number IN (SELECT group_code_number
						   FROM teacher_teaches_subjects_in_groups
						   WHERE person_number IN (SELECT person_number
												  FROM teacher
												  WHERE department = 'АСУ'))