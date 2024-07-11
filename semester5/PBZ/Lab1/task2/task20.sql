SELECT DISTINCT t1.color
FROM details t1, amount_of_parts_supplied t2
WHERE t2.p1 = 'П1' AND t1.d1 = t2.d1