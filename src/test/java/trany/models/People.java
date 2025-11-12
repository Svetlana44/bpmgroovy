package trany.models;

import lombok.Data;

/*
@Setter
@Getter
@ToString*/
@Data
public class People {
    public String name;
    public int age;

    public People() {
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

 /*   замена аннотацией lombok
 @Override
     public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }*/
}
