package ru.skypro.lessons.springboot.weblibrary1.pojo;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String position;

    public Position() {
    }
    public Position(int id, String position){
        this.id = id;
        this.position = position;
    }

}