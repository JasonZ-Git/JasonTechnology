# Useful Commmand

## ssh a remote server by pass password
### 1. Generate s keys if not already existing
 $ ssh-keygen
### 2. Copy the key to remote host
 $ ssh-copy-id -i ~/.ssh/id_rsa.pub user@remote-host


##  Search file content
find -name '*.*' | xargs grep '<pattern>' .

## Use awk and sed to replace string in files
### Replace Pom Version for all files
git grep 'version>9.9.0-SNAPSHOT' | awk '{print substr($1,0, length($1)-1)}' | sed -i 's/9.9.0-SNAPSHOT/9.9.109-SNAPSHOT/1'
### Replace org.apache.commons.lang to org.apache.commons.lang3 for all java files
git grep 'org.apache.commons.lang.' -- '*.java' | awk -F ':' '\{print $1}' | xargs sed -i 's/org.apache.commons.lang./org.apache.commons.lang3./1'

## AWK command
awk -F ',' '{print "$0 $1 $2"}' source.txt >output.txt

## MYSQL DB Size
### {My SQL Data folder}\MySQL Server 5.7\Data, check the folder size

### Run the SQL
SELECT table_schema "DB Name",
        ROUND(SUM(data_length + index_length) / 1024 / 1024, 1) "DB Size in MB" 
FROM information_schema.tables 
GROUP BY table_schema; 

## MySQL Dump and restore data
mysqldump -ujason -p --single-transaction --hex-blob --routines a_data_name  "c:\temp\dump.sql"
mysql -ujason -p a_data_name -e "source c:\temp\dump.sql"

## Git - Show changes file names of a commit
git diff-tree --no-commit-id --name-only -r {commit_id}

## Created a signed jar 
mvn -Pjnlp to generate the signed jar.

## Pack the jar 
pack200 ***.jar.pack.gz          ***.jar
