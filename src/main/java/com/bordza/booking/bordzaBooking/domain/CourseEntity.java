package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crsId;

    // @ManyToOne
    // discipline
}
