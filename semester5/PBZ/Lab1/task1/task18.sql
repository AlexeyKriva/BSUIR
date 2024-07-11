SELECT subject_code_number, person_number, group_code_number
FROM teacher_teaches_subjects_in_groups
WHERE person_number IN (SELECT person_number
					   FROM teacher
					   WHERE department = 'ЭВМ' AND specialty LIKE '%АСОИ%')