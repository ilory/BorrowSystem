package dao.impl;

import Utils.JDBCUtils;
import dao.PersonDao;
import entity.Equipment;
import entity.Person_Equipment;
import entity.Person;
import jdk.nashorn.internal.scripts.JD;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonDaoImpl implements PersonDao {
    Connection con;
    PreparedStatement pst;
    ResultSet rst;

    @Test
    public void test(){
        Equipment equipment = new Equipment(null,"equipment1",null);
        System.out.println(selectEquipment(equipment));
    }

    @Override
    public List<Equipment> selectEquipment(Equipment equipment) {
        String sql = "SELECT * FROM equipment WHERE 1=1 AND borrow=1";
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
        String sql = "SELECT * FROM equipment WHERE borrow=1";
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
    public boolean insertPerson_Equipment(int eid,int uid,Date date) {
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

//    @Override
//    public boolean get(int uid, String password) {
//        String sql = "SELECT * FROM user WHEHE uid=? and password=md5(?)";
//        try{
//            con = JDBCUtils.getConnection();
//            pst = con.prepareStatement(sql);
//            pst.setInt(1,uid);
//            pst.setString(2,password);
//            rst = pst.executeQuery();
//            if(rst.next()) {
//                return true;
//            }else {
//                return false;
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }finally {
//            JDBCUtils.release(pst,con,rst);
//        }
//    }
}
