# KMM で Ktor Client を利用して Github API を呼び出す

# はじめに

KMM にて Github API を呼び出す処理を Ktor Client を利用して書いてみます。

# セットアップ

Ktor Client を利用するために commonMain には ktor-client-core、 androidMain には ktor-client-android、iosMain には ktor-client-ios を追加します。今回はあと API から返される JSON を kotlin-serialization でシリアライズするので plugin.serialization、ライブラリに ktor-client-json と ktor-client-serialization を追加します。 (ktor-client-serialization を追加したら kotlin-serialization の依存関係の追加は必要ないので注意してください追加する上手く動作しないことがあります)

```other
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.5.0"
    id("com.android.library")
}

kotlin {
		︙ 省略
    sourceSets {
        val kotlinVersion = "1.5.10"
        val ktorVersion = "1.5.4"

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion")
                api("io.ktor:ktor-client-core:$ktorVersion")
                api("io.ktor:ktor-client-json:$ktorVersion")
                api("io.ktor:ktor-client-serialization:$ktorVersion")
            }
        }
				︙ 省略
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
				︙ 省略
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
				︙ 省略
    }
}

︙ 省略
```

あと Ktor Client を利用するには coroutines が必要になります。coroutines は native-mt 版である必要があります。なので commonMain には kotlinx-coroutines-core:1.4.2-native-mt、androidMain には kotlinx-coroutines-android:1.4.2-native-mt を追加します。(Ktor Clientでは native-mt 版を利用するようになっているみたいで通常のバージョンだと上手く動かずエラーが発生するので注意が必要です。)

```other
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.5.0"
    id("com.android.library")
}

kotlin {
		︙ 省略
    sourceSets {
        val kotlinVersion = "1.5.10"
        val coroutinesVersion = "1.4.2-native-mt"
        val ktorVersion = "1.5.4"

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                api("io.ktor:ktor-client-core:$ktorVersion")
                api("io.ktor:ktor-client-json:$ktorVersion")
                api("io.ktor:ktor-client-serialization:$ktorVersion")
            }
        }
				︙ 省略
        val androidMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
				︙ 省略
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
				︙ 省略
    }
}

︙ 省略
```

これで KMM で Ktor Client は使えるようになります。セットアップ方法の詳細については以下のドキュメントにまとまっています。もし JSON を GSON でシリアライズしたいなど上記の条件と異なる方法で実装を行う場合には確認すると良いかなと思います。

