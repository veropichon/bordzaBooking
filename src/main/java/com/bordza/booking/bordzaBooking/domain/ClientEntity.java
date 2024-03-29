package com.bordza.booking.bordzaBooking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Getter @Setter
@EqualsAndHashCode(exclude = {"courseClients"})
@Entity(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cliId;

    @OneToOne
    private UserEntity user;

    @ManyToOne
    private LevelEntity level;

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<CourseClientEntity> courseClients;

    @Basic
    @Column(nullable = false, length = 50)
    private String cliFirstname;

    @Basic
    @Column(nullable = false, length = 50)
    private String cliLastname;

    @Basic
    @Column(nullable = false, length = 10)
    private String cliPhone;

    @Basic
    @Column(nullable = false, length = 5)
    private String cliZipcode;

    @Basic
    @Column(nullable = false, length = 50)
    private String cliCity;

    @Basic
    @Column(length = 3)
    private Integer cliWeight;

    @Basic
    @Column(length = 3)
    private Integer cliHeight;

    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private Date cliBirthdate;

    @Basic
    @Column(length = 50)
    private String cliTutorFirstname;

    @Basic
    @Column(length = 50)
    private String cliTutorLastname;

    @Basic
    @Column(length = 50)
    private String cliTutorEmail;

    @Basic
    @Column(length = 10)
    private String cliTutorPhone;

    @Basic
    @Column(length = 255)
    private String cliComment;

    @Basic
    @Column(length = 255)
    private String cliUrlPicture;

    @Column(nullable = false)
    private Boolean cliDeleted;

    /**
     * Display Default values
     *
     * @return client entity with default values
     */
    public static ClientEntity defaultValue(ClientEntity clientEntity) {

        if (clientEntity.cliDeleted == null) {
            clientEntity.cliDeleted = false;
        }
        return clientEntity;
    }
}
