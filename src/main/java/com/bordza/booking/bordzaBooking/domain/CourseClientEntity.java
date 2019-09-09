package com.bordza.booking.bordzaBooking.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data

@Entity(name = "booking")
public class CourseClientEntity implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private ClientEntity client;

    @Id
    @ManyToOne
    @JoinColumn
    private CourseEntity course;

    private Boolean bkVip;
    private Boolean bkMat;

}
