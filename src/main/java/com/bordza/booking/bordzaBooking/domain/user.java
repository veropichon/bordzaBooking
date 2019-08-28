package com.bordza.booking.bordzaBooking.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of= {"id","usr_login","usr_pwd"})
@Entity
public class user {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter @Setter
    @NotNull
    @Size(min = 4, max = 15)
    private String usr_login;

    @Getter @Setter
    @NotBlank
    private String usr_pwd;

}
