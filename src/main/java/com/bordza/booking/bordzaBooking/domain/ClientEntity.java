package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    @Basic
    @Column(nullable = false, length = 100)
    private String cliFirstname;

    @Basic
    @Column(nullable = false, length = 100)
    private String cliLastname;

    @Basic
    @Column(nullable = false, length = 10)
    private String cliPhone;

    @Basic
    @Column(nullable = false, length = 5)
    private Integer cliZipcode;

    @Basic
    @Column(nullable = false, length = 100)
    private String cliCity;

    @Basic
    @Column(length = 3)
    private Integer cliWeight;

    @Basic
    @Column(length = 3)
    private Integer cliHeight;

    @DateTimeFormat
    private Date cliBirthdate;

    @Basic
    @Column(length = 100)
    private String cliTutorFirstname;

    @Basic
    @Column(length = 100)
    private String cliTutorLastname;

    @Basic
    @Column(length = 100)
    private String cliTutorEmail;

    @Basic
    @Column(length = 100)
    private String cliTutorPhone;

    @Basic
    @Column(length = 255)
    private String cliComment;

    @Column(nullable = false)
    private Boolean cliValidated;

    @Column(nullable = false)
    private Boolean cliDeleted;

    /**
     * Display Default values
     * @return client entity with default values
     */
    public static ClientEntity defaultValue(ClientEntity clientEntity) {
        clientEntity.cliValidated = false;
        clientEntity.cliDeleted = false;
        return clientEntity;
    }

}
