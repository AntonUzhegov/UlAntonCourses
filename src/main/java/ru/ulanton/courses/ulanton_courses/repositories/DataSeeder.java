package ru.ulanton.courses.ulanton_courses.repositories;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.ulanton.courses.ulanton_courses.models.Course;
import ru.ulanton.courses.ulanton_courses.models.Lesson;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    public DataSeeder(CourseRepository courseRepository, LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void run(String... args) {


// 1) Создаём курс (если его ещё нет)

    }
}
