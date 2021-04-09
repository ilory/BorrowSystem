package service.impl;

import dao.AdminDao;
import dao.impl.AdminDaoImpl;
import entity.Admin;
import entity.Person;
import org.junit.Test;
import service.AdminService;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    AdminDao adminDao = new AdminDaoImpl();
    /*判断密码是否符合要求*/
    public boolean passwordJudge(String password){
        if(password.length()<6||password.length()>18){
            System.out.println("长度不符合");
            return true;
        }
        for(int i = 0;i< password.length();i++){
            char x = password.charAt(i);
            if(x<'0'||(x>'9'&&x<'A')||(x>'Z'&&x<'_')||(x>'_'&&x<'a')||(x>'z')){
                System.out.println("字符集不合");
                return true;
            }
        }
        return false;
    }

    /*判断名字是否符合要求*/
    public boolean unameJudge(String uname){
        if(uname.length() <2||uname.length() >10){
            System.out.println("名字长度不合");
            return true;
        }
        for(int i = 0;i < uname.length();i ++){
            char x = uname.charAt(i);
            if(!((x>=65&&x<=90||x>=97&&x<=122)||(x>=0x4E00&&x<=0x9FA5))){
                System.out.println("名字字符集不合");
                return true;
            }
        }
        return false;
    }

    /*获取单个等级最大uid
    * 返回-1表示出现异常
    * */
    public int getMaxUid(int level){
        if(level==2||level==3){
            int maxUid = adminDao.getMaxUidByLevel(level);
            return maxUid;
        }
        return -1;
    }

    /*注册新用户*/
    @Override
    public boolean register(Person person){
        if(person.getName()==null||person.getPassword()==null||person.getSex()==-1||person.getAge()==-1){
            return false;
        }else if ((!passwordJudge(person.getPassword()))||(!unameJudge(person.getName()))){
            return false;
        }else if(adminDao.getMaxUidByLevel(person.getLevel())==29999){
            return false;
        }
        int uid = adminDao.getMaxUidByLevel(person.getLevel());
        person.setUid(uid+1);
        if(adminDao.insert(person)){
            return true;
        }
        return false;
    }

    /*修改用户密码*/
    @Override
    public boolean editUserPassword(int uid, String password){
        Person person = adminDao.getPersonByUid(uid);
        if(person==null){
            System.out.println("未找到");
            return false;
        }
        if(passwordJudge(person.getPassword())||unameJudge(person.getName())){
            System.out.println("不符合格式");
            return false;
        }
        if(adminDao.updatePassword(uid, password)){
            return true;
        }
        return false;
    }

    /*查找所有用户*/
    @Override
    public List<Person> getAllUser(int level){
        List<Person> list = new ArrayList<>();
        list = adminDao.getAll(level);
        return list;
    }

    /*按uid删除用户*/
    @Override
    public boolean delUser(int uid){
        return adminDao.deletePerson(uid);
    }

    @Override
    public List<Person> getSelectPerson(Person person) {
        return adminDao.getSelectPerson(person);
    }


}
