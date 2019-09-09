package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crsId;

    @ManyToOne
    private DisciplineEntity discipline;

    @ManyToOne
    private LocationEntity location;

    @ManyToOne
    private LevelEntity level;

    @Column(nullable = false)
    private Boolean crsVip;

    @DateTimeFormat
    private LocalDateTime crsFromDate;
    @DateTimeFormat
    private LocalDateTime crsToDate;
    private String crsTitle;
    private String crsDesc;
    private String crsComment;

    @Column(nullable = false)
    private Boolean crsIndisp;

    @Column(nullable = false)
    private Boolean crsValidated;

    @Column(nullable = false)
    private Boolean crsPublished;

    @Column(nullable = false)
    private Boolean crsDeleted;

    /**
     * Display Default values
     * @return course entity with default values
     */
    public static CourseEntity defaultValue() {

        CourseEntity courseEntity = new CourseEntity();

        courseEntity.crsVip = false;
        courseEntity.crsValidated = false;
        courseEntity.crsPublished = false;
        courseEntity.crsDeleted = false;
        courseEntity.crsIndisp = false;

        return courseEntity;
    }

    public Long getCrsId() {
        return crsId;
    }

    public void setCrsId(Long crsId) {
        this.crsId = crsId;
    }

    public DisciplineEntity getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineEntity discipline) {
        this.discipline = discipline;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public LevelEntity getLevel() {
        return level;
    }

    public void setLevel(LevelEntity level) {
        this.level = level;
    }

    public Boolean getCrsVip() {
        return crsVip;
    }

    public void setCrsVip(Boolean crsVip) {
        this.crsVip = crsVip;
    }

    public LocalDateTime getCrsFromDate() {
        return crsFromDate;
    }

    public void setCrsFromDate(LocalDateTime crsFromDate) {
        this.crsFromDate = crsFromDate;
    }

    public LocalDateTime getCrsToDate() {
        return crsToDate;
    }

    public void setCrsToDate(LocalDateTime crsToDate) {
        this.crsToDate = crsToDate;
    }

    public String getCrsTitle() {
        return crsTitle;
    }

    public void setCrsTitle(String crsTitle) {
        this.crsTitle = crsTitle;
    }

    public String getCrsDesc() {
        return crsDesc;
    }

    public void setCrsDesc(String crsDesc) {
        this.crsDesc = crsDesc;
    }

    public String getCrsComment() {
        return crsComment;
    }

    public void setCrsComment(String crsComment) {
        this.crsComment = crsComment;
    }

    public Boolean getCrsValidated() {
        return crsValidated;
    }

    public void setCrsValidated(Boolean crsValidated) {
        this.crsValidated = crsValidated;
    }

    public Boolean getCrsPublished() {
        return crsPublished;
    }

    public void setCrsPublished(Boolean crsPublished) {
        this.crsPublished = crsPublished;
    }

    public Boolean getCrsDeleted() {
        return crsDeleted;
    }

    public void setCrsDeleted(Boolean crsDeleted) {
        this.crsDeleted = crsDeleted;
    }

    public Boolean getCrsIndisp() {
        return crsIndisp;
    }

    public void setCrsIndisp(Boolean crsIndisp) {
        this.crsIndisp = crsIndisp;
    }
}
