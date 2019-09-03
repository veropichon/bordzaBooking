package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;

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

    private String cliFirstname;
    private String cliLastname;
    private String cliPhone;
    private Integer cliZipcode;
    private String cliCity;
    private Integer cliWeight;
    private Integer cliHeight;
    private Date cliBirthdate;
    private String cliTutorFirstname;
    private String cliTutorLastname;
    private String cliTutorEmail;
    private String cliTutorPhone;
    private String cliComment;

    @Column(nullable = false)
    private Boolean cliValidated;

    @Column(nullable = false)
    private Boolean cliDeleted;

    /**
     * Display Default values
     * @return client entity with default values
     */
    public static ClientEntity defaultValue() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.cliValidated = false;
        clientEntity.cliDeleted = false;
        return clientEntity;
    }

}
