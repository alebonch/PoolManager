import BusinessLogic.AdminController;
import BusinessLogic.LoginController;
import BusinessLogic.UserController;
import DomainModel.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        handleLoginAction();

    }

    private static void handleLoginAction() throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        LoginController loginController = new LoginController();

        String input;

        do {

            System.out.println(
                    """
                    \s
                     PoolManager
                     1. Sign in
                     2. Sign up
                     3. Admin
                     4. Exit
                   \s"""
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> {

                    Scanner scanner1 = new Scanner(System.in);

                    System.out.println("\nMail: ");
                    String mail = scanner1.nextLine();
                    System.out.println("Password: ");
                    String password = scanner1.nextLine();

                    User user = loginController.login(mail, password);

                    if (user != null)
                        handleUserAction(user);

                }
                case "2" -> {
                    System.err.println("\nWelcome!");
                    String[] data = register();

                    User user = loginController.register(data[0], data[1], data[2], data[3], data[4]);

                    if (user != null)
                        handleUserAction(user);
                }
                case "3" -> {

                    Scanner scanner3 = new Scanner(System.in);

                    System.out.println("\nPassword Admin: ");
                    String password = scanner3.nextLine();

                    if (loginController.adminLogin(password) != null)
                        handleAdminAction();
                }
                case "4" -> System.exit(0);
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }
    private static void handleAdminAction() throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        String input;
        do { 
            System.out.println(
                    """
                    \s
                     Admin Actions
                     1. Users
                     2. Typologies
                     3. Postations
                     4. Reservations 
                     5. Extra
                     6. Log out
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> handleAdminUserAction();
                case "2" -> handleAdminTypologiesAction();
                case "3" -> handleAdminPostationsAction();
                case "4" -> handleAdminReservationsAction();
                case "5" -> handleAdminExtraAction();
                case "6" -> handleLoginAction();
            }
        } while (true);
    }
    private static void handleAdminUserAction()throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        AdminController adminController = new AdminController();
        String input;
        do { 
            System.out.println(
                    """
                    \s
                     Admin Actions
                     1. Search user
                     2. View all users
                     3. Add user
                     4. Remove user 
                     5. Back to main menu
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    System.out.println("Insert the mail of the user you are trying to find");
                    String input1 = scanner.nextLine();
                    User user = adminController.getUser(input1);
                    System.out.println(user.getInfo());
                }
                case "2" -> {
                    ArrayList<User> users = adminController.getAllUsers();
                    for (User user : users) {
                        System.out.println(user.getInfo());
                    }
                }
                case "3" -> {
                    String[] data = register();
                    adminController.AddUser(data[0], data[2], data[3], data[4], data[1]);
                }
                case "4" -> {
                    System.err.println("Insert the mail of the user you want to remove:");
                    String input1 = scanner.nextLine();
                    adminController.removeUser(input1);
                }
                case "5" -> handleAdminAction();
            }
        } while (true);
    }
    private static void handleAdminTypologiesAction()throws ClassNotFoundException, SQLException{

    }
    private static void handleAdminPostationsAction()throws ClassNotFoundException, SQLException{

    }
    private static void handleAdminReservationsAction()throws ClassNotFoundException, SQLException{

    }
    private static void handleAdminExtraAction()throws ClassNotFoundException, SQLException{

    }
    private static void handleUserAction(User user) throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();

        String input;
        do { 
            System.out.println(
                    """
                    \s
                     User Actions
                     1. Check postations
                     2. Reserve or cancel a reservation
                     3. Manage profile
                     4. Log out
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> handleUserPostationAction();
                case "2" -> handleUserReserveAction(user);
                case "3" -> handleUserProfileAction(user);
                case "4" -> handleLoginAction();
            }
        } while (true);
    } 

    private static void handleUserPostationAction() throws ClassNotFoundException, SQLException{}
    private static void handleUserReserveAction(User user) throws ClassNotFoundException, SQLException{}
    private static void handleUserProfileAction(User user) throws ClassNotFoundException, SQLException{}


    private static String[] register() {

        Scanner scanner2 = new Scanner(System.in);

        // personal data
        System.out.println("\n Please provide the following information to register:");
        //login
        String mail, pwd, name, surname, telephone;
        do { 
            System.out.println("Mail: ");
            mail = scanner2.nextLine();
            System.out.println("Password: ");
            pwd = scanner2.nextLine();
            System.out.println("Name: ");
            name = scanner2.nextLine();
            System.out.println("Surname: ");
            surname = scanner2.nextLine();
            System.out.println("Telephone: ");
            telephone = scanner2.nextLine();
            if (telephone.isEmpty()) {
                telephone = "No phone number inserted";
        }
        } while (name == null || name.isEmpty() || surname == null || surname.isEmpty() || mail == null || mail.isEmpty() || pwd == null || pwd.isEmpty()); 

        

        return new String[] {mail, pwd, name, surname, telephone};

    }

}
