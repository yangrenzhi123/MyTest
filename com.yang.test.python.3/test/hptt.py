import httplib2
h = httplib2.Http(".cache")
(resp_headers, content) = h.request("https://www.baidu.com/", "GET")
print(resp_headers['connection'])