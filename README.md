# MetronAPI

Kotlin library for working with [Metron API](https://metron.cloud). Powered by [OkHttp](https://square.github.io/okhttp/), [Retrofit](https://square.github.io/retrofit/), [Gson](https://github.com/google/gson) and [RxJava](https://github.com/ReactiveX/RxJava)

## Dependency

To add this library as dependency, add this line into your `dependencies` build.gradle block:
```groovy
implementation 'com.github.AtsumeruDev:MetronAPI:-SNAPSHOT'
```

Also, make sure to add this repository into `repositories` block:
```groovy
maven { url 'https://jitpack.io' }
```

## Usage

### Initializing
Usage of library is very simple. First, initialize it (make sure to do it as soon as possible before making any Metron requests):
```kotlin
MetronApi.init(
    builder: OkHttpClient.Builder = OkHttpClient.Builder(), // Custom OkHttp client Builder
    userName: String, // Your Metron account username
    password: String, // Your Metron account password
    apiEndpoint: String = "https://metron.cloud/api/", // API Endpoint url. Always try to use default one
    userAgent: String = "MetronAPI Library", // API UserAgent. Set your app's name or use default one
    httpConnectTimeoutMs: Long = 15000, // Connection timeout in milliseconds
    httpReadTimeoutMs: Long = 25000, // Read timeout in milliseconds
    isDebug: Boolean = false // Debug mode or not. When true, library will log every request into logs 
)
```

Simple initializing method with defaults:
```kotlin
MetronApi.init(userName = {username}, password = {password})
```

### API Methods calling

After that, you can make calls to any of defined methods. For example, getting info about [Suicide Squad (2016)](https://metron.cloud/series/suicide-squad-2016/) serie (with id `3006`):

#### Async RXJava way:
```kotlin
MetronApi.getSerie(3006)
    .cache()
    .subscribeOn(Schedulers.newThread())
    .observeOn(Schedulers.io())
    .subscribe(
        serie -> {do on success},
        throwable -> {do on throwable}
);
```

#### Blocking way:
```kotlin
MetronApi.getSerie(3006).blockingGet()
```