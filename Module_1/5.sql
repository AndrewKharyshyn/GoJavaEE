SELECT
  companies.COMPANY_NAME,
  customers.CUSTOMER_NAME,
  SUM(cost) AS LOWEST_INCOME
FROM companies
  INNER JOIN company_project ON companies.COMPANY_ID = company_project.COMPANY_ID
  INNER JOIN customer_project ON company_project.PROJECT_ID = customer_project.PROJECT_ID
  INNER JOIN customers ON customer_project.CUSTOMER_ID = customers.CUSTOMER_ID
  INNER JOIN projects ON customer_project.PROJECT_ID = projects.PROJECT_ID

GROUP BY companies.COMPANY_ID, customers.CUSTOMER_ID
HAVING SUM(COST) <= ALL (SELECT SUM(cost)
                         FROM PROJECTS
                           INNER JOIN customer_project ON customer_project.PROJECT_ID = projects.PROJECT_ID
                           INNER JOIN company_project ON company_project.PROJECT_ID = projects.PROJECT_ID
                         WHERE COMPANY_ID = COMPANIES.COMPANY_ID
                         GROUP BY customer_project.CUSTOMER_ID);