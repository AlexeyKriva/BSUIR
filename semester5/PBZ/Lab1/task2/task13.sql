SELECT DISTINCT t1.pr1
FROM amount_of_parts_supplied t1, suppliers t2, projects t3
WHERE t1.p1 = t2.p1 AND t2.city != t3.city AND t1.pr1 = t3.pr1