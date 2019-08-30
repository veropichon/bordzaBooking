package com.bordza.booking.bordzaBooking.domain;

import javax.persistence.*;

@Entity (name = "level")
public class LevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long levId;

    private String levClientLabel;
    private String levCourseLabel;

    public LevelEntity(String levClientLabel, String levCourseLabel) {
        this.levClientLabel = levClientLabel;
        this.levCourseLabel = levCourseLabel;
    }

    public LevelEntity() {
    }

    public Long getLevId() {
        return levId;
    }

    public String getLevClientLabel() {
        return levClientLabel;
    }

    public void setLevClientLabel(String levClientLabel) {
        this.levClientLabel = levClientLabel;
    }

    public String getLevCourseLabel() {
        return levCourseLabel;
    }

    public void setLevCourseLabel(String levCourseLabel) {
        this.levCourseLabel = levCourseLabel;
    }
}
