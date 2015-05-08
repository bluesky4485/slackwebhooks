package com.kolehank.slack;

import com.google.gson.JsonObject;

/**
 * Created by huangke on 2015/5/6.
 */
public class SlackField {
    private boolean shorten = false;
    private String title = null;
    private String value = null;

    public SlackField(String title) {
        this(title, null, false);
    }

    public SlackField(String title, String value) {
        this(title, value, false);
    }

    public SlackField(String title, String value, boolean isShort) {
        this.title = title;
        this.value = value;
        this.shorten = isShort;
    }

    public static SlackField instance(String title) {
        return new SlackField(title);
    }

    public static SlackField instance(String title, String value) {
        return new SlackField(title, value);
    }

    public SlackField title(String title) {
        setTitle(title);
        return this;
    }

    public SlackField value(String value) {
        setValue(value);
        return this;
    }

    public SlackField isShort(boolean isShort) {
        setShorten(isShort);
        return this;
    }

    public boolean isShorten() {
        return shorten;
    }


    public void setShorten(boolean shorten) {
        this.shorten = shorten;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JsonObject toJson() {
        JsonObject data = new JsonObject();
        data.addProperty("title", title);
        data.addProperty("value", value);
        data.addProperty("short", shorten);
        return data;
    }
}
