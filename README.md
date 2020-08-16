# Securing Keys

This is a sample project that demonstrates  how to secure keys and other important credentials in
android apps.

There are a lot of methods as discussed [here](https://guides.codepath.com/android/Storing-Secret-Keys-in-Android)
but based on my current requirements, i used the gradle variables approach

## Requirement

1. I have a private key `123abcd` that would be used to access a particular server api. I want to
   share  my app with the world, but that key is precious to me and if misused might cost me . So
   using gradle variables and custom properties files will allow me to share the app code without
   sharing the actual file having my key

2. This will also help in a case where i have different api keys for production and debug
   environment.

## How it works

Checkout `app/build.gradle` for more info .


1. we create a folder in project root say `secure` and add a properties file

```python
# secure/debug_creds.properties
KEY_USER = "This is a debug key"
```

2. then we access the variables from properties file  in app/build.gradle

```python

android {
    ...
    defaultConfig {
        ...
        def credsFile = rootProject.file("secure\\debug_creds.properties")
        def prop = new Properties()
        prop.load(new FileInputStream(credsFile))
        buildConfigField('String', 'KEY_USER', prop['KEY_USER']) // always use single quotes here
        ...
    }


```


3. then we access in the activity or anywhere else using the following code.

```kotlin
val key = BuildConfig.KEY_USER
```

4. if the keys are needed  to be hidden , we can simply add the line `secure/*` in a `.gitignore`
   file. this will let git know that the content in `secure` folders does not need to be pushed to
   repo

5. for different environments, we move the code to from point 2 to `buildTypes` tasks:

```java

   buildTypes {
        debug{

            def credsFile = rootProject.file("secure\\debug_creds.properties")
            def prop = new Properties()
            prop.load(new FileInputStream(credsFile))
            buildConfigField('String', 'KEY_USER', prop['KEY_USER']) // always use single quotes here

        }

        release {

            def credsFile = rootProject.file("secure\\release_creds.properties")
            def prop = new Properties()
            prop.load(new FileInputStream(credsFile))
            buildConfigField('String', 'KEY_USER', prop['KEY_USER']) // always use single quotes here


            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }


```

---