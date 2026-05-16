import jpa.JPARoleManager;
import jpa.JPAUserManager;
import pojos.Role;

public class TestCreateUser {
    public static void main(String[] args) {
        JPARoleManager roleManager = new JPARoleManager();
        JPAUserManager userManager = new JPAUserManager();

        Role pharmacistRole = roleManager.findRoleByName("pharmacist");
        System.out.println("Role found: " + pharmacistRole);

        System.out.println("Creating user...");
        userManager.createUser("aleexmartnz", "Atleti123", pharmacistRole);
        System.out.println("Done.");
    }
}
