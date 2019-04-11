# JWeb: Java Http Connection

**JWeb** is a Java library for connecting to Http servers and fetch html or json data.

## Example
Fetch the [Wikipedia](https://en.wikipedia.org/wiki/Main_Page) homepage:

```java
Response response = JWeb.connect("https://en.wikipedia.org/wiki/Main_Page")
    .setMethod(Method.GET)// optional default is GET
    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
    //.setBody("username=name&password=pass") // set body
    .exec();
System.out.println(response.getStatusCode() + ":" + response.getBody());
```
## Open source
JWeb is an open source project distributed under the liberal MIT license. The source code is available at [GitHub](https://github.com/sadeghpro/JWeb).