package service;

import entity.Equipment;

import java.util.Date;
import java.util.List;

public interface UserService {
    boolean applyForEquipment(int eid, int uid, Date date);
    List<Equipment> getAllEquipment();
    List<Equipment> getSelectEquipment(Equipment equipment);
}
