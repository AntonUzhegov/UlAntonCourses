package ru.ulanton.courses.ulanton_courses.dto;

public class ContactFormDto {
    private String name;
    private String email;
    private String topic;
    private String message;

    // конструкторы
    public ContactFormDto() {}

    public ContactFormDto(String name, String email, String topic, String message) {
        this.name = name;
        this.email = email;
        this.topic = topic;
        this.message = message;
    }

    // геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Имя: " + name + "\n" +
                "Email: " + email + "\n" +
                "Тема: " + topic + "\n" +
                "Сообщение:\n" + message;
    }
}