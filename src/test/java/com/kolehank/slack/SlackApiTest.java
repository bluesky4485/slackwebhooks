package com.kolehank.slack;

import org.junit.Test;

public class SlackApiTest {

    @Test
    public void testCall() throws Exception {
        SlackApi api = new SlackApi("incomingwebhooksurl");
        api.call(new SlackMessage("my test message"));

        api.call(new SlackMessage("huangke", "my test message"));

        api.call(new SlackMessage("#general", null, "my test message"));

        api.call(new SlackMessage("#general", "黄科", "my test message"));
        api.call(SlackMessage.instance("#general", "黄科", "my test message").icon("ghost"));

    }
}