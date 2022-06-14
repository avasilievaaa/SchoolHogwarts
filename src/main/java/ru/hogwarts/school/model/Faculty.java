package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Faculty {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String color;

    @JsonIgnore
    @OneToMany(mappedBy = "faculty")
    private Collection<Student> students;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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
                ", title='" + title +
                ", color='" + color;
    }

    public Collection<Student> getStudents() {
        return students;
    }
}