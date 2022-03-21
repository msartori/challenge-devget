SELECT t4.origin,
       t4.destination,
       MIN(weight) -MIN(cost) scale,
       MIN(cost) cost
FROM (SELECT t1.origin,
             t1.destination,
             t1.cost AS cost,
             t1.cost + 0 AS weight
      FROM flights t1
      UNION ALL
      SELECT t1.origin,
             t2.destination,
             t1.cost + t2.cost AS cost,
             t1.cost + t2.cost + 1 weight
      FROM flights t1
        JOIN flights t2 ON t1.destination = t2.origin
      UNION ALL
      SELECT t1.origin,
             t3.destination,
             t1.cost + t2.cost + t3.cost AS cost,
             t1.cost + t2.cost + t3.cost + 2 weight
      FROM flights t1
        JOIN flights t2 ON t1.destination = t2.origin
        JOIN flights t3 ON t2.destination = t3.origin) t4
GROUP BY t4.origin,
         t4.destination
ORDER BY origin, destination;