package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity (name = "level")
public class LevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long levId;

    private String levClientLabel;
    private String levCourseLabel;


}
