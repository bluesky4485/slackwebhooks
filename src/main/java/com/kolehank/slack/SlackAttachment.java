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
public class SlackAttachment {
    private String fallback;
    private String text;
    private String pretext;
    private String color;
    private String title;
    private String titlelink;
    private String imageurl;
    private List<SlackField> fields;

    public SlackAttachment(String fallback) {
        this.fallback = fallback;
    }

    public static SlackAttachment instance(String fallback) {
        return new SlackAttachment(fallback);
    }

    public SlackAttachment addFields(SlackField... field) {
        if (this.fields == null) {
            this.fields = new ArrayList<SlackField>(Arrays.asList(field));
        }

        this.fields.addAll(Arrays.asList(field));

        return this;
    }

    private boolean isHex(String pair) {
        return pair.matches("^([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    }

    private JsonArray prepareFields() {
        JsonArray data = new JsonArray();
        for (SlackField field : fields) {
            data.add(field.toJson());
        }

        return data;
    }

    public SlackAttachment removeFields(Integer index) {
        if (this.fields != null) {
            this.fields.remove(index);
        }

        return this;
    }

    public SlackAttachment setColor(String color) {
        if (!Strings.isNullOrEmpty(color)) {
            if (color.charAt(0) == '#') {
                if (!isHex(color.substring(1))) {
                    throw new IllegalArgumentException(
                            "Invalid Hex Color @ SlackAttachment");
                }
            } else if (!color.matches("^(good|warning|danger)$")) {
                throw new IllegalArgumentException(
                        "Invalid PreDefined Color @ SlackAttachment");
            }
        }

        this.color = color;

        return this;
    }

    public SlackAttachment setFallback(String fallback) {
        this.fallback = fallback;

        return this;
    }

    public SlackAttachment setFields(ArrayList<SlackField> fields) {
        this.fields = fields;

        return this;
    }

    public SlackAttachment setPretext(String pretext) {
        this.pretext = pretext;

        return this;
    }

    public SlackAttachment setText(String text) {
        this.text = text;

        return this;
    }

    public String getTitle() {
        return title;
    }

    public SlackAttachment setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitlelink() {
        return titlelink;
    }

    public SlackAttachment setTitlelink(String titlelink) {
        this.titlelink = titlelink;
        return this;
    }

    public String getImageurl() {
        return imageurl;
    }

    public SlackAttachment setImageurl(String imageurl) {
        this.imageurl = imageurl;
        return this;
    }

    public JsonObject toJson() {
        JsonObject data = new JsonObject();

        if (Strings.isNullOrEmpty(fallback)) {
            throw new IllegalArgumentException(
                    "Missing Fallback @ SlackAttachment");
        } else {
            data.addProperty("fallback", fallback);
        }

        if (!Strings.isNullOrEmpty(text)) {
            data.addProperty("text", text);
        }

        if (!Strings.isNullOrEmpty(pretext)) {
            data.addProperty("pretext", pretext);
        }

        if (!Strings.isNullOrEmpty(color)) {
            data.addProperty("color", color);
        }

        if (!Strings.isNullOrEmpty(title)) {
            data.addProperty("title", title);
        }

        if (!Strings.isNullOrEmpty(titlelink)) {
            data.addProperty("title_link", titlelink);
        }

        if (!Strings.isNullOrEmpty(imageurl)) {
            data.addProperty("image_url", imageurl);
        }

        if (fields != null && fields.size() > 0) {
            data.add("fields", prepareFields());
        }

        return data;
    }
}
