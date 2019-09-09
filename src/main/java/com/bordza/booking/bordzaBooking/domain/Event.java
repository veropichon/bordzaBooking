package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Event {
    private LocalDateTime start;
    private LocalDateTime end;
    private String title;
    private String url;
    private String backgroundColor;
    private String borderColor;
    private String textColor;

    public Event(LocalDateTime start, LocalDateTime end, String title, String url, String backgroundColor, String borderColor, String textColor) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.url = url;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.textColor = textColor;
    }
}
