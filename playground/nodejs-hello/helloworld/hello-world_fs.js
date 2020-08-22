var http = require('http');
var fs = require('fs');
var uc = require('upper-case');

http.createServer(function (req, res) {
  fs.readFile('demoFile1.html', function(err, data) {
    res.writeHead(200, {'Content-Type': 'text/html'});
    res.write(data + ' ' + uc('this is upper'));
    res.end();
  });

  fs.appendFile('mynewfile1.txt', 'Hello content!', function (err) {
    if (err) throw err;
    console.log('Saved!');
  });

}).listen(8080);