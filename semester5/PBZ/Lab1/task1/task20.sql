SELECT person_number
FROM teacher
WHERE department = 'ЭВМ' AND person_number IN (SELECT person_number
											  FROM teacher_teaches_subjects_in_groups
											  WHERE subject_code_number IN (SELECT t1.subject_code_number
																		   FROM subject t1, student_group t2
																		   WHERE POSITION(t1.specialty IN t2.specialty) > 0))