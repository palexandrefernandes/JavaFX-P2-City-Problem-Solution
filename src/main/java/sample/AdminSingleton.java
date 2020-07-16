package sample;

import models.Admin;
import repository.AdminRepository;

public class AdminSingleton {
    private static Admin admin;

    private AdminSingleton(){}

    public static boolean login(String name, String password){
        AdminRepository ar = new AdminRepository();
        Admin temp = ar.login(name, password);
        if(temp != null) {
            admin = temp;
            return true;
        }
        return false;
    }

    public static Admin getCurrentAdmin(){
        return admin;
    }

    public static void logout(){
        admin = null;
    }
}
