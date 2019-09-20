package com.bordza.booking.bordzaBooking.utils;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class SomeBean {

    private LocalDateTime fromDateTime;
    private Integer fromTimeHour;
    private Integer fromTimeMinutes;

    private LocalDate fromDate;
    private String fromDateUS;
}
