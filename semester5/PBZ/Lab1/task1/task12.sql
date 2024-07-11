SELECT DISTINCT t1.person_number
FROM teacher_teaches_subjects_in_groups t1, student_group t2
WHERE t1.group_code_number = t2.group_code_number AND t2.specialty = 'ЭВМ'
