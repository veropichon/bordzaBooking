package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "practice")
public class PracticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prcId;

    private String prcLabel;
}
