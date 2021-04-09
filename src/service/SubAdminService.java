package service;

import entity.Equipment;
import entity.Person_Equipment;

import java.util.List;

public interface SubAdminService {
    List<Equipment> getAllEquipment();
    List<Equipment> getSelectEquipment(Equipment equipment);
    boolean addEquipment(String ename);
    boolean delEquipment(int eid);

    List<Person_Equipment> getPersonEquipmentList(Person_Equipment person_equipment);
    Person_Equipment getPersonEquipmentById(int eid) ;
    boolean equipmentBorrow(Person_Equipment person_equipment);
    boolean equipmentRepay(Person_Equipment person_equipment);
}
