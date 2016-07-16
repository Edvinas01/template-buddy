[![Build Status](https://travis-ci.org/Edvinas01/template-buddy.svg?branch=master)](https://travis-ci.org/Edvinas01/template-buddy)
[![Coverage Status](https://coveralls.io/repos/github/Edvinas01/template-buddy/badge.svg?branch=master)](https://coveralls.io/github/Edvinas01/template-buddy?branch=master)

## TemplateBuddy
TemplateBuddy provides additional configuration features _(work in progress)_ and a builder which uses fluent api for Spring `RestTemplate` class.

## Usage
First create a `TemplateBuddy` bean somewhere in your Spring application.
```java
@Configuration
public class TemplateBuddyConfiguration {

    @Bean
    public TemplateBuddy templateBuddy() {
        return new TemplateBuddy();
    }
}
```

Then in your service classes, reuse the created instance by injecting the bean.
```java
@Autowired
private TemplateBuddy templateBuddy;
```

You can use the fluent api of the `TemplateBuddy`.
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

Or use it as a simple `RestTemplate`.
```java
public String get() {
    return templateBuddy
            .getForObject("http://my-url.com?param1={val1}&param2={val2}",
                    String.class, "query", "params");
}
```