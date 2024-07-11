SELECT DISTINCT person_number
FROM teacher_teaches_subjects_in_groups
WHERE group_code_number IN (SELECT group_code_number
					FROM student_group
					WHERE group_name = 'Э-15' AND person_number NOT IN (SELECT person_number
																   FROM teacher_teaches_subjects_in_groups
																   WHERE subject_code_number = '12П'))
