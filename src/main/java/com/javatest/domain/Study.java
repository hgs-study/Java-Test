package com.javatest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Study {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private StudyStatus status = StudyStatus.DRAFT;
    private int limitCount;
    private String name;
    private LocalDateTime openDateTime;
    private Long ownerId;

    public Study(int limit, String name){
        this.limitCount = limit;
        this.name = name;
    }

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit은 0보다 커야 한다.");
        }
        this.limitCount = limit;
    }

    public void open() {
        this.openDateTime = LocalDateTime.now();
        this.status = StudyStatus.OPENED;

    }
}
