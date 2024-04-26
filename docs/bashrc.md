
alias ll='ls -al'

alias gdl='git diff HEAD^ --name-only'

alias gsps='git stash;git pull;git stash pop'

alias mcis='mvn -T 2C clean install -Dmaven.test.skip'

alias mcis-sonata='mvn -T 2C -pl "!sonata-sanity-check"  clean install -Dmaven.test.skip'

alias mciss='mvn -T 2C clean source:jar install -Dmaven.test.skip -Dskip.analyze=true'

alias mciss-sonata='mvn -T 2C -pl "!sonata-sanity-check" clean source:jar install -Dmaven.test.skip -Dskip.analyze=true'

alias mis='mvn -T 2C -pl "!sonata-sanity-check" source:jar install -Dmaven.test.skip'

alias mvn-sonata-full='mvn -T 4C -pl "!sonata-sanity-check" clean install -U'

alias mvnformat='mvn formatter:format -o'

alias mtest='mvn -T 2C test -P unit-test '

alias npp='/c/Program\ Files\ \(x86\)/Notepad++/notepad++.exe -multiInst'

alias uedit='/c/Program\ Files\ \(x86\)/IDM\ Computer\ Solutions/UltraEdit/Uedit32.exe'

alias home='cd /c/users/jzhang/'

alias base='cd /c/Work/base'

alias client='cd /c/Work/client'

alias deploy_workflow_full='cd /c/Work/base/sqlb; mvn clean install; cd ../core-framework; mvn clean install; cd workflow; mvn install -P pkg-ear,local -pl workflow-package/workflow-ear'

alias deploy_workflow="base; cd workflow; mvn install -P pkg-ear,local -pl workflow-package/workflow-ear"

alias web='cd /c/Work/sonata-web'

alias api='cd /c/Work/sonata-api'

alias jboss="cd /c/Applications/wildfly-16.0.0.Final_Base/ "

alias jboss_client="cd /c/Applications/wildfly-16.0.0.Final_Client/ "

alias start_jboss="/c/Applications/wildfly-16.0.0.Final_Base/bin/standalone.sh"

alias start_client_jboss="/c/Applications/wildfly-16.0.0.Final_Client/bin/standalone.sh"

alias notepad="/c/Program\ Files/Notepad++/notepad++.exe"

alias note="/c/Program\ Files/Notepad++/notepad++.exe"

alias jason='cd /c/Work/jason-technology/'

alias jason_mode='mv /c/users/jzhang/.m2/settings.xml /c/users/jzhang/.m2/settings.xml.bravura; cd /c/Work/jason-technology'

alias work_mode='mv /c/users/jzhang/.m2/settings.xml.bravura /c/users/jzhang/.m2/settings.xml; cd /c/Work/base'

alias sqlb_base='cp /c/Workspace/Base/sqlb.properties /c/Work/base/sonata-conf/target/classes/; cp /c/WorkSpace/Base/sqlb.properties /c/Work/base/sonata-rda/target/classes/'

alias sqlb_client='cp /c/Workspace/Client/sqlb.properties /c/Work/client/sonata-conf/target/classes/'

alias installer_dev='cp /c/Users/jzhang/bravurasolutions/overrides_dev.properties /c/Users/jzhang/bravurasolutions/overrides.properties'
alias installer_prod='cp /c/Users/jzhang/bravurasolutions/overrides_prod.properties /c/Users/jzhang/bravurasolutions/overrides.properties' 

alias chrome='/c/Program\ Files\ \(x86\)/Google/Chrome/Application/chrome.exe'

alias jshell='/c/Program\ Files/Java/jdk-11.0.3.7-hotspot/bin/jshell.exe'

alias jasonvm='echo mevcdvdi01'

alias techm='echo mevwtapsonata08'

alias chrome_debug='chrome --remote-debugging-port=9222'

# This command are only used within cmder
alias split_h='bash --login -i -new_console:s1TV'
alias split_v='bash --login -i -new_console:s1TH'

