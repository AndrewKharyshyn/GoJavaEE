SELECT AVG(SALARY) AS DEVELOPER_AVERAGE_SALARY
FROM DEVELOPERS
  INNER JOIN DEVELOPER_PROJECT ON DEVELOPER_PROJECT.DEVELOPER_ID = DEVELOPERS.DEVELOPER_ID
WHERE PROJECT_ID IN (SELECT PROJECT_ID
                     FROM PROJECTS
                     WHERE COST = (SELECT MIN(COST)
                                   FROM PROJECTS));