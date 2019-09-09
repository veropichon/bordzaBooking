package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "discipline")
public class DisciplineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disId;

    @ManyToOne
    private PracticeEntity practice;

    private String disLabel;

    public Long getDisId() {
        return disId;
    }

    public void setDisId(Long disId) {
        this.disId = disId;
    }

    public PracticeEntity getPractice() {
        return practice;
    }

    public void setPractice(PracticeEntity practice) {
        this.practice = practice;
    }

    public String getDisLabel() {
        return disLabel;
    }

    public void setDisLabel(String disLabel) {
        this.disLabel = disLabel;
    }
}
