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
        Course java = courseRepository.findBySlug("java-beginner")
                .orElseGet(() -> {
                    Course c = new Course();
                    c.setSlug("java-beginner");
                    c.setTitle("Java для начинающих");
                    c.setBadge("BASE");
                    c.setDifficulty("Лёгкая");
                    c.setDurationWeeks(8);
                    c.setLessonsCount(20);
                    c.setShortDescription("Идеальный старт в Java: установка, синтаксис, ООП и практика.");
                    return courseRepository.save(c);
                });

// 2) Урок 1 (создаём если его нет)
        lessonRepository.findByCourse_SlugAndPosition("java-beginner", 1)
                .orElseGet(() -> {
                    Lesson l1 = new Lesson();
                    l1.setCourse(java);
                    l1.setPosition(1);
                    l1.setTitle("Старт в Java: установка, Hello World, переменные");
                    l1.setContent("""
# Урок 1: Старт в Java
Тут твой Markdown-текст...
""");
                    return lessonRepository.save(l1);
                });

// 3) Урок 2 (создаём если его нет)
        lessonRepository.findByCourse_SlugAndPosition("java-beginner", 2)
                .orElseGet(() -> {
                    Lesson l2 = new Lesson();
                    l2.setCourse(java);
                    l2.setPosition(2);
                    l2.setTitle("Операторы и ввод данных (Scanner)");
                    l2.setContent("""
                            <section class="lesson">
                                                          <header class="lesson-header">
                                                            <h2 class="lesson-title">Урок: Среда разработки и первый запуск</h2>
                                                            <p class="lesson-subtitle">
                                                              Поставим JDK, выберем IDE и запустим первую программу на Java.
                                                            </p>
                                                          </header>
                            
                                                          <div class="lesson-content">
                                                            <p>
                                                              Для начала работы с Java необходимо установить и настроить несколько основных компонентов:
                                                            </p>
                            
                                                            <h3>1. Установка JDK (Java Development Kit)</h3>
                                                            <p>
                                                              JDK включает компилятор, инструменты разработки и среду выполнения Java.
                                                            </p>
                                                            <ul>
                                                              <li>Скачайте последнюю версию JDK с официального сайта Oracle или используйте OpenJDK</li>
                                                              <li>Установите JDK, следуя инструкциям для вашей операционной системы</li>
                                                              <li>Проверьте установку командой: <code>java -version</code></li>
                                                            </ul>
                            
                                                            <h3>2. Выбор IDE (Integrated Development Environment)</h3>
                                                            <p>Рекомендуемые среды разработки для Java:</p>
                                                            <ul>
                                                              <li><strong>IntelliJ IDEA</strong> — популярная IDE от JetBrains</li>
                                                              <li><strong>Eclipse</strong> — бесплатная с открытым исходным кодом</li>
                                                              <li><strong>NetBeans</strong> — ещё одна бесплатная альтернатива</li>
                                                            </ul>
                            
                                                            <h3>3. Создание первого проекта</h3>
                                                            <p>В вашей IDE создайте новый Java-проект:</p>
                                                            <ol>
                                                              <li>Выберите <strong>New Project</strong> → <strong>Java</strong></li>
                                                              <li>Укажите имя проекта (например, <code>HelloWorld</code>)</li>
                                                              <li>Выберите версию JDK, которую вы установили</li>
                                                            </ol>
                            
                                                            <h3>4. Написание и запуск первой программы</h3>
                                                            <p>Создайте новый класс с именем <code>Main</code>:</p>
                            
                                                            <pre class="code-block"><code class="language-java">public class Main {
                                                            public static void main(String[] args) {
                                                                System.out.println("Hello, World!");
                                                            }
                                                        }</code></pre>
                            
                                                            <p>Для запуска программы:</p>
                                                            <ul>
                                                              <li>Нажмите кнопку <strong>Run</strong> в вашей IDE</li>
                                                              <li>Или используйте терминал: <code>javac Main.java &amp;&amp; java Main</code></li>
                                                            </ul>
                            
                                                            <aside class="note">
                                                              <p>
                                                                <strong>Примечание:</strong> Убедитесь, что переменная окружения <code>JAVA_HOME</code>
                                                                настроена корректно, а путь к директории <code>bin</code> JDK добавлен в <code>PATH</code>.
                                                              </p>
                                                            </aside>
                                                          </div>
                                                        </section>
""");
                    return lessonRepository.save(l2);
                });
    }
}
