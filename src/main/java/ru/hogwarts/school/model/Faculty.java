package ru.hogwarts.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Faculty {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String color;

    public Faculty() {
    }

    public Faculty(String title, String color) {
        this.title = title;
        this.color = color;
    }

    public Faculty(Long id, String title, String color) {
        this.id = id;
        this.title = title;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id.equals(faculty.id) && title.equals(faculty.title) && color.equals(faculty.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, color);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Faculty" +
                "id=" + id +
                ", name='" + title +
                ", color='" + color;
    }
}