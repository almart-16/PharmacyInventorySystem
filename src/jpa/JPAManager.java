package jpa;

public class JPAManager {

    private JPAUserManager userManager;
    private JPARoleManager roleManager;

    /**
     * Default constructor that initializes the JPA user and role managers.
     */
    public JPAManager() {

        userManager = new JPAUserManager();
        roleManager = new JPARoleManager();
    }

    /**
     * Gets the JPA user manager.
     * @return the user manager
     */
    public JPAUserManager getUserManager() {

        return userManager;
    }

    /**
     * Gets the JPA role manager.
     * @return the role manager
     */
    public JPARoleManager getRoleManager() {

        return roleManager;
    }

    /**
     * Disconnects the JPA entity managers.
     */
    public void disconnect() {

        if(userManager != null) {
            userManager.disconnect();
        }

        if(roleManager != null) {
            roleManager.disconnect();
        }
    }
}