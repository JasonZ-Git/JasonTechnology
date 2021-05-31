### Useful Commmand

## Search file content
find -name '*.*' | xargs grep '<pattern>' .

## Replace Pom Version for all files
git grep 'version>9.9.0-SNAPSHOT' | awk '{print substr($1,0, length($1)-1)}' | sed -i 's/9.9.0-SNAPSHOT/9.9.109-SNAPSHOT/1'

## AWK command
awk -F ',' '{print "$0 $1 $2"}' source.txt >output.txt

## MYSQL DB Size
# {My SQL Data folder}\MySQL Server 5.7\Data, check the folder size

# Run the SQL
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
