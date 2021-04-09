package entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Person {
    protected Integer uid;
    protected String name;
    protected String password;
    protected Integer sex;
    protected Integer age;
    private Integer level;
    public Person() {
        this.level = 3;
    }

    public Person(int uid, String name, String password, Integer sex, Integer age) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.level = 3;
    }
    public Person(String json) throws IOException {
        Person person = new ObjectMapper().readValue(json,Admin.class);
        this.uid = person.getUid();
        this.name = person.getName();
        this.password = person.getPassword();
        this.sex = person.getSex();
        this.age = person.getAge();
        this.level = person.getLevel();
    }
    @Override
    public String toString() {
        return "Person{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                "}";
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getLevel() {
        return level;
    }
}
