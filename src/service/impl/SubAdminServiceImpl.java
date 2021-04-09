package service.impl;

import dao.SubAdminDao;
import dao.impl.SubAdminDaoImpl;
import entity.Equipment;
import entity.Person_Equipment;
import org.junit.Test;
import service.SubAdminService;

import java.util.List;

public class SubAdminServiceImpl implements SubAdminService {

    SubAdminDao subAdminDao = new SubAdminDaoImpl();

    @Override
    public List<Equipment> getAllEquipment() {
        return subAdminDao.getAllEquipment();
    }

    @Override
    public List<Equipment> getSelectEquipment(Equipment equipment) {
        return subAdminDao.selectEquipment(equipment);
    }

    @Override
    public boolean addEquipment(String ename) {
        return subAdminDao.addEquipment(ename);
    }

    @Override
    public boolean delEquipment(int eid) {
        return subAdminDao.delEquipment(eid);
    }

    @Override
    public List<Person_Equipment> getPersonEquipmentList(Person_Equipment person_equipment) {
        return subAdminDao.getPersonEquipment(person_equipment);
    }

    @Override
    public Person_Equipment getPersonEquipmentById(int eid) {
        return subAdminDao.getPerson_EquipmentById(eid);
    }

    @Override
    public boolean equipmentBorrow(Person_Equipment person_equipment) {
        return subAdminDao.equipmentBorrow(person_equipment);
    }

    @Override
    public boolean equipmentRepay(Person_Equipment person_equipment) {
        return subAdminDao.equipmentRepay(person_equipment);
    }
}
