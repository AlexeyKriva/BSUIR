SELECT DISTINCT t1.p1
FROM amount_of_parts_supplied t1
WHERE t1.p1 NOT IN (SELECT t2.p1
			       FROM amount_of_parts_supplied t2
				   WHERE t1.d1 != t2.d1 AND t1.p1 = t2.p1 AND t1.pr1 != t2.pr1)