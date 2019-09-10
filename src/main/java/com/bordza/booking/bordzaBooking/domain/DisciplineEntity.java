package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "discipline")
public class DisciplineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disId;

    private String disLabel;

}
