package service;

public interface LoginService {
    boolean login(String userId,String password, int level);
    String getUserName(String userId);
    int getUserLevel(String userId);
}
