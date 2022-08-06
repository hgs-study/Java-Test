package com.javatest.domain;

import com.javatest.StudyStatus;

import java.time.LocalDateTime;

public class Study {

    private StudyStatus status ;

    private int limit;

    private String name;

    private Member owner;

    private LocalDateTime openDateTime;

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public Study(int limit){
        if( limit < 0 ){
            throw new IllegalArgumentException("limit은 0보다 커야합니다.");
        }
        this.limit = limit;
    }

    public StudyStatus getStatus(){
        return this.status;
    }

    public int getLimit() {
        return limit;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getOpenDateTime() {
        return openDateTime;
    }

    @Override
    public String toString() {
        return "Study{" +
                "status=" + status +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                '}';
    }

    public void open(){
        this.openDateTime = LocalDateTime.now();
        this.status = StudyStatus.OPENED;
    }
}
