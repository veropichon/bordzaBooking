package com.bordza.booking.bordzaBooking.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;

//@Data
//@FieldDefaults(level= AccessLevel.PRIVATE)
//@NoArgsConstructor
//@AllArgsConstructor
@Entity (name = "user")
public class UserEntity {

    //@Getter
    @Id
    @Column(name = "usr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long usrId;

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

    public UserEntity(Long usrId, String usrLogin, @Size(min = 4, max = 15) String usrPwd) {
        this.usrId = usrId;
        this.usrLogin = usrLogin;
        this.usrPwd = usrPwd;
    }

    public Long getId() {
        return usrId;
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
