package dao;

import entity.Admin;
import entity.Person;

import java.util.List;

public interface AdminDao {
    /* 添加用户 */
    boolean insert(Person person);

    /* 修改用户密码 */
    boolean updatePassword(int uid, String password);

    /* 删除用户 */
    boolean deletePerson(int uid);

    /* 查找所有同等级用户 */
    List<Person> getAll(int level);

    /*动态查找person*/
    List<Person> getSelectPerson(Person person);

    /* 根据uid查找 */
    Person getPersonByUid(int uid);

    int getMaxUidByLevel(int level);
}
