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
    private String role;

    /**
     * Display Default values
     * @return user entity with default role
     */
    public static UserEntity defaultValue(UserEntity userEntity) {
        if(userEntity.role == null){
            userEntity.role = "CLIENT";
        }
        return userEntity;
    }

}
