## Template Buddy [![Build Status](https://travis-ci.org/Edvinas01/template-buddy.svg?branch=master)](https://travis-ci.org/Edvinas01/template-buddy) [![Coverage Status](https://coveralls.io/repos/github/Edvinas01/template-buddy/badge.svg?branch=master)](https://coveralls.io/github/Edvinas01/template-buddy?branch=master)
Template Buddy provides additional configuration features _(work in progress)_ and a builder which uses fluent api for Spring `RestTemplate` class.

## Build
This library depends on [spring-boot-starter-web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web).
To build a runnable jar, navigate to project root directory and run the gradle `build` command using gradle wrapper.
```bash
./gradlew build
```

## Examples
First create a `TemplateBuddy` bean or an instance somewhere in your application.
```java
@Configuration
public class TemplateBuddyConfiguration {

    @Bean
    public TemplateBuddy templateBuddy() {
        return new TemplateBuddy();
    }
}
```

Reuse the created instance by injecting the bean.
```java
@Autowired
private TemplateBuddy templateBuddy;
```

Now you can use the fluent api of the `TemplateBuddy`.
```java
public String get() {
    return templateBuddy
            .fromUrl("http://my-url.com")
            .param("param1", "query")
            .param("param2", "params")
            .expect(String.class)
            .get();
}
```

Or use `RestTemplate` features.
```java
public String get() {
    return templateBuddy
            .getForObject("http://my-url.com?param1={val1}&param2={val2}",
                    String.class, "query", "params");
}
```
