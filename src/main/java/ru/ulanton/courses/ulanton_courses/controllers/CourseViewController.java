package ru.ulanton.courses.ulanton_courses.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ulanton.courses.ulanton_courses.repositories.CourseRepository;
import ru.ulanton.courses.ulanton_courses.repositories.LessonRepository;

@Controller
public class CourseViewController {

    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    public CourseViewController(CourseRepository courseRepository, LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
    }

    @GetMapping("/courses/{slug}")
    public String courseView(@PathVariable String slug,
                             @RequestParam(name = "lesson", required = false) Integer lessonPos,
                             Model model) {

        var course = courseRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Course not found: " + slug));

        int pos = (lessonPos == null) ? 1 : lessonPos;

        var lesson = lessonRepository.findByCourse_SlugAndPosition(slug, pos)
                .orElse(null);

        model.addAttribute("course", course);
        model.addAttribute("lesson", lesson);
        model.addAttribute("activeLessonPos", pos);

        return "course-view";
    }
}
