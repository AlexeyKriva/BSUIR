SELECT DISTINCT t1.d1
FROM amount_of_parts_supplied t1
WHERE 320 < (SELECT AVG(t2.s1)
			  FROM amount_of_parts_supplied t2
			  WHERE t1.pr1 = t2.pr1)
ORDER BY t1.d1