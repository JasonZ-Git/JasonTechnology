### Useful Commmand

## Search file content
find -name '*.*' | xargs grep '<pattern>' .

## Replace Pom Version for all files
git grep 'version>9.9.0-SNAPSHOT' | awk '{print substr($1,0, length($1)-1)}' | sed -i 's/9.9.0-SNAPSHOT/9.9.109-SNAPSHOT/1'

## AWK command
awk -F ',' '{print "$0 $1 $2"}' source.txt >output.txt

## MySQL Dump and restore data
mysqldump -ujason -p --single-transaction --hex-blob --routines a_data_name  "c:\temp\dump.sql"
mysql -ujason -p a_data_name -e "source c:\temp\dump.sql"

## Git - Show changes file names of a commit
git diff-tree --no-commit-id --name-only -r {commit_id}

## Created a signed jar 
mvn -Pjnlp to generate the signed jar.

## Pack the jar 
pack200 ***.jar.pack.gz          ***.jar
