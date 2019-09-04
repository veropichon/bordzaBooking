package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.domain.UserEntity;
import com.bordza.booking.bordzaBooking.repositories.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    /**
     * Exception check when @POST from courseNew form
     * @param courseEntity course input form courseNew
     * @throws IllegalArgumentException
     */

    public void saveCourse(UserEntity userEntity, CourseEntity courseEntity) throws IllegalArgumentException {

        //
    }
}
