package entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Admin extends Person {
    private Integer level;
    public Admin() {}

    public Admin(int uid, String name, String password, Integer sex, Integer age,Integer level) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.level = level;
    }

    public Admin(String json) throws IOException {
        Admin admin = new ObjectMapper().readValue(json,Admin.class);
        this.uid = admin.getUid();
        this.name = admin.getName();
        this.password = admin.getPassword();
        this.sex = admin.getSex();
        this.age = admin.getAge();
        this.level = admin.getLevel();
    }

    @Override
    public String toString() {
        return "Admin{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                "}";
    }

    @Override
    public Integer getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
