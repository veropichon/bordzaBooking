package com.bordza.booking.bordzaBooking.domain;

import javax.persistence.*;
import java.sql.Date;


//@Entity(name = "client")
public class ClientEntity {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cliId;

    //@ManyToOne
    //private Long cliUsrId;
    private UserEntity cliUsrId;

    //@ManyToOne
    //private Long cliLevId;
    private LevelEntity cliLevId;

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
    private Boolean cliValidated;
    private Boolean cliDeleted;

    public ClientEntity() {
    }

    public ClientEntity(UserEntity cliUsrId, LevelEntity cliLevId, String cliFirstname, String cliLastname, String cliPhone, Integer cliZipcode, String cliCity, Integer cliWeight, Integer cliHeight, Date cliBirthdate, String cliTutorFirstname, String cliTutorLastname, String cliTutorEmail, String cliTutorPhone, String cliComment, Boolean cliValidated, Boolean cliDeleted) {
        this.cliUsrId = cliUsrId;
        this.cliLevId = cliLevId;
        this.cliFirstname = cliFirstname;
        this.cliLastname = cliLastname;
        this.cliPhone = cliPhone;
        this.cliZipcode = cliZipcode;
        this.cliCity = cliCity;
        this.cliWeight = cliWeight;
        this.cliHeight = cliHeight;
        this.cliBirthdate = cliBirthdate;
        this.cliTutorFirstname = cliTutorFirstname;
        this.cliTutorLastname = cliTutorLastname;
        this.cliTutorEmail = cliTutorEmail;
        this.cliTutorPhone = cliTutorPhone;
        this.cliComment = cliComment;
        this.cliValidated = cliValidated;
        this.cliDeleted = cliDeleted;
    }

    public UserEntity getCliUsrId() {
        return cliUsrId;
    }

    public void setCliUsrId(UserEntity cliUsrId) {
        this.cliUsrId = cliUsrId;
    }

    public LevelEntity getCliLevId() {
        return cliLevId;
    }

    public void setCliLevId(LevelEntity cliLevId) {
        this.cliLevId = cliLevId;
    }

    public String getCliFirstname() {
        return cliFirstname;
    }

    public void setCliFirstname(String cliFirstname) {
        this.cliFirstname = cliFirstname;
    }

    public String getCliLastname() {
        return cliLastname;
    }

    public void setCliLastname(String cliLastname) {
        this.cliLastname = cliLastname;
    }

    public String getCliPhone() {
        return cliPhone;
    }

    public void setCliPhone(String cliPhone) {
        this.cliPhone = cliPhone;
    }

    public Integer getCliZipcode() {
        return cliZipcode;
    }

    public void setCliZipcode(Integer cliZipcode) {
        this.cliZipcode = cliZipcode;
    }

    public String getCliCity() {
        return cliCity;
    }

    public void setCliCity(String cliCity) {
        this.cliCity = cliCity;
    }

    public Integer getCliWeight() {
        return cliWeight;
    }

    public void setCliWeight(Integer cliWeight) {
        this.cliWeight = cliWeight;
    }

    public Integer getCliHeight() {
        return cliHeight;
    }

    public void setCliHeight(Integer cliHeight) {
        this.cliHeight = cliHeight;
    }

    public Date getCliBirthdate() {
        return cliBirthdate;
    }

    public void setCliBirthdate(Date cliBirthdate) {
        this.cliBirthdate = cliBirthdate;
    }

    public String getCliTutorFirstname() {
        return cliTutorFirstname;
    }

    public void setCliTutorFirstname(String cliTutorFirstname) {
        this.cliTutorFirstname = cliTutorFirstname;
    }

    public String getCliTutorLastname() {
        return cliTutorLastname;
    }

    public void setCliTutorLastname(String cliTutorLastname) {
        this.cliTutorLastname = cliTutorLastname;
    }

    public String getCliTutorEmail() {
        return cliTutorEmail;
    }

    public void setCliTutorEmail(String cliTutorEmail) {
        this.cliTutorEmail = cliTutorEmail;
    }

    public String getCliTutorPhone() {
        return cliTutorPhone;
    }

    public void setCliTutorPhone(String cliTutorPhone) {
        this.cliTutorPhone = cliTutorPhone;
    }

    public String getCliComment() {
        return cliComment;
    }

    public void setCliComment(String cliComment) {
        this.cliComment = cliComment;
    }

    public Boolean getCliValidated() {
        return cliValidated;
    }

    public void setCliValidated(Boolean cliValidated) {
        this.cliValidated = cliValidated;
    }

    public Boolean getCliDeleted() {
        return cliDeleted;
    }

    public void setCliDeleted(Boolean cliDeleted) {
        this.cliDeleted = cliDeleted;
    }

    public Long getCliId() {
        return cliId;
    }
}
