package com.imooc.demo;

public class Person implements Comparable<Person>, Cloneable {

    private int age;

    private Father father;

    public Father getFather() {
        return father;
    }

    public void setFather(Father father) {
        this.father = father;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        return age - o.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", father=" + father +
                '}';
    }

    public static void main(String[] args) {
        Person person1 = new Person();
        person1.setAge(11);
        Father father = new Father();
        father.setName("哈哈，就是我");
        person1.setFather(father);

        Person person2 = new Person();
        person2.setAge(23);

        System.out.println(person1.compareTo(person2));
        try {
            Person personClone = (Person) person1.clone();
            System.out.println(personClone);
            father.setName("我变了");
            System.out.println(personClone);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
