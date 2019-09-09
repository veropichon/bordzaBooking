package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity (name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usrId;

    @Column(nullable = false, length = 50)
    private String usrEmail;

    @Column(nullable = false, length = 50)
    private String usrPwd;

    @Column(nullable = false, length = 20)
    private String usrRole;

    /**
     * Display Default values
     * @return user entity with default role
     */
    public static UserEntity defaultValue(UserEntity userEntity) {
        userEntity.usrRole = "CLIENT";
        return userEntity;
    }

}
