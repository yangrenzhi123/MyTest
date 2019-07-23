import httplib2
h = httplib2.Http(".cache")
(resp_headers, content) = h.request("http://127.0.0.1:8080/", "GET")
print(resp_headers['date'])
print(content)