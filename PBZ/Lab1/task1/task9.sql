SELECT DISTINCT t1.subject_name, t2.group_name
FROM subject t1, student_group t2, teacher_teaches_subjects_in_groups t3
WHERE t1.subject_code_number = t3.subject_code_number AND t2.group_code_number = t3.group_code_number AND t3.room_number >= 100 AND t3.room_number <= 200