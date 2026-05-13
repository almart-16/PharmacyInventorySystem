package jpa;

public class JPAManager {

    private JPAUserManager userManager;
    private JPARoleManager roleManager;

    public JPAManager() {

        userManager = new JPAUserManager();
        roleManager = new JPARoleManager();
    }

    public JPAUserManager getUserManager() {

        return userManager;
    }

    public JPARoleManager getRoleManager() {

        return roleManager;
    }

    public void disconnect() {

        if(userManager != null) {
            userManager.disconnect();
        }

        if(roleManager != null) {
            roleManager.disconnect();
        }
    }
}