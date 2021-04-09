package dao.impl;

import Utils.JDBCUtils;
import dao.AdminDao;
import entity.Admin;
import entity.Equipment;
import entity.Person;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    Connection con;
    PreparedStatement pst;
    ResultSet rst;

    @Override
    public boolean insert(Person person) {
//        String sql = "INSERT INTO user VALUES(?,?,md5(?),?,?,?)";
        String sql = "INSERT INTO user VALUES(?,?,?,?,?,?)";
        try {
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1,person.getUid());
            pst.setString(2,person.getName());
            pst.setString(3,person.getPassword());
            pst.setInt(4,person.getSex());
            pst.setInt(5,person.getAge());
            pst.setInt(6,person.getLevel());
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
    public boolean updatePassword(int uid, String password) {
//        String sql = "UPDATE user SET password=md5(?) WHERE uid=?";
        String sql = "UPDATE user SET password=? WHERE uid=?";
        try{
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1,password);
            pst.setInt(2,uid);
            int result = pst.executeUpdate();
            if(result>0) {
                System.out.println("success");
                return true;
            }else {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            JDBCUtils.release(pst,con);
        }
    }

    @Override
    public boolean deletePerson(int uid) {
        String sql = "DELETE FROM user WHERE uid=?";
        try{
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1,uid);
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
    public List<Person> getAll(int level) {
        String sql = "SELECT * FROM user WHERE level=?";
        List<Person> list = new ArrayList<>();
        try{
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1,level);
            rst = pst.executeQuery();
            while(rst.next()){
                Person person = null;
                int uid = rst.getInt("uid");
                String uname = rst.getString("uname");
                String password = rst.getString("password");
                int sex = rst.getInt("sex");
                int age = rst.getInt("age");
                if (level<=2){
                    person = new Admin(uid,uname,password,sex,age,level);
                } else if(level==3){
                    person = new Person(uid,uname,password,sex,age);
                }
                list.add(person);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.release(pst,con,rst);
        }
        return list;
    }

    @Test
    public void test(){
        Person person = new Person(30002,null,null,null,null);
        System.out.println(getSelectPerson(person));
    }

    @Override
    public List<Person> getSelectPerson(Person person) {
        String sql = "SELECT * FROM user WHERE 1=1 ";
        StringBuffer sb = new StringBuffer(sql);
        List<Object> params = new ArrayList<Object>();
        // 动态构建本次查询的sql
        if(null != person.getUid()) {
            sb.append(" and uid = ? ");
            params.add(person.getUid());
        }
        if(null != person.getName()) {
            sb.append(" and uname like ? ");
            params.add("%"+ person.getName()+"%");
        }
        if(null != person.getLevel()) {
            sb.append(" and level = ? ");
            params.add(person.getLevel());
        }else{
            sb.append(" and level = 3 ");
        }

        sql = sb.toString();
        List<Person> list = new ArrayList<>();
        try{
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            for(int i=0;i<params.size();i++) {
                pst.setObject(i+1, params.get(i));
                System.out.println(params.get(i));
            }
            System.out.println(sql);
            rst = pst.executeQuery();
            while(rst.next()){
                Person person1 = null;
                int uid = rst.getInt("uid");
                String uname = rst.getString("uname");
                String password = rst.getString("password");
                int sex = rst.getInt("sex");
                int age = rst.getInt("age");
                int level = rst.getInt("level");
                if (rst.getInt("level")==2){
                    person1 = new Admin(uid,uname,password,sex,age,level);
                } else if(rst.getInt("level")==3){
                    person1 = new Person(uid,uname,password,sex,age);
                }
                list.add(person1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.release(pst,con,rst);
        }
        return list;
    }


    @Override
    public Person getPersonByUid(int uid) {
        String sql = "SELECT * FROM user WHERE uid=?";
        Person person = null;
        try{
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1,uid);
            rst = pst.executeQuery();
            while(rst.next()){
                String uname = rst.getString("uname");
                String password = rst.getString("password");
                int sex = rst.getInt("sex");
                int age = rst.getInt("age");
                int level = rst.getInt("level");
                if (rst.getInt("level")==2){
                    person = new Admin(uid,uname,password,sex,age,level);
                } else if(rst.getInt("level")==3){
                    person = new Person(uid,uname,password,sex,age);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.release(pst,con,rst);
        }
        return person;
    }

    @Override
    public int getMaxUidByLevel(int level) {
        String sql = "SELECT MAX(uid) FROM user WHERE level=? ORDER BY uid";
        int maxUid=-1;
        try{
            con = JDBCUtils.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1,level);
            rst = pst.executeQuery();
            while(rst.next()){
                maxUid=rst.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return maxUid;
        } finally {
            JDBCUtils.release(pst,con,rst);
        }
        return maxUid;
    }
}
