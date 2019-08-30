package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Data
//@Entity
public class TestEntity {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;

    private String testFirstname;
    private String testLastname;

}
