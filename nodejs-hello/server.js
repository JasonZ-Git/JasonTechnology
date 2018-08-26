var http = require('http');
var url = require('url');
var fs = require('fs');

http.createServer(function(request, response){
	var path = url.parse(request.url).pathname;

	console.log("Request for" + path);
 	
	fs.readFile(path.substr(1), function(err, data){
	  if(err){
	     console.log(err);
             response.writeHead(404,{'Context-Type':'text/html'});
           } else {
	     response.writeHead(200, {'Content-Type': 'text/html'});	
             response.write(data.toString());
	   }
	  response.end();
	})
}).listen(8081);

console.log('Server running at port 8081');
