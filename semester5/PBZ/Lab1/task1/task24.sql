SELECT DISTINCT t1.group_code_number
FROM teacher_teaches_subjects_in_groups t1
WHERE t1.group_code_number IN (SELECT t2.group_code_number
						   FROM student_group t2
						   WHERE t2.group_name = 'АС-8' AND t1.subject_code_number NOT IN (SELECT t3.subject_code_number
														FROM teacher_teaches_subjects_in_groups t3
														WHERE t1.subject_code_number = t3.subject_code_number AND t1.group_code_number != t3.group_code_number))