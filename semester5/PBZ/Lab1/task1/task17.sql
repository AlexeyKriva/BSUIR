SELECT *
FROM teacher t1
WHERE post = 'Доцент' AND person_number IN (SELECT person_number
						  FROM teacher_teaches_subjects_in_groups t2
						  WHERE t1.person_number = t2.person_number AND (t2.group_code_number = '3Г' OR t2.group_code_number = '8Г'))