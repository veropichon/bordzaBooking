package com.bordza.booking.bordzaBooking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

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

    @ManyToOne
    private DurationEntity duration;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<CourseClientEntity> courseClients;

    @Basic
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
    private Boolean crsPublished;

    @Column(nullable = false)
    private Boolean crsDeleted;

    @Column(nullable = false)
    private Boolean crsUnavailable;

    /**
     * Display Default values
     * @return course entity with default values
     */
    public static CourseEntity defaultValue(CourseEntity courseEntity) {

        if (courseEntity.crsVip == null) { courseEntity.crsVip = false; }
        if (courseEntity.crsPublished == null) { courseEntity.crsPublished = false; }
        if (courseEntity.crsDeleted == null) { courseEntity.crsDeleted = false; }
        if (courseEntity.crsUnavailable == null) { courseEntity.crsUnavailable = false; }

        return courseEntity;
    }
}
