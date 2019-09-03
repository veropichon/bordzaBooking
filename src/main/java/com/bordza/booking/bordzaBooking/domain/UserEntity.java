package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity (name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usrId;

    private String usrEmail;

    private String usrPwd;

    @Column(nullable = false)
    private String usrRole;

    /**
     * Display Default values
     * @return user entity with default role
     */
    public static UserEntity defaultValue() {
        UserEntity userEntity = new UserEntity();
        userEntity.usrRole = "CLIENT";
        return userEntity;
    }

}
