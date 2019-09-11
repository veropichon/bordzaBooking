package com.bordza.booking.bordzaBooking.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data

@Entity(name = "booking")
public class CourseClientEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bkId;


    @ManyToOne
    @JoinColumn
    private ClientEntity client;


    @ManyToOne
    @JoinColumn
    private CourseEntity course;

    private Boolean bkVip;
    private Boolean bkMat;

    /**
     * Display Default values
     * @return courseClient entity with default values
     */
    public static CourseClientEntity defaultValue(CourseClientEntity courseClientEntity) {
        courseClientEntity.bkVip = false;
        courseClientEntity.bkMat = false;
        return courseClientEntity;
    }

}
