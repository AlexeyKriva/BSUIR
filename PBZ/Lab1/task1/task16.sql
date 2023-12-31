SELECT *
FROM subject
WHERE subject_code_number NOT IN (SELECT subject_code_number
								 FROM teacher_teaches_subjects_in_groups
								 WHERE group_code_number NOT IN (SELECT group_code_number
																FROM student_group
																WHERE group_name != 'М-6'))