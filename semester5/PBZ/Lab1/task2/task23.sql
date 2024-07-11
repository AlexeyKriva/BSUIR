SELECT DISTINCT t1.p1
FROM amount_of_parts_supplied t1
WHERE t1.d1 IN (SELECT t2.d1
			   FROM amount_of_parts_supplied t2
			   WHERE t1.p1 != t2.p1 AND t1.d1 = t2.d1 AND t2.p1 IN (SELECT t3.p1
																   FROM amount_of_parts_supplied t3, details t4
																   WHERE t3.d1 = t4.d1 AND t4.color = 'Красный'))