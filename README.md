AsynchronousWebService
======================

This is an example to create REST based asynchronous web-service. First you will have to enable the Async web-service in `web.xml` (See sttached):

```
	<context-param>
    	<param-name>resteasy.async.job.service.enabled</param-name>
    	<param-value>true</param-value>
	</context-param>
```

Create a REST based WS and deploy it, once done hit the app with `?asynch=true`

```

$ curl --dump-header header.txt http://testws-nmone.rhcloud.com/RESTexample/hello/text?asynch=true

```

Above command dumps the header information to a file named as `header.txt`. You can read the file from your favourite text editor. 

```

$ cat header.txt 
HTTP/1.1 202 Accepted
Date: Wed, 09 Apr 2014 08:04:42 GMT
Server: Apache-Coyote/1.1
Location: http://testws-nmone.rhcloud.com/RESTexample/asynch/jobs/1397030682758-5
Content-Length: 0
Content-Type: text/plain

```

Now since the sleep was for 10 mins.. so the response isn't generated yet. that is why if you curl to the job-id, you will get 202 response. [1] tells what 202 is.

```

$ curl -v http://testws-nmone.rhcloud.com/RESTexample/asynch/jobs/1397030682758-5
* Adding handle: conn: 0x2322db0
* Adding handle: send: 0
* Adding handle: recv: 0
* Curl_addHandleToPipeline: length: 1
* - Conn 0 (0x2322db0) send_pipe: 1, recv_pipe: 0
* About to connect() to testws-nmone.rhcloud.com port 80 (#0)
*   Trying 23.22.103.184...
* Connected to testws-nmone.rhcloud.com (23.22.103.184) port 80 (#0)
> GET /RESTexample/asynch/jobs/1397030682758-5 HTTP/1.1
> User-Agent: curl/7.32.0
> Host: testws-nmone.rhcloud.com
> Accept: */*
> 
< HTTP/1.1 202 Accepted
< Date: Wed, 09 Apr 2014 08:05:00 GMT
* Server Apache-Coyote/1.1 is not blacklisted
< Server: Apache-Coyote/1.1
< Content-Length: 0
< Content-Type: text/plain
< 
* Connection #0 to host testws-nmone.rhcloud.com left intact

```


After 10 minutes when you hit the above command again, you will get the response :

```

$ curl -v http://testws-nmone.rhcloud.com/RESTexample/asynch/jobs/1397030682758-5
* Adding handle: conn: 0x2199db0
* Adding handle: send: 0
* Adding handle: recv: 0
* Curl_addHandleToPipeline: length: 1
* - Conn 0 (0x2199db0) send_pipe: 1, recv_pipe: 0
* About to connect() to testws-nmone.rhcloud.com port 80 (#0)
*   Trying 23.22.103.184...
* Connected to testws-nmone.rhcloud.com (23.22.103.184) port 80 (#0)
> GET /RESTexample/asynch/jobs/1397030682758-5 HTTP/1.1
> User-Agent: curl/7.32.0
> Host: testws-nmone.rhcloud.com
> Accept: */*
> 
< HTTP/1.1 200 OK
< Date: Wed, 09 Apr 2014 08:19:26 GMT
* Server Apache-Coyote/1.1 is not blacklisted
< Server: Apache-Coyote/1.1
< Content-Type: text/plain
< Content-Length: 11
< Vary: Accept-Encoding
< 
* Connection #0 to host testws-nmone.rhcloud.com left intact
Hello There                                                                   <<<< See the response here.

```

[1] http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html
