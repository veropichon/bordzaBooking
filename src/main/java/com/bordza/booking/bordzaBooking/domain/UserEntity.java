package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity (name = "user")
public class UserEntity {

    @Id
    @Column(name = "usr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usrId;

    private String usrLogin;

    @Size(min = 4, max = 15)
    private String usrPwd;

}
