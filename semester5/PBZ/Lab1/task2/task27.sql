SELECT t1.p1
FROM amount_of_parts_supplied t1
WHERE t1.d1 = 'Д1' AND t1.s1 > (SELECT AVG(t2.s1)
							   FROM amount_of_parts_supplied t2
							   WHERE t1.pr1 = t2.pr1 AND t1.d1 = t2.d1) 