package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;

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
    private Boolean bkValidated;

    /**
     * Display Default values
     * @return courseClient entity with default values
     */
    public static CourseClientEntity defaultValue(CourseClientEntity courseClientEntity) {

        if (courseClientEntity.bkVip == null) { courseClientEntity.bkVip = false; }
        if (courseClientEntity.bkMat == null) { courseClientEntity.bkMat = false; }
        if (courseClientEntity.bkValidated == null) { courseClientEntity.bkValidated = false; }

        return courseClientEntity;
    }

}
