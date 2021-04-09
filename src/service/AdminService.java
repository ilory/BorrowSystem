package service;

import entity.Person;

import java.util.List;

public interface AdminService {
    boolean register(Person person);
    boolean editUserPassword(int uid,String password);
    List<Person> getAllUser(int level);
    boolean delUser(int uid);
    List<Person> getSelectPerson(Person person);
}
