# NodeJS Simple

## <strong>nvm</strong> - Node Version Manager
nvm <span style="color:red">install</span> node # install the latest node

nvm <span style="color:red">install</span> 14.7.0 # install a specific version of node

nvm <span style="color:red">uninstall</span> 8 # remove a version

nvm <span style="color:red">use</span> 8 # use a version

nvm <span style="color:red">ls</span>  # list installed node version

nvm <span style="color:red">ls-remote</span> # list remote node versions

## <strong>npm</strong> - Node Package Manager

{
  ""scripts"": {
    ""start-dev"": ""node lib/server-development"",
    ""start"": ""node lib/server-production""
  }
}

npm <span style="color:red">run</span> start-dev # start-dev is a task defined in package.json

npm <span style="color:red">install</span>

npm <span style="color:red">install</span> {package-name}

npm <span style="color:red">install</span> {package-name}>@<version>

npm <span style="color:red">install</span> -g  <package-name>

npm <span style="color:red">root -g</span> # glocal node module location

npm <span style="color:red">update</span> # Update all minor versions and also package.json

npm <span style="color:red">update</span> {package-name}

## update to major version
npm <span style="color:red">install</span> -g npm-check-updates<br>
ncu -u

npm <span style="color:red">list</span> # list installed packages including their depedencies

npm <span style="color:red">list</span> --depth=0 # list packages within the depth

npm <span style="color:red">list</span> {package-name}

npm <span style="color:red">view</span> {package-name} <span style="color:red">version</span>

npm <span style="color:red">view</span> {package-name} <span style="color:red">versions</span>

npm <span style="color:red">uninstall</span> {package-name}

npm <span style="color:red">uninstall -g</span> {package-name}

## Bebel - Transform JS code to ES5-compatible JS code

## nodemon - live load code change
npm install -g <span style="color:red">nodemon</span> # install ndoemon

<span style="color:red">nodemon</span> **.js

## require && import
requre is used with NodeJS - CommonJS

import is a ECMA-Script2017 standard

## process
