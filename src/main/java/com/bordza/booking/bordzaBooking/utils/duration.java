package com.bordza.booking.bordzaBooking.utils;

import lombok.Data;

@Data
public class duration {

    private int value;
    private String label;

    public duration(int value, String label) {

        this.value = value;
        this.label = label;
    }

}
