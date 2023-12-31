--Получить номера предметов и названия предметов, которые ведет преподаватель Костин
SELECT distinct t1.subject_code_number, t1.subject_name
FROM subject t1, teacher t2, teacher_teaches_subjects_in_groups t3 WHERE t1.subject_code_number = t3.subject_code_number and t3.person_number = t2.person_number and t2.surname = 'Костин';