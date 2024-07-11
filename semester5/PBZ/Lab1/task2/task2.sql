SELECT DISTINCT t2.p_name, t2.status, t2.city, t3.d_name, t3.color, t3.size, t3.city, t4.pr_name, t4.city, t1.s1
FROM amount_of_parts_supplied t1
JOIN suppliers t2 ON t1.p1 = t2.p1
JOIN details t3 ON t1.d1 = t3.d1
JOIN projects t4 ON t1.pr1 = t4.pr1
WHERE t4.city = 'Москва'
ORDER BY t1.s1