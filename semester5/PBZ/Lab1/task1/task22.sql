SELECT subject_code_number
FROM teacher_teaches_subjects_in_groups
WHERE group_code_number IN (SELECT group_code_number
						   FROM student_group
						   WHERE group_name = 'АС-8')