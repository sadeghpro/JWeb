# JWeb: Java Http Connection [![](https://www.jitpack.io/v/sadeghpro/jweb.svg)](https://www.jitpack.io/#sadeghpro/jweb)

**JWeb** is a Java library for connecting to Http servers and fetch html or json data.


##Getting start

###Maven
Add jitpack to maven repositories:
```
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://www.jitpack.io</url>
	</repository>
</repositories>
```
Add the dependency:
```
<dependency>
    <groupId>com.github.sadeghpro</groupId>
    <artifactId>jweb</artifactId>
    <version>0.1.0</version>
</dependency>
```
### Gradle
Add this in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://www.jitpack.io' }
	}
}
```
and then in dependencies:
```
dependencies {
    implementation 'com.github.sadeghpro:jweb:0.1.0'
}
```


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
