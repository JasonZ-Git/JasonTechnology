var http = require('http');
var urlUtil = require('url');
var dateTime = require('../helloworld/myModule');

http.createServer(function (req, res) {
  res.writeHead(200, {'Content-Type': 'text/html'});
  var urlText = urlUtil.parse(req.url, true).query;
  res.write(urlText.year + " " + urlText.month);
  res.end('\n Hello World!' + dateTime.myDateTime());
}).listen(8080);