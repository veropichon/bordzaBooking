package com.bordza.booking.bordzaBooking.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;

    private String testFirstname;
    private String testLastname;

    public TestEntity() {
    }

    public TestEntity(String testFirstname, String testLastname) {
        this.testFirstname = testFirstname;
        this.testLastname = testLastname;
    }

    public String getTestFirstname() {
        return testFirstname;
    }

    public void setTestFirstname(String testFirstname) {
        this.testFirstname = testFirstname;
    }

    public String getTestLastname() {
        return testLastname;
    }

    public void setTestLastname(String testLastname) {
        this.testLastname = testLastname;
    }

    public Long getTestId() {
        return testId;
    }
}
