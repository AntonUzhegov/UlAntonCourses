package ru.ulanton.courses.ulanton_courses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulanton.courses.ulanton_courses.models.Course;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findBySlug(String slug);
}