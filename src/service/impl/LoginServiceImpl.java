package service.impl;


import com.ndktools.javamd5.Mademd5;
import dao.PersonDao;
import dao.impl.PersonDaoImpl;
import entity.Person;
import service.LoginService;

public class LoginServiceImpl implements LoginService {

    PersonDao personDao = new PersonDaoImpl();
//    Mademd5 md = new Mademd5();
    /**
     * 登录
     */
    @Override
    public boolean login(String userId, String password,int level){
        System.out.println("login");
        if (userId!=null||password!=null){
            int realLevel= getUserLevel(userId);
            Integer id = Integer.parseInt(userId);
            Person person = personDao.selectPersonByUid(id);
            System.out.println(userId + " " + person.getPassword());
            if(person.getPassword().equals(password)&&realLevel==level){
                return true;
            }
        }
        return false;
//        if (userId!=null||password!=null){
//            Integer uid = Integer.parseInt(userId);
//            System.out.println(userId);
//            return personDao.get(uid,password);
//        }
//        return false;
    }

    /**
     * 获取用户名
     */
    @Override
    public String getUserName(String userId){
        Integer id = Integer.parseInt(userId);
        Person person = personDao.selectPersonByUid(id);
        String uname = personDao.selectPersonByUid(id).getName();
        return uname;
    }

    /**
     * 获取用户等级
     */
    @Override
    public int getUserLevel(String userId){
        try{
            Integer id = Integer.parseInt(userId);
            int level = id/10000;
            if (level==1){
                return 1;
            }else if(level==2){
                return 2;
            }else if(level>=3){
                return 3;
            }else{
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }
}
