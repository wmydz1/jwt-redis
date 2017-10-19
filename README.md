JWT token with redis!

# API

## Requests

### **GET** - /key

#### CURL

```sh
curl -X GET "http://localhost:8080/key"
```

### **POST** - /auth
Please replace to your key by http://localhost:8080/key
#### CURL 



```sh
curl -X POST "http://localhost:8080/auth\
?
&token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1jaGVuIiwianRpIjoiMDdhYzI5MTdjNjFkNGIzNzhhMjRhMjU5NDBmMGI2M2YifQ.HPz9zyDcLyUneinwN0K2EdHsGSP2IImAyASqHI2XkzRojlhe4Gu2MrckWeBulIj5rVeilY7GbuOZoaoOXv07ag"
```

#### Query Parameters
Please replace to your key by http://localhost:8080/key
- **token** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1jaGVuIiwianRpIjoiMDdhYzI5MTdjNjFkNGIzNzhhMjRhMjU5NDBmMGI2M2YifQ.HPz9zyDcLyUneinwN0K2EdHsGSP2IImAyASqHI2XkzRojlhe4Gu2MrckWeBulIj5rVeilY7GbuOZoaoOXv07ag"
  ],
  "default": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1jaGVuIiwianRpIjoiMDdhYzI5MTdjNjFkNGIzNzhhMjRhMjU5NDBmMGI2M2YifQ.HPz9zyDcLyUneinwN0K2EdHsGSP2IImAyASqHI2XkzRojlhe4Gu2MrckWeBulIj5rVeilY7GbuOZoaoOXv07ag"
}
```

### **POST** - /refresh
Please replace to your key by http://localhost:8080/key
#### CURL

```sh
curl -X POST "http://localhost:8080/refresh\
?
&token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1jaGVuIiwianRpIjoiMDdhYzI5MTdjNjFkNGIzNzhhMjRhMjU5NDBmMGI2M2YifQ.HPz9zyDcLyUneinwN0K2EdHsGSP2IImAyASqHI2XkzRojlhe4Gu2MrckWeBulIj5rVeilY7GbuOZoaoOXv07ag"
```

#### Query Parameters

- **token** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1jaGVuIiwianRpIjoiMDdhYzI5MTdjNjFkNGIzNzhhMjRhMjU5NDBmMGI2M2YifQ.HPz9zyDcLyUneinwN0K2EdHsGSP2IImAyASqHI2XkzRojlhe4Gu2MrckWeBulIj5rVeilY7GbuOZoaoOXv07ag"
  ],
  "default": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1jaGVuIiwianRpIjoiMDdhYzI5MTdjNjFkNGIzNzhhMjRhMjU5NDBmMGI2M2YifQ.HPz9zyDcLyUneinwN0K2EdHsGSP2IImAyASqHI2XkzRojlhe4Gu2MrckWeBulIj5rVeilY7GbuOZoaoOXv07ag"
}
```

## References



![diagram](http://7xir7h.com1.z0.glb.clouddn.com/jwt-diagram.png)