[Getting started with a Ktor client | Ktor](https://ktor.io/docs/getting-started-ktor-client.html)

[Json | Ktor](https://ktor.io/docs/json.html)

# Ktor Client で Github API の GET gists を呼び出せるようにする

今回は Ktor Client の動作確認のため  Github API の GET gists を呼び出してみようと思います。（GET gists は指定したユーザーの Gists 一覧を取得するための API になります）

[Gists - GitHub Docs](https://docs.github.com/en/rest/reference/gists)

#### GET gists の DTO クラスを作成する

GET Gists を実行する API クライアントを作成する前準備として Gists の DTO クラスを作成します。GET gists を実行すると次のような JSON レスポンスが返ってきます。

```json
[
  {
    "url": "https://api.github.com/gists/aa5a315d61ae9438b18d",
    "forks_url": "https://api.github.com/gists/aa5a315d61ae9438b18d/forks",
    "commits_url": "https://api.github.com/gists/aa5a315d61ae9438b18d/commits",
    "id": "aa5a315d61ae9438b18d",
    "node_id": "MDQ6R2lzdGFhNWEzMTVkNjFhZTk0MzhiMThk",
    "git_pull_url": "https://gist.github.com/aa5a315d61ae9438b18d.git",
    "git_push_url": "https://gist.github.com/aa5a315d61ae9438b18d.git",
    "html_url": "https://gist.github.com/aa5a315d61ae9438b18d",
    "files": {
      "hello_world.rb": {
        "filename": "hello_world.rb",
        "type": "application/x-ruby",
        "language": "Ruby",
        "raw_url": "https://gist.githubusercontent.com/octocat/6cad326836d38bd3a7ae/raw/db9c55113504e46fa076e7df3a04ce592e2e86d8/hello_world.rb",
        "size": 167
      }
    },
    "public": true,
    "created_at": "2010-04-14T02:15:15Z",
    "updated_at": "2011-06-20T11:34:15Z",
    "description": "Hello World Examples",
    "comments": 0,
    "user": null,
    "comments_url": "https://api.github.com/gists/aa5a315d61ae9438b18d/comments/",
    "owner": {
      "login": "octocat",
      "id": 1,
      "node_id": "MDQ6VXNlcjE=",
      "avatar_url": "https://github.com/images/error/octocat_happy.gif",
      "gravatar_id": "",
      "url": "https://api.github.com/users/octocat",
      "html_url": "https://github.com/octocat",
      "followers_url": "https://api.github.com/users/octocat/followers",
      "following_url": "https://api.github.com/users/octocat/following{/other_user}",
      "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
      "organizations_url": "https://api.github.com/users/octocat/orgs",
      "repos_url": "https://api.github.com/users/octocat/repos",
      "events_url": "https://api.github.com/users/octocat/events{/privacy}",
      "received_events_url": "https://api.github.com/users/octocat/received_events",
      "type": "User",
      "site_admin": false
    },
    "truncated": false
  }
]
```

なのでこれらの JSON レスポンスを kotlin-seriazation でシリアライズできるように次の DTO クラスを生成しておきます。

```kotlin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GistDto(
    @SerialName("comments")
    val comments: Int,
    @SerialName("comments_url")
    val commentsUrl: String,
    @SerialName("commits_url")
    val commitsUrl: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("description")
    val description: String,
    @SerialName("forks_url")
    val forksUrl: String,
    @SerialName("git_pull_url")
    val gitPullUrl: String,
    @SerialName("git_push_url")
    val gitPushUrl: String,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("id")
    val id: String,
    @SerialName("node_id")
    val nodeId: String,
    @SerialName("owner")
    val owner: Owner,
    @SerialName("public")
    val `public`: Boolean,
    @SerialName("truncated")
    val truncated: Boolean,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("url")
    val url: String
)

@Serializable
data class Owner(
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("events_url")
    val eventsUrl: String,
    @SerialName("followers_url")
    val followersUrl: String,
    @SerialName("following_url")
    val followingUrl: String,
    @SerialName("gists_url")
    val gistsUrl: String,
    @SerialName("gravatar_id")
    val gravatarId: String,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("id")
    val id: Int,
    @SerialName("login")
    val login: String,
    @SerialName("node_id")
    val nodeId: String,
    @SerialName("organizations_url")
    val organizationsUrl: String,
    @SerialName("received_events_url")
    val receivedEventsUrl: String,
    @SerialName("repos_url")
    val reposUrl: String,
    @SerialName("site_admin")
    val siteAdmin: Boolean,
    @SerialName("starred_url")
    val starredUrl: String,
    @SerialName("subscriptions_url")
    val subscriptionsUrl: String,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
)
```

#### GET gists の API クライアントを作成する

Ktor Client ではプラットフォームごとに最適なネットワーク通信を実行できるように HttpClient にエンジンを設定できるような仕組みになっています。なのでプラットフォームごとに HttpClient のエンジンを変更できるように shared モジュールに expect キーワードをつけた HttpClientManager クラスを作成します。

```kotlin
expect class HttpClientManager() {
    val client: HttpClient
}
```

[Engines | Ktor](https://ktor.io/docs/http-client-engines.html#dependencies)

そしてAndroid 用に androidMain モジュールに actual キーワードをつけた HttpClientManager クラスを作成します。これで Android に最適なエンジンを使ったネットワーク通信をする HttpClient が取得できるようになります。

```kotlin
actual class HttpClientManager actual constructor() {
    actual val client: HttpClient = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
    }
}
```

iOS も同じです iosMain モジュールに actual キーワードをつけた HttpClientManager クラスを作成します。これで iOS に最適なエンジンを使ったネットワーク通信をする HttpClient が取得できるようになります。

```kotlin
actual class HttpClientManager actual constructor() {
    actual val client: HttpClient = HttpClient(Ios) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
    }
}
```

これで各プラットフォームで使える HttpClient が取得できるようになったので、あとは以下のような GistApi クラスを作成すれば GET gists が呼び出せるようになります。

```kotlin
class GistApi(private val manager: HttpClientManager, private val baseUrl: String) {
    suspend fun getGists(): ArrayList<GistDto> {
        return manager.client.get("$baseUrl/gists")
    }
}
```

# 動作確認

それでは実際に動作するか確かめてみます。

**Android**

Android は Jetpack Compose を利用してボタンをクリックしたときに GET gists を呼び出すようにしてみます。

```kotlin
class MainActivity : AppCompatActivity() {
    private val gistApi = GistApi(HttpClientManager(), "https://api.github.com/users/kaleidot725")
    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var gistsText by remember { mutableStateOf("") }

            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    text = gistsText,
                    textAlign = TextAlign.Center
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    onClick = { scope.launch { gistsText = gistApi.getGists().toString() } }
                ) {
                    Text(text = "UPDATE")
                }
            }
        }
    }
}
```

Android ではネットワーク通信をする場合にはパーミッションを設定する必要があります。なので AndroidManifest.xml に android.permission.INTERNET のパーミッションを忘れずに記述しておきます。

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="jp.kaleidot725.githubclient.android">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="false"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

#### iOS

iOS でも Swift UI を利用してボタンをクリックしたときに GET gists を呼び出すようにしておきます。

```swift
struct ContentView: View {
    let gistApi : GistApi = GistApi(manager: HttpClientManager(), baseUrl: "https://api.github.com/users/kaleidot725")

    @State var gists: String = ""
    
    var body: some View {
        Text(gists)
        Button("UPDATE") {
            gistApi.getGists(
                completionHandler: { (array, error) in
                    if (array != nil) {
                        let gists = array!.compactMap { $0 as? GistDto }
                        let gistTexts = gists.compactMap { $0.description() }
                        self.gists = gistTexts.joined()
                    }
                }
            )
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
	ContentView()
	}
}
```

それでは実行してみます。すると Android と iOS の両方を GET gists のレスポンスを無事取得できました。

![de8fc20e394808a03ac7e7a8b6de3a9b.png](https://res.craft.do/user/full/3a21bd0e-fe7a-39aa-73ad-b52ef24b655b/4BC46E79-7E02-4126-BC86-7CF36C0A763E_2/de8fc20e394808a03ac7e7a8b6de3a9b.png)

# おわりに

KMM で Ktor Client を利用して Github API を呼び出してみました。Android のほうが Coroutines が使えることもあり API の呼び出しもスムーズに書けるのかなと思いました。iOS では Coroutines が使えないこともあり shared モジュールで定義した suspend func はそのまま呼び出せずに CompletionHandler を経由して呼び出す形に変換されるようです。というように iOS では Android で同じように書けないとか制約がありそうです。shared モジュールに記述するコードはこのあたりも考慮して実装を進めていく必要がありそうだなと感じました。

