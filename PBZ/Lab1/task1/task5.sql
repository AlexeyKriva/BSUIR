/*Получить номер группы, в которой ведутся предметы преподавателем Фроловы*/
SELECT DISTINCT t1.group_code_number
FROM teacher_teaches_subjects_in_groups t1, teacher t2 WHERE t1.person_number = t2.person_number AND t2.surname = 'Фролов';