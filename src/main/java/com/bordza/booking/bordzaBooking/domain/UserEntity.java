package com.bordza.booking.bordzaBooking.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

//@Data
//@FieldDefaults(level= AccessLevel.PRIVATE)
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class UserEntity {

    //@Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //@Getter @Setter
    String usrLogin;

    //@Getter @Setter
    @Size(min = 4, max = 15)
    String usrPwd;

    public UserEntity() {
    }

    public UserEntity(String usrLogin, @Size(min = 4, max = 15) String usrPwd) {
        this.usrLogin = usrLogin;
        this.usrPwd = usrPwd;
    }

    public Long getId() {
        return id;
    }

    public String getUsrLogin() {
        return usrLogin;
    }

    public void setUsrLogin(String usrLogin) {
        this.usrLogin = usrLogin;
    }

    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }
}
