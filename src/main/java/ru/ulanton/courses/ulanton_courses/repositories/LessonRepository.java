package ru.ulanton.courses.ulanton_courses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulanton.courses.ulanton_courses.models.Lesson;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Optional<Lesson> findByCourse_SlugAndPosition(String slug, Integer position);
}
