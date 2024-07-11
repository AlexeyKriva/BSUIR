SELECT DISTINCT t1.p1 , t2.p1
FROM amount_of_parts_supplied t1
JOIN amount_of_parts_supplied t2 ON t1.d1 = t2.d1 AND t1.p1 < t2.p1
JOIN details t3 ON t1.d1 = t3.d1
JOIN details t4 ON t2.d1 = t4.d1
GROUP BY t1.p1, t2.p1
HAVING COUNT(*) = COUNT(t3.d1) AND COUNT(*) = COUNT(t4.d1);