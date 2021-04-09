package dao;

import entity.Equipment;
import entity.Person_Equipment;
import entity.Person;

import java.util.Date;
import java.util.List;

public interface PersonDao {
    /*动态设备查找*/
    List<Equipment> selectEquipment(Equipment equipment);
    /*按uid用户查找*/
    Person selectPersonByUid(int uid);
    /*获取所有设备信息*/
    List<Equipment> getAllEquipment();
    /*添加申请租借项*/
    boolean insertPerson_Equipment(int eid, int uid, Date date);
    /*根据uid获取已接设备数目*/
    int numberOfBorrowed(int uid);
//    /**
//     * 用于登录判断
//     */
//    boolean get(int uid, String password);
}
