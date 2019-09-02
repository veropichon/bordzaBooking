package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@Entity(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cliId;

    @OneToOne
    private UserEntity user;

    @ManyToOne
    private LevelEntity level;

    @Column(nullable = false)
    private String cliFirstname;

    @Column(nullable = false)
    private String cliLastname;

    @Column(nullable = false)
    private String cliPhone;

    @Size(min = 5, max = 5)
    private Integer cliZipcode;
    private String cliCity;

    @Column(length = 3)
    private Integer cliWeight;
    @Column(length = 3)
    private Integer cliHeight;

    @Column(nullable = false)
    private Date cliBirthdate;

    private String cliTutorFirstname;
    private String cliTutorLastname;
    private String cliTutorEmail;
    private String cliTutorPhone;
    private String cliComment;

    @Column(nullable = false, columnDefinition="BOOLEAN DEFAULT false")
    private Boolean cliValidated;

    @Column(nullable = false, columnDefinition="BOOLEAN DEFAULT false")
    private Boolean cliDeleted;

}
