package com.codestates.todo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 100, nullable = false)
    String title;
    @Column(nullable = false)
    Integer todoOrder;
    @Column(nullable = false)
    Boolean completed;
}
