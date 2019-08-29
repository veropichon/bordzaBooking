package com.bordza.booking.bordzaBooking.domain;

import javax.persistence.Entity;

@Entity(name = "client")
public class ClientEntity {

    private Long cliId;
    private Long CliUsrId;
    private String cliFirstname;
    private String cliLastname;
    private String cliPhone;




}
