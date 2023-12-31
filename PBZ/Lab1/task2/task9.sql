SELECT DISTINCT t1.d1
FROM amount_of_parts_supplied t1, suppliers t2
WHERE t1.p1 = t2.p1 AND t2.city = 'Москва'
ORDER BY t1.d1