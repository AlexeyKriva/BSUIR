SELECT DISTINCT surname
FROM teacher t1, teacher_teaches_subjects_in_groups t2
WHERE t1.person_number = t2.person_number AND t2.room_number = 210