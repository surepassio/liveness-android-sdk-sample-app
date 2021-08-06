# Liveness Android SDK
Sample application for Liveness SDK

## Step to use the SDK as below mentioned:

#### 1. build.grade (project):

```kotlin
allprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven {
            url "https://maven.pkg.github.com/surepassio/liveness-android-sdk-sample-app"
            credentials {
                username = "GITHUB_USER_NAME"
                password = "GITHUB_PAT_TOKEN" //https://docs.github.com/en/github/authenticating-to-github/keeping-your-account-and-data-secure/creating-a-personal-access-token (Allow Package Install Permission)
            }
        }
    }
}
```

#### 2. build.grade (app):

- Incase of Verion 1, Add following:
```java
dependencies {
    ....
    'implementation 'io.surepass.sdk:liveness-android-sdk:1.0.8' // without Face Mask Detection
}
```
- Incase of Verion 2, Add following:

```java
android {
    ....
    aaptOptions {
        noCompress '.tflite'
    }
}
dependencies {
    ....
    'implementation 'io.surepass.sdk:liveness-android-sdk:2.0.8' // with Face Mask Detection
}
```
#### 3. Inside Application:

```kotlin
import io.surepass.livenessandroidsdk.ui.InitSDK

  class  MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //token is needed when using liveness sdk
        val token = "TOKEN"
        val intent = Intent(this, InitSDK::class.java)
        intent.putExtra("token",token)
        intent.putExtra("videoPlayBackDisable",true) // Optional (Default: false)

        startActivityForResult(intent , 10000)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            10000 -> {
                val response = data!!.getStringExtra("response");
                Log.d("CONSOLE", "response $response")
            }
        }
    }
}
```

### Handling the responses in callback

SurePass Liveness Android SDK returns following response depending on the status of process.

**Note:** Only the **200 SUCCESS** status sent to `onActivityResult` function represents successful process. Rest, all the other responses are dispatched to the `onActivityResult` function are errors.

- #### 200 SUCCESS

Returns HTTP status of 200 OK, when the Liveness process has been completed successfully.

```json
{
  "data": {
    "client_id": "CLIENT_ID"
  },
  "status_code": 200,
  "message_code": "SUCCESS",
  "message": "Successfully Completed",
  "success": true
}
```

- #### 401 UNAUTHORIZED ACCESS

```json
{
  "data": {
    "client_id": "CLIENT_ID"
  },
  "status_code": 401,
  "message_code": "UNAUTH_ACCESS",
  "message": "Invalid Token",
  "success": false
}
```

- #### 403 MAXIMUM RETRY REACHED (TOKEN EXPIRED)

```json
{
  "data": {
    "client_id": "CLIENT_ID"
  },
  "status_code": 403,
  "message_code": "TOKEN_EXPIRED",
  "message": "Token Expired",
  "success": false
}
```

- #### 433 SESSION CLOSED BY USER

```json
{
  "data": {
    "client_id": "CLIENT_ID"
  },
  "status_code": 433,
  "message_code": "SDK_CLOSED",
  "message": "User closed the SDK before process completed",
  "success": false
}
```

- #### 500 INTERNAL SERVER ERROR

```json
{
  "data": {
    "client_id": "CLIENT_ID"
  },
  "status_code": 500,
  "message_code": "INT_SERVER_ERROR",
  "message": "Something went wrong.Try again later",
  "success": false
}
```

- #### 501 TIMEOUT ERROR

```json
{
  "data": {
    "client_id": "CLIENT_ID"
  },
  "status_code": 501,
  "message_code": "TIMEOUT_ERROR",
  "message": "Session Timeout Occurred",
  "success": false
}
```

### Custom Language Support (optional)
  The SDK language can be customized by overriding the string in main `strings.xml`. At the moment, we support and maintain translations for English (default) only.

  - The keys can be found in [`io/surepass/sdk/liveness-android-sdk/app/src/main/res/values/strings.xml`](/io/surepass/sdk/liveness-android-sdk/app/src/main/res/values/strings.xml). They can be passed as a string in the main application `strings.xml` to override.

