# NodeJS Simple

## <mark>NVM</mark> - Node Version Manager
```BASH
$ nvm install node # install the latest node

$ nvm install 14.7.0 # install a specific version of node

$ nvm uninstall 8 # remove a version

$ nvm use 8 # use a version

$ nvm ls  # list installed node version

$ nvm ls-remote # list remote node versions
```

## <mark>NPM</mark> - Node Package Manager
### <mark> install </mark> and <mark> update</mark>
```BASH
$ npm install

$ npm install {package-name}

$ npm install {package-name}>@<version>

$ npm install -g  <package-name>

$ npm root -g # glocal node module location

$ npm update # Update all minor versions and also package.json

$ npm update {package-name}
```

### Update to major version
```BASH
$ npm install -g npm-check-updates
$ ncu -u
```

### View packaage(s)
```BASH
$ npm list # list installed packages including their depedencies

$ npm list --depth=0 # list packages within the depth

$ npm list {package-name}

$ npm view {package-name} version

$ npm view {package-name} versions
```

### Uninstall package
```BASH
$ npm uninstall {package-name}

$ npm uninstall -g {package-name}
```

## Bebel - Transform JS code to ES5-compatible JS code
????

## nodemon - live load code change
```BASH
$ npm install -g nodemon
```

```JS
nodemon **.js
```

## run a task 
```JSON
{
  "scripts": {
    "watch": "webpack --watch --progress --colors --config webpack.conf.js",
    "dev": "webpack --progress --colors --config webpack.conf.js",
    "prod": "NODE_ENV=production webpack -p --config webpack.conf.js",
  }
}
```

```BASH
$ npm run watch
$ npm run dev
$ npm run prod
```

## require && import
requre is used with NodeJS - CommonJS

import is a ECMA-Script2017 standard

## process
```JS
process.exit(1); // exit the programme

process.kill(process.pid, 'SIGTERM')
```

## commandline argument
node app.js joe // pass argument 'joe'
const args = process.argv.slice(2)
args[0] // it is 'joe'

### process argument using <mark>minimist</mark>
node app.js --name=joe
const args = require('minimist')(process.argv.slice(2))
args['name'] //joe


## console
```js
const x = 'x'
const y = 'y'
console.log(x, y)

console.log('My %s has %d ears', 'cat', 2)
```

console time function
```JS
console.time('doSomething');
...
console.timeEnd('doSomething');
```

## chalk - colorful output
```
$ npm install chalk
```

```JS
const chalk = require('chalk')
console.log(chalk.yellow('hi!'))
```

## progress
```
$ npm install progress
```
```JS
const ProgressBar = require('progress')

const bar = new ProgressBar(':bar', { total: 10 })
const timer = setInterval(() => {
  bar.tick()
  if (bar.complete) {
    clearInterval(timer)
  }
}, 100)

```

## readline from input
```JS
const readline = require('readline').createInterface({
  input: process.stdin,
  output: process.stdout
})

readline.question(`What's your name?`, name => {
  console.log(`Hi ${name}!`)
  readline.close()
})
```

more complex example using <mark>inquirer</mark>
```
$ npm install inquirer
```
```JS
const inquirer = require('inquirer')

var questions = [
  {
    type: 'input',
    name: 'name',
    message: "What's your name?"
  }
]

inquirer.prompt(questions).then(answers => {
  console.log(`Hi ${answers['name']}!`)
})

```

## export an object and use it in another file
export it
```JS
const car = {
  brand: 'Ford',
  model: 'Fiesta'
}

module.exports = car
```
use it
```JS
const items = require('./items')
const car = items.car

or  
const car = require('./items').car

or 
const { car } = require('./items')
```

