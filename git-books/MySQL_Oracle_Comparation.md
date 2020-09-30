# MySQL-15.1(MariaDB-10.3) Oracle 12c 

| DataType | MySQL/MariaDB  | Oracle   |
| --------------------- | --------- | ------ |
|  |  |

| Group By | MySQL/MariaDB  | Oracle 12c |
| --------------- | --------- | -------- |
| SQL | Extended SQL | SQL 2003 standard |
|  | The select list can refer to nonaggregated columns not named in the GROUP BY clause | All items in the select list not contained in an aggregate function must be included in the group by clause |
| Default Port | 3306 | 1521 |
| Licence | GNU Licence | Commercial |
| Source Code, Language | C and C++ | C and C++ |
| Lock Mode | Table Lock | Row Lock | 
| SQL Support | SQL | SQL and PL/SQL |
| XML Support | Not Supported | Supported |


| Query Difference | MySQL/MariaDB  | Oracle 12c |
| --------------- | --------- | -------- |
| Create DB | CREATE DATABASE customers |CREATE DATABASE customers |
| Create table| CREATE TABLE customer (cust_id int PRIMARY KEY,branch varchar(255),status varchar(255)); | CREATE TABLE customer (cust_id int, branch varchar(255), status varchar(255), CONSTRAINT customer_pk PRIMARY KEY (cust_id)); |
| Select | SELECT * FROM customer | SELECT * FROM customer |
| Insert | INSERT INTO customer(cust_id, branch, status) VALUES ('appl01', 'main', 'A') | INSERT INTO customer(cust_id, branch, status) VALUES ('appl01', 'main', 'A'); |
| Update | UPDATE customer SET branch="main" WHERE custage > 2 | UPDATE customer SET branch="main" WHERE custage > 2 |