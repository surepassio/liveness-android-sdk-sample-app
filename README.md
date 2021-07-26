# liveness-android-sdk-sample-app
Sample application for Liveness SDK

Step to use the SDK below as well:

#### 1. build.grade (project):

```
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


```
dependencies {
    'implementation 'io.surepass.sdk:liveness-android-sdk:1.0.6'
}
```

#### 3. Inside Application:

```
import io.surepass.livenessandroidsdk.ui.InitSDK

  class  MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //token is needed when using liveness sdk
        val token = "TOKEN"
        val intent = Intent(this, InitSDK::class.java)
        intent.putExtra("token",token)

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
