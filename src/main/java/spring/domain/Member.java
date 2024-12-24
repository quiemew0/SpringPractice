package spring.domain;

import jakarta.persistence.*;

@Entity

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    public Member(String name, int age) {
        this.age = age;
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public Member(){
    }
    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

}
