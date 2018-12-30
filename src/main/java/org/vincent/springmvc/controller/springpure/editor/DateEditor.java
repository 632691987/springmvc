package org.vincent.springmvc.controller.springpure.editor;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateEditor extends PropertyEditorSupport {

    DateFormat dateFormat = new SimpleDateFormat("MMddYYYY");
    private Date date;

    public DateEditor() {
        dateFormat.setTimeZone(TimeZone.getDefault());
    }

    @Override
    public String getAsText() {
        return this.date != null ? dateFormat.format(this.date) : "";
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.hasText(text)) {
            try {
                this.date = dateFormat.parse(text);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setValue(Object value) {
        this.date = (Date)value;
    }

    @Override
    public Object getValue() {
        return this.date;
    }
}
