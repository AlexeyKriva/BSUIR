SELECT DISTINCT t1.group_code_number, t2.group_code_number
FROM student_group t1, student_group t2 
WHERE t1.specialty = t2.specialty AND t1.group_code_number != t2.group_code_number AND t1.group_code_number > t2.group_code_number