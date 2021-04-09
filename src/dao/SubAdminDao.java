package dao;

import entity.Person_Equipment;

import java.util.List;

public interface SubAdminDao extends PersonDao{
    /*添加设备*/
    boolean addEquipment(String ename);
    /*根据ID查找申请表*/
    Person_Equipment getPerson_EquipmentById(int eid);
    /*获取所有申请表*/
    List<Person_Equipment> getPersonEquipment(Person_Equipment person_equipment);
    /*修改申请表（借出）*/
    boolean equipmentBorrow(Person_Equipment person_equipment);
    /*修改申请表（归还）*/
    boolean equipmentRepay(Person_Equipment person_equipment);
    /*删除设备*/
    boolean delEquipment(int eid);
}
