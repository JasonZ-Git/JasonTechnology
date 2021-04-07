## Search file content
find -name '*.*' | xargs grep '<pattern>' .

## Change Pom Version
git grep 'version>9.9.0-SNAPSHOT' | awk '{print substr($1,0, length($1)-1)}' | sed -i 's/9.9.0-SNAPSHOT/9.9.109-SNAPSHOT/1'

## Git - Show changes file names of a commit
git diff-tree --no-commit-id --name-only -r {commit_id}

## AWK command
awk -F ',' '{print "insert into tscm_treaty_country(cntr_id, cntr_treaty_country_id, cntr_country_id, cntr_expense_type_id, cntr_rate, cntr_rate_used, cntr_effective_date, cntr_last_updated, cntr_last_updated_by) select "$1",", $2",", $3",", $4",", $6",", "\x27"substr($7,2,1)"\x27,","to_date(\x27"$8"\x27,\x27DD/MM/YYYY HH24:MI:SS\x27),", "to_date(\x27"$12"\x27,\x27DD/MM/YYYY HH24:MI:SS\x27),", "\x27"substr($13,2,17)"\x27 from dual where not exists (select 1 from tscm_treaty_country where cntr_id = ",$1");"}' Tax_Jason2.csv >Tax_Jason_output.sql

Input
150329,213,2,1045,,30,"N",01/01/2019 00:00:00,,,,19/03/2019 17:21:16,"kchee:BASE-222775",0,

Output
insert into tscm_treaty_country(cntr_id, cntr_treaty_country_id, cntr_country_id, cntr_expense_type_id, cntr_rate, cntr_rate_used, cntr_effective_date, cntr_last_updated, cntr_last_updated_by) select 150414, 202, 2, 1043, 30, 'N', to_date('01/01/2019 00:00:00','DD/MM/YYYY HH24:MI:SS'), to_date('19/03/2019 18:04:26','DD/MM/YYYY HH24:MI:SS'), 'kchee:BASE-222775' from dual where not exists (select 1 from tscm_treaty_country where cntr_id =  150414);

## Created a signed jar 
mvn -Pjnlp to generate the signed jar.

## Pack the jar 
pack200 ***.jar.pack.gz          ***.jar
