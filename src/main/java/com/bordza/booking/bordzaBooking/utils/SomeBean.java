package com.bordza.booking.bordzaBooking.utils;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class SomeBean {

    private LocalDateTime fromDateTime;
    // private LocalTime fromTime;
    private Integer fromTimeHour;
    private Integer fromTimeMinutes;
}
