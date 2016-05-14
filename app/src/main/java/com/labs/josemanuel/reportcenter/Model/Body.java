package com.labs.josemanuel.reportcenter.Model;

/**
 * Created by bufigol on 14/05/16.
 */
public class Body {
    private String value;
    private String format;
    private boolean summary;

    public Body(String value, String format, boolean summary) {
        this.value = value;
        this.format = format;
        this.summary = summary;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isSummary() {
        return summary;
    }

    public void setSummary(boolean summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Body{" +
                "value='" + value + '\'' +
                ", format='" + format + '\'' +
                ", summary=" + summary +
                '}';
    }
}
