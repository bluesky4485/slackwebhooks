# slackwebhooks
按照api对slack incomingwebhooks 进行简单的封装，主要是发送消息到slack。

针对slackMessage的使用分两种形式，一种使用构造函数创建对象，set其他值；另外一种是直接调用设置值。
示例代码：

```java
SlackApi api = new SlackApi("incomingwebhooksurl");
api.call(new SlackMessage("my test message"));

api.call(new SlackMessage("huangke", "my test message"));
api.call(new SlackMessage("#general", null, "my test message"));
api.call(new SlackMessage("#general", "黄科", "my test message"));

api.call(SlackMessage.instance("#general", "黄科", "my test message").icon("ghost"));
```

另外，代码中直接使用HttpURLConnection发送http请求，测试代码中有使用httpclient发送请求的示例，可以自行更改。
