package service.impl;

import dao.PersonDao;
import dao.impl.PersonDaoImpl;
import entity.Equipment;
import org.junit.Test;
import service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    PersonDao personDao = new PersonDaoImpl();

    @Override
    public boolean applyForEquipment(int eid, int uid, Date date) {
        /*判重*/
        List<Equipment> list = getSelectEquipment(new Equipment(eid,null,null));
        for(Equipment equipment:list){
            if(equipment.getBorrow()!=1){
                return false;
            }
        }
        /*限制每人最多借4台设备*/
        if (personDao.numberOfBorrowed(uid)>5){
            return false;
        }
        return personDao.insertPerson_Equipment(eid,uid,date);
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return personDao.getAllEquipment();
    }

    @Override
    public List<Equipment> getSelectEquipment(Equipment equipment) {
        return personDao.selectEquipment(equipment);
    }


}
