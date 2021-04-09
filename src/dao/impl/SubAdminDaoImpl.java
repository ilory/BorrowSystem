package dao.impl;

import Utils.JDBCUtils;
import dao.SubAdminDao;
import entity.Equipment;
import entity.Person_Equipment;
import entity.Person;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubAdminDaoImpl implements SubAdminDao {
    Connection con;
    PreparedStatement pst;
    ResultSet rst;

    @Override
    public List<Equipment> selectEquipment(Equipment equipment) {
        String sql = "SELECT * FROM equipment WHERE 1=1 ";
        StringBuffer sb = new StringBuffer(sql);
        List<Object> params = new ArrayList<Object>();
        // 动态构建本次查询的sql
        if(null != equipment.getEid()) {
            sb.append(" and eid = ? ");
            params.add(equipment.getEid());
        }
        if(null != equipment.getEname()) {
            sb.append(" and ename like ? ");
            params.add("%"+equipment.getEname()+"%");
        }

        sql = sb.toString();
        List<Equipment> list = new ArrayList<>();
        try{
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            for(int i=0;i<params.size();i++) {
                pst.setObject(i+1, params.get(i));
            }
            rst = pst.executeQuery();
            while(rst.next()){
                int eid = rst.getInt("eid");
                String ename = rst.getString("ename");
                int borrow = rst.getInt("borrow");
                Equipment personEquipment = new Equipment(eid,ename,borrow);
                list.add(personEquipment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pst,con,rst);
        }
        return list;
    }

    @Override
    public Person selectPersonByUid(int uid) {
        Person person = new Person();
        String sql = "SELECT * FROM user WHERE uid=?";

        try{
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1,uid);
            rst = pst.executeQuery();
            while(rst.next()){
                person.setUid(rst.getInt("uid"));
                person.setName(rst.getString("uname"));
                person.setPassword(rst.getString("password"));
                person.setSex(rst.getInt("sex"));
                person.setAge(rst.getInt("age"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pst,con,rst);
        }
        return person;
    }


    @Override
    public List<Equipment> getAllEquipment() {
        String sql = "SELECT * FROM equipment";
        List<Equipment> list = new ArrayList<>();
        try{
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            rst = pst.executeQuery();
            while(rst.next()){
                int eid = rst.getInt("eid");
                String ename = rst.getString("ename");
                int borrow = rst.getInt("borrow");
                Equipment equipment = new Equipment(eid,ename,borrow);
                list.add(equipment);
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.release(pst,con,rst);
        }
        return list;
    }

    @Override
    public boolean insertPerson_Equipment(int eid, int uid, Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "INSERT INTO user_equipment (eid,uid,type,btime,rtime) VALUES(?,?,?,CURRENT_DATE,?)";

        try{
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1,eid);
            pst.setInt(2,uid);
            pst.setInt(3,1);
            pst.setDate(4,sqlDate);
            int result = pst.executeUpdate();
            if(result>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            JDBCUtils.release(pst,con);
        }
    }

    @Override
    public int numberOfBorrowed(int uid) {
        int num=0;
        String sql = "SELECT COUNT(uid) FROM user_equipment WHERE uid=?";

        try{
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1,uid);
            rst = pst.executeQuery();
            while(rst.next()){
                num=rst.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pst,con,rst);
        }
        return num;
    }

    @Override
    public boolean addEquipment(String ename) {
        String sql = "INSERT INTO equipment(ename,borrow) VALUE(?,1)";
        try {
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1,ename);
            int result = pst.executeUpdate();
            if(result>0) {
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.release(pst,con);
        }
    }

    @Override
    public Person_Equipment getPerson_EquipmentById(int eid) {
        String sql = "SELECT ue.`eid`,e.`ename`,ue.`uid`,u.`uname`,ue.`btime`,ue.`rtime`,ue.`type` " +
                "FROM user_equipment ue " +
                "LEFT JOIN USER u ON ue.`uid`=u.`uid` " +
                "LEFT JOIN equipment e ON ue.`eid`=e.`eid` " +
                "WHERE ue.`eid`=1 ";
        Person_Equipment personEquipment = null;
        try {
            con = JDBCUtils.getConnection();
            System.out.println(sql);
            pst = con.prepareStatement(sql);
            // 给参数赋值
            rst = pst.executeQuery();
            while(rst.next()) {
                int uid = rst.getInt("uid");
                java.sql.Date btime = rst.getDate("btime");
                java.sql.Date rtime = rst.getDate("rtime");
                int type = rst.getInt("type");
                String ename = rst.getString("ename");
                String uname = rst.getString("uname");
                personEquipment = new Person_Equipment(eid,uid,new Date(btime.getTime()),new Date(rtime.getTime()),type,ename,uname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(pst,con,rst);
        }
        return personEquipment;
    }

    @Test
    public void test() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

//        Date date1 = format.parse("2021-04-08");
//        Date date2 = format.parse("2021-04-26");
        Person_Equipment personEquipment = new Person_Equipment(0,0,null,null,0,null,null);
        System.out.println(getPersonEquipment(personEquipment));
    }

    @Override
    public List<Person_Equipment> getPersonEquipment(Person_Equipment person_equipment) {
        String sql = "SELECT ue.`eid`,e.`ename`,ue.`uid`,u.`uname`,ue.`btime`,ue.`rtime`,ue.`type` " +
                "FROM user_equipment ue " +
                "LEFT JOIN USER u ON ue.`uid`=u.`uid` " +
                "LEFT JOIN equipment e ON ue.`eid`=e.`eid` " +
                "WHERE 1=1 ";

        StringBuffer sb = new StringBuffer(sql);
        List<Object> params = new ArrayList<Object>();
        // 动态构建本次查询的sql
        if(null != person_equipment.getEid()) {
            sb.append(" and ue.eid = ? ");
            params.add(person_equipment.getEid());
        }
        if(null != person_equipment.getUid()) {
            sb.append(" and ue.uid = ? ");
            params.add(person_equipment.getUid());
        }
        if(null != person_equipment.getType()) {
            sb.append(" and ue.type = ? ");
            params.add(person_equipment.getType());
        }

        sql = sb.toString();

        List<Person_Equipment> list = null;
        try {
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            // 给参数赋值
            for(int i=0;i<params.size();i++) {
                pst.setObject(i+1, params.get(i));
            }

            rst = pst.executeQuery();
            list = new ArrayList<Person_Equipment>();
            while(rst.next()) {
                int eid = rst.getInt("eid");
                int uid = rst.getInt("uid");
                java.sql.Date btime = rst.getDate("btime");
                java.sql.Date rtime = rst.getDate("rtime");
                int type = rst.getInt("type");
                String ename = rst.getString("ename");
                String uname = rst.getString("uname");
                Person_Equipment personEquipment = new Person_Equipment(eid, uid, new Date(btime.getTime()), new Date(rtime.getTime()), type, ename, uname);
                list.add(personEquipment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(pst,con,rst);
        }
        return list;
    }

    @Override
    public boolean equipmentBorrow(Person_Equipment person_equipment) {
        String sql = "UPDATE user_equipment ue,equipment e SET ue.type=2,e.borrow=0 " +
                "WHERE ue.eid=? AND ue.uid=? AND ue.type=1 AND e.eid=?";
        try{
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1,person_equipment.getEid());
            pst.setInt(2,person_equipment.getUid());
            pst.setInt(3,person_equipment.getEid());
            int result = pst.executeUpdate();
            if(result>0) {
                System.out.println("success");
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            JDBCUtils.release(pst,con);
        }
    }

    @Override
    public boolean equipmentRepay(Person_Equipment person_equipment) {
        String sql = "UPDATE user_equipment ue,equipment e set ue.type=3,ue.rtime=?,e.borrow=1 " +
                "WHERE ue.eid=? AND ue.uid=? AND ue.type=2 AND e.eid=? ";
        try{
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            pst.setDate(1,new java.sql.Date(person_equipment.getRtime().getTime()));
            pst.setInt(2,person_equipment.getEid());
            pst.setInt(3,person_equipment.getUid());
            pst.setInt(4,person_equipment.getEid());
            int result = pst.executeUpdate();
            if(result>0) {
                System.out.println("success");
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            JDBCUtils.release(pst,con);
        }
    }

    @Override
    public boolean delEquipment(int eid) {
        String sql = "DELETE FROM equipment WHERE eid=?";
        try{
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1,eid);
            int result = pst.executeUpdate();
            if(result>0) {
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.release(pst,con);
        }
    }

//    @Override
//    public boolean get(int uid, String password) {
//        return false;
//    }
}
