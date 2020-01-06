package com.zookeeper;

import java.util.Date;

public class CloneTest {

    public static void main(String[] args) {
        Dog dog = new Dog("旺财" ,"1");
//        Dog dog1 = dog.clone();
//        dog1.age="3";
//        System.out.println(dog+"----"+dog1);
        Person person = new Person("陈留涛","15","男",new Date(), dog);
        System.out.println(person);
        Person person1 = person.clone();
        System.out.println(person==person1);
        person1.dog=new Dog("来福","3");
        System.out.println(person);
        System.out.println(person1);

    }

}

class Person implements Cloneable {
    String name;
    String age;
    String sex;
    Date birthday;
    Dog dog;


    public Person(String name, String age, String sex, Date birthday, Dog dog) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.birthday = birthday;
        this.dog = dog;
    }

    public Person clone() {
        Person person = null;
        try {
            person = (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        person.dog = dog.clone();
        return person;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", dog=" + dog +
                '}';
    }
}


class Dog implements Cloneable{
    String name;
    String age;

    public Dog(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Dog clone(){
        Dog dog = null;
        try {
            dog= (Dog) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return dog;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
