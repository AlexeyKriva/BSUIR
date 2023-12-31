SELECT *
FROM subject 
WHERE subject_code_number NOT IN (SELECT subject_code_number
							 FROM teacher_teaches_subjects_in_groups
							 WHERE person_number = '221Л')