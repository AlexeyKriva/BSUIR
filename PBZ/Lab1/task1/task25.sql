SELECT DISTINCT group_code_number
FROM student_group
WHERE group_code_number NOT IN (SELECT group_code_number
								 FROM teacher_teaches_subjects_in_groups
								 WHERE person_number = '430Л')