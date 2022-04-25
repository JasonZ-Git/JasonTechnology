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

$ npm list -g --depth 0 # list global packages with depth

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
```JS
node app.js --name=joe
const args = require('minimist')(process.argv.slice(2))
args['name'] //joe
```

## console
```JS
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

## setTimeout & clearTimeout
```JS
setTimeout(() => {
  // runs after 2 seconds
}, 2000)


# setTimeout with function parameters
const myFunction = (firstParam, secondParam) => {
  // do something
}
// runs after 2 seconds
setTimeout(myFunction, 2000, firstParam, secondParam);


# clear time out
const id = setTimeout(() => {
  // should run after 2 seconds
}, 2000)
// I changed my mind
clearTimeout(id)

```

## setIntervals & clearInterval - runs every * seconds
```JS
setInterval(() => {
  // runs every 2 seconds
}, 2000)

# clearInterval
const id = setInterval(() => {
  // runs every 2 seconds
}, 2000)

clearInterval(id)

```

## callback - classic way to asynchronous programming - There is callback hell problem
```JS
window.addEventListener('load', () => {
  //window loaded
  //do what you want
})
```

## Promise - Introduced in ES2015
```JS
let done = true;
const isDoneYet = new Promise((resolve, reject) => {
  if (done) {
    const workDone = 'Here is the thing I build';
    resolve(workDone);
  } else {
    const why = 'Still working on something else'
    reject(why);
  }
});

const checkIfDone = () => {
  isDoneyet.then(okay =>{
    console.log(okay)
  }).catch(err => {
    console.error(err)
  })
}

checkIfDone();
```

## Promisifying - turn a callback to a function
```JS
const fs = require('fs')
const getFile = (fileName) => {
  return new Promise((resolve, reject) => {
    fs.readFile(fileName, (err, data) => {
      if (err) {
        reject(err)  // calling `reject` will cause the promise to fail with or without the error passed as an argument
        return        // and we don't want to go any further
      }
      resolve(data)
    })
  })
}
getFile('/etc/passwd')
.then(data => console.log(data))
.catch(err => console.error(err))

``` 

## Orchestrating promises
```JS
const promose1 = new Promise(...)
const promose2 = new Promise(...

Promise.all([promise1, promise2]).then(...).catch(...);
Promise.race([promise1, promise2]).then(...).catch(...);
Promise.any([promise1, promise2]).then(...).catch(...);
```

## async/await - Since ES2017
As Promise looks like they are synchronised, but they are actually asynchronised.
```JS
Beofore
const aFunction = () => {
  return Promise.resolve('test')
}

After
const aFunction = async () => {
  return 'test'
}
```

## event Emitter
```JS
const EventEmitter = require('events')
const eventEmitter = new EventEmitter()
eventEmitter.on('start', ()=> {
  ...
})

eventEmitter.emit('start');
```

## Emitter With Parameter
```JS
eventEmitter.on('start', (start, end) => {
  console.log(`started from ${start} to ${end}`)
})

eventEmitter.emit('start', 1, 100)

```

## Create HTTP Server
```JS
const http = require('http')

const port = process.env.PORT || 3000

const server = http.createServer((req, res) => {
  res.statusCode = 200
  res.setHeader('Content-Type', 'text/html')
  res.end('<h1>Hello, World!</h1>')
})

server.listen(port, () => {
  console.log(`Server running at port ${port}`)
})
```

## Perform Http Request using axios(It is eaisier than the built in https module)
```JS
const axios = require('axios')

axios
  .get('https://example.com/todos')
  .then(res => {
    console.log(`statusCode: ${res.status}`)
    console.log(res)
  })
  .catch(error => {
    console.error(error)
  })
```

## filesystem - open file
```JS
const fs = require('fs')

fs.open('/Users/joe/test.txt', 'r', (err, fd) => {
  //fd is our file descriptor
})
```
or
```JS
const fs = require('fs')

try {
  const fd = fs.openSync('/Users/joe/test.txt', 'r')
} catch (err) {
  console.error(err)
}
...
fs.close();
```

## filesystem - status (property)
```JS
const fs = require('fs')
fs.stat('/Users/joe/test.txt', (err, stats) => {
  if (err) {
    console.error(err)
    return
  }

  stats.isFile() //true
  stats.isDirectory() //false
  stats.isSymbolicLink() //false
  stats.size //1024000 //= 1MB
})
```

## filesystem - path
```JS
const path = require('path')
const notes = '/home/jason/notes.txt'

path.dirname(notes) // /home/jason
path.basename(notes) // notes.txt
path.extname(notes) // .txt
path.basename(notes, path.extname(notes)) //notes

const name = 'jason'
path.join('/', 'home', name, 'notes.txt') // /home/jason/notes.txt'

path.resolve('joe.txt') // /home/jason/joe.txt if run from jason folder
path.resolve('tmp', 'joe.txt') // /home/jason/tmp/joe.txt if run from my home folder
path.resolve('/etc', 'joe.txt') // /etc/joe.txt
```

## filesystem - read file(sync)
```JS
const fs = require('fs')

fs.readFile('/Users/joe/test.txt', 'utf8' , (err, data) => {
  if (err) {
    console.error(err)
    return
  }
  console.log(data)
})

...

try {
  const data = fs.readFileSync('/Users/joe/test.txt', 'utf8')
  console.log(data)
} catch (err) {
  console.error(err)
}

```

## filesystem - write file (sync)
```JS
const fs = require('fs')

const content = 'Some content!'

fs.writeFile('/Users/joe/test.txt', content, err => {
  if (err) {
    console.error(err)
    return
  }
  //file written successfully
})

...
const content = 'Some content!'
try {
  fs.writeFileSync('/Users/joe/test.txt', content)
  //file written successfully
} catch (err) {
  console.error(err)
}
```
