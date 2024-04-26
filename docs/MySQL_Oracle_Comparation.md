# MySQL-15.1(MariaDB-10.3) Oracle 12c 

| DataType | MySQL/MariaDB  | Oracle   |
| --------------------- | --------- | ------ |
| | BIGINT | NUMBER(19, 0) |
| | BIT | RAW |
| | BLOB | BLOB, RAW |
| | CHAR | CHAR |
| | DATE | DATE |
| | DATETIME | DATE |
| | DECIMAL | FLOAT (24) |
| | DOUBLE | FLOAT (24) |
| | DOUBLE PRECISION | FLOAT (24) |
| | ENUM | VARCHAR2 |
| | FLOAT | FLOAT |
|  | LONGBLOB | BLOB, RAW |
|  | LONGTEXT | CLOB, RAW |
|  | MEDIUMBLOB | BLOB, RAW |
|  | MEDIUMINT | NUMBER(7, 0) |
|  | MEDIUMTEXT | CLOB, RAW |
|  | NUMERIC | NUMBER |
|  | REAL | FLOAT (24) |
|  | SET | VARCHAR2 |
|  | SMALLINT | NUMBER(5, 0) |
|  | TEXT | VARCHAR2, CLOB |
|  | TIME | DATE |
|  | TIMESTAMP | DATE |
|  | TINYBLOB | RAW |
|  | TINYINT | NUMBER(3, 0) |
|  | TINYTEXT | VARCHAR2 |
|  | VARCHAR | VARCHAR2, CLOB |
|  | YEAR | NUMBER |

| String Function | MySQL | Oracle |
| --------------------- | --------- | ------ |
| substr | SUBSTR(name,start[,length]) or substring | SELECT SUBSTR(name,1,5) FROM products |
| concat | concat(str1[,str2]...) |  |
| trim | trim(str) - ltrim(str) - rtrim(str) |  |
| length | length(str) |  |
|  | left(str, length) right(str, length) |  |
|  |  insert(str,start,length,insert)|  |
|  |  reverse(str) |  |
|  |  lower(str) |  |
|  |  upper(str) |  |
|  |  repeat(str,count) |  |
|  |  lpad(str,length,pad) |  |
|  |  rpad(str,length,pad) |  |

| Date Function | MySQL | Oracle |
| --------------------- | --------- | ------ |
|  | now() = sysdate() = current_timestamp() |  |
|  | sysdate() |  |
|  | curdate()=current_date |  |
|  | curtime()=current_time() |  |
|  | dayofmonth(date) |  |
|  | month |  |
|  | year |  |
|  | month |  |
|  | hour |  |
|  | minute |  |
|  | second |  |
|  | dayofweek | |
|  | quarter |  |
|  | dayofyear |  |
|  | week |  |
|  | last_day() |  |

| Alter Table | MySQL/MariaDB  | Oracle 12c |
| --------------- | --------- | -------- |
| Rename Table | RENAME table old_name TO new_name | RENAME old_name TO new_name |
| Add column | ALTER TABLE table_name ADD COLUMN column_name column_definition |ALTER TABLE table_name ADD column_name data_type constraint|
| Drop column | ALTER TABLE table_name Drop COLUMN column_name | ALTER TABLE table_name Drop column_name |
| Change password | SET PASSWORD FOR ‘username’@’hostname’ = PASSWORD(‘password’); | Use password command in a interactive way | 


| Group By | MySQL/MariaDB  | Oracle 12c |
| --------------- | --------- | -------- |
|  | The select list can refer to nonaggregated columns not named in the GROUP BY clause | All items in the select list not contained in an aggregate function must be included in the group by clause |
| Default Port | 3306 | 1521 |
| Licence | GPL | Commercial |
| Source Code, Language | C and C++ | C and C++ |
| Lock Mode | Table Lock | Row Lock |
| SQL Support | SQL | SQL and PL/SQL |
| XML Support | Not Supported | Supported |


| Query Differences | MySQL/MariaDB  | Oracle 12c |
| --------------- | --------- | -------- |
| Create DB | CREATE DATABASE customers | CREATE DATABASE customers |
| Create table| CREATE TABLE customer (cust_id int PRIMARY KEY,branch varchar(255),status varchar(255)); | CREATE TABLE customer (cust_id int, branch varchar(255), status varchar(255), CONSTRAINT customer_pk PRIMARY KEY (cust_id)); |
| Select | SELECT * FROM customer | SELECT * FROM customer |
| Insert | INSERT INTO customer(cust_id, branch, status) VALUES ('appl01', 'main', 'A') | INSERT INTO customer(cust_id, branch, status) VALUES ('appl01', 'main', 'A'); |
| Update | UPDATE customer SET branch="main" WHERE custage > 2 | UPDATE customer SET branch="main" WHERE custage > 2 |

| Other Differences | MySQL/MariaDB  | Oracle 12c |
| --------------- | --------- | -------- |
| Regex | REGEXP | REGEXP_LIKE |


# MySQL - Enum
    -- Must be a quoted String
    -- It is not recommended inserting number as Enumeration.
    -- Sort - Sorting is according to the index (declaration sequence)
    
# MySQL - Set
    -- Could have multiple values.