package com.kolehank.slack;

import com.google.common.base.Strings;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by huangke on 2015/5/6.
 */
public class SlackMessage {
    private List<SlackAttachment> attach = null;
    private String channel = null;
    private String icon = null;
    private JsonObject slackMessage = new JsonObject();

    private String text = null;
    private String username = null;

    public SlackMessage(String text) {
        this(null, null, text);
    }

    public SlackMessage(String username, String text) {
        this(null, username, text);
    }

    public SlackMessage(String channel, String username, String text) {
        if (!Strings.isNullOrEmpty(channel)) {
            this.channel = channel;
        }

        if (!Strings.isNullOrEmpty(username)) {
            this.username = username;
        }

        this.text = text;
    }

    public static SlackMessage instance(String text) {
        return new SlackMessage(text);
    }

    public static SlackMessage instance(String username, String text) {
        return new SlackMessage(username, text);
    }

    public static SlackMessage instance(String channel, String username, String text) {
        return new SlackMessage(channel, username, text);
    }

    /**
     * Convert to JSON
     *
     * @return JsonObject
     */
    public JsonObject prepare() {
        if (!Strings.isNullOrEmpty(channel)) {
            slackMessage.addProperty("channel", channel);
        }

        if (!Strings.isNullOrEmpty(username)) {
            slackMessage.addProperty("username", username);
        }

        if (!Strings.isNullOrEmpty(icon)) {
            if (icon.contains("http")) {
                slackMessage.addProperty("icon_url", icon);
            } else {
                slackMessage.addProperty("icon_emoji", icon);
            }
        }

        if (Strings.isNullOrEmpty(text)) {
            throw new IllegalArgumentException(
                    "Missing Text field @ SlackMessage");
        } else {
            slackMessage.addProperty("text", text);
        }

        if (attach != null && attach.size() > 0) {
            slackMessage.add("attachments", this.prepareAttach());
        }

        return slackMessage;
    }

    private JsonArray prepareAttach() {
        JsonArray attachs = new JsonArray();
        for (SlackAttachment attach : this.attach) {
            attachs.add(attach.toJson());
        }

        return attachs;
    }

    public SlackMessage removeAttachment(Integer index) {
        if (this.attach != null) {
            this.attach.remove(index);
        }

        return this;
    }

    public SlackMessage setAttachments(ArrayList<SlackAttachment> attach) {
        this.attach = attach;

        return this;
    }

    public SlackMessage channel(String channel) {
        if (!Strings.isNullOrEmpty(channel)) {
            this.channel = channel;
        }

        return this;
    }

    public SlackMessage icon(String icon) {
        if (!Strings.isNullOrEmpty(icon)) {
            this.icon = icon;
        }

        return this;
    }

    public SlackMessage text(String message) {
        if (!Strings.isNullOrEmpty(message)) {
            this.text = message;
        }

        return this;
    }

    public SlackMessage username(String username) {
        if (!Strings.isNullOrEmpty(username)) {
            this.username = username;
        }

        return this;
    }

    public SlackMessage attachments(SlackAttachment... attach) {
        if (this.attach == null) {
            this.attach = new ArrayList<SlackAttachment>(Arrays.asList(attach));
        } else
            this.attach.addAll(Arrays.asList(attach));

        return this;
    }

}
