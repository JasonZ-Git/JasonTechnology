# Useful Daily Commmand

## ssh a remote server by pass password
### 1. Generate s keys if not already existing
```BASH
$ ssh-keygen
```
### 2. Copy the key to remote host
```BASH
$ ssh-copy-id -i ~/.ssh/id_rsa.pub user@remote-host
```

##  Search file content
```BASH
$ find -name '*.*' | xargs grep '<pattern>' .
```

### Search directory with pattern for files
```BASH
$ find . -type d -name 'industries-fulfillment-orchestrator*' | xargs -I {} find {} -name ownership.yaml
```

### Use awk and sed to replace string in files
### Replace Pom Version for all files
```BASH
$ git grep 'version>9.9.0-SNAPSHOT' | awk '{print substr($1,0, length($1)-1)}' | sed -i 's/9.9.0-SNAPSHOT/9.9.109-SNAPSHOT/1'
```

### Replace org.apache.commons.lang to org.apache.commons.lang3 for all java files
```BASH
$ git grep 'org.apache.commons.lang.' -- '*.java' | awk -F ':' '\{print $1}' | xargs sed -i 's/org.apache.commons.lang./org.apache.commons.lang3./1'
```

### Git - compare a file changes in 2 commits
```BASH
git diff HEAD:{file with path} HEAD~1:{file with path}
```
### AWK command
```BASH
$ awk -F ',' '{print "$0 $1 $2"}' source.txt >output.txt
```
## Curl command with anthtication
```BASH
$ curl -i -H "Authorization:Basic Zmlyc3Q6JDQ3ODEl" -X GET http://192.168.1.89:8080/moonlight/v1/authenticate
```
## Set git branch in termimal (Ubuntu)
```BASH
parse_git_branch() {
     git branch 2> /dev/null | sed -e '/^[^*]/d' -e 's/* \(.*\)/ (\1)/'
}
export PS1="\u@\h \[\033[32m\]\w\[\033[33m\]\$(parse_git_branch)\[\033[00m\] $ "
```
## MYSQL DB Size
### {My SQL Data folder}\MySQL Server 5.7\Data, check the folder size

### Run the SQL
```SQL
SELECT table_schema "DB Name",
        ROUND(SUM(data_length + index_length) / 1024 / 1024, 1) "DB Size in MB" 
FROM information_schema.tables 
GROUP BY table_schema; 
```
### MySQL Dump and restore data
```BASH
$ mysqldump -ujason -p --single-transaction --hex-blob --routines a_data_name  "c:\temp\dump.sql"
$ mysql -ujason -p a_data_name -e "source c:\temp\dump.sql"
```
### MYSQL - Check constraint of a table
```SQL
SELECT COLUMN_NAME, CONSTRAINT_NAME, REFERENCED_COLUMN_NAME, REFERENCED_TABLE_NAME FROM information_schema.KEY_COLUMN_USAGE WHERE TABLE_NAME = 'your table name';
```
### MySQL - Drop a unique constraint
```SQL
alter table table_name drop index index_name
```
## Git - Show changes file names of a commit
```BASH
$ git diff-tree --no-commit-id --name-only -r {commit_id}
```
## Git - Beautify log 
```BASH
$ git config --global alias.lg "log --color --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit"
```

## Git - Show last 10 log
```BASH
$ git config --global alias.last "lg -10"
```

## Cool way to export Java_Home
```BASH
export JAVA_HOME="$(dirname $(dirname $(realpath $(which javac))))"
```

## Show git branch in console
```BASH
parse_git_branch() {
     git branch 2> /dev/null | sed -e '/^[^*]/d' -e 's/* \(.*\)/ (\1)/'
}
export PS1="\u@\h \[\033[32m\]\w\[\033[33m\]\$(parse_git_branch)\[\033[00m\] $ "
```

## format json in gedit
preferences->Plugins-> check 'External Tools'
Manager External Tools -> All languages -> add one:
 -- input - Current Docuemtn
 -- OUtput - Replace Current Document
 -- ShortCut Key - Ctrl + Shift + F
 -- Contents as follows:
```BASH
#!/usr/bin/env python3

import json
import sys

j = json.load(sys.stdin)
print (json.dumps(j, sort_keys=True, indent=2))
```


## Convert Video into images in Linux
```BASH
sudo install ffmpeg
ffmpeg -i IMG_6357.MOV image-%03d.png
ffmpeg -i IMG_6357.MOV -vf fps=1 image-%03d.png

```