package ru.ulanton.courses.ulanton_courses.models;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, unique = true)
    private String slug;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(length = 2000)
    private String shortDescription;

    @Setter
    private String badge;
    @Setter
    private String difficulty;
    @Setter
    private Integer durationWeeks;
    @Setter
    private Integer lessonsCount;

    @Setter
    @Column(length = 500)
    private String tags;

    @Setter
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("position ASC")
    private List<Lesson> lessons = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getBadge() {
        return badge;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Integer getDurationWeeks() {
        return durationWeeks;
    }

    public Integer getLessonsCount() {
        return lessonsCount;
    }

    public String getTags() {
        return tags;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

}
