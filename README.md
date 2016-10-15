# AndroidSDK

# Step 1 : 
Open your project build.gradle (the root one) and add the following lines in repositories section
```
buildscript {
    repositories {
        jcenter()
        maven {
            url  "http://dl.bintray.com/himanshugupta/mindtickle"
        }
    }
    ...
    ...
}

allprojects {
    repositories {
        jcenter()
        maven {
            url  "http://dl.bintray.com/himanshugupta/mindtickle"
        }
    }
}
```
# Step 2 :
Go to your app's build.gralde and include mindtickle sdk as dependency
```
dependencies {
    ...
    ...
    compile 'com.mindtickle.integrations:android-sdk:0.0.4'
    
}
```
Also, add packagingOptions block as shown below in "android" block of same build.gradle
```
android {
    ...
    packagingOptions {
        exclude 'META-INF/LICENSE'
    }
}
```
# Step 3:
In you custom Application class, add MinTickle.initialize() as shown below; so that it matches the following:
````
public class CustomApplicationClass extends Application {

@Override
    public void onCreate() {
        super.onCreate();
        MindTickle.initialize(
                getApplicationContext(),
                <learning_site>,
                <secret_key>);
    }
}
```
For this you need 
 1. learning site : This is the mindtickle login page domain, For ex, if your learners login to Mindtickle using domain pathshala.mindtickle.com then learning_site is pathshala.mindtickle.com
 2. secret_key : This is available in account settings on the admin dashboard of MindTickle
 
 # Step 4:
 
 Set the user's email in your app as below
 ```
 MindTickle.setUserEmail(<user_email>);
 ```
 Note : Make sure this email is same as the email of the user on MindTickle Platform
 
 # Step 5:
 
 Open MindTickle using this code
 
 ```
MindTickle.openMindTickle();

 ```


