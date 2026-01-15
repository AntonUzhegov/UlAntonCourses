package ru.ulanton.courses.ulanton_courses.models;

import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer position; // 1..20

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String content; // текст урока (можно HTML/Markdown)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    // --- getters/setters ---
    public Long getId() { return id; }

    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
