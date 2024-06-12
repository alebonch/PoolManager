import BusinessLogic.AdminController;
import BusinessLogic.LoginController;
import BusinessLogic.ReserveController;
import BusinessLogic.UserController;
import DomainModel.Postation;
import DomainModel.Reservation;
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
                    System.out.println("\nWelcome!");
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
                     2. Reservations 
                     3. Resources 
                     4. Extra
                     5. Log out
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> handleAdminUserAction();
                case "2" -> handleAdminReservationsAction();
                case "3" -> handleResourcesAction();
                case "4" -> handleAdminExtraAction();
                case "5" -> handleLoginAction();
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
                     Admin User Actions
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
                    System.out.println("Insert the mail of the user you want to remove:");
                    String input1 = scanner.nextLine();
                    adminController.removeUser(input1);
                }
                case "5" -> handleAdminAction();
            }
        } while (true);
    }
    private static void handleAdminReservationsAction() throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        AdminController adminController = new AdminController();
        ReserveController reserveController = new ReserveController();
        String input;
        do { 
            System.out.println(
                    """
                    \s
                     Admin Reservations Actions
                     1. View reservations
                     2. Add reservation
                     3. Remove reservation
                     4. Back to Admin menu
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> handleAdminReservationsViewAction();
                case "2" -> {
                    //FIXME
                    String[] data = addReservation();
                    //FIXME
                    adminController.addReservation(data[0], Integer.parseInt(data[1]),Integer.parseInt(data[3]),data[2]);
                }
                case "3" -> handleAdminReservationsRemoveAction();
                case "4" -> handleAdminAction();
            }
        }while (true);
    }

    private static void handleAdminReservationsRemoveAction() throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        AdminController adminController = new AdminController();
        String input;
        do { 
            System.out.println(
                    """
                    \s
                     Reservation removal Actions
                     1. Remove precise reservation
                     2. Remove all reservations by user
                     3. Remove all reservations by date
                     4. Back to Admin menu
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    //FIXME
                    System.out.println("Insert the username of the reservation: ");
                    String username = scanner.nextLine();
                    System.out.println("Insert the date of the reservation: ");
                    String date = scanner.nextLine();
                    adminController.removeReservation(username, date);
                }
                case "2" -> {
                    //FIXME
                    System.out.println("Insert the username of the reservations: ");
                    String username = scanner.nextLine();
                    adminController.removeAllReservationsByUser(username);
                }
                case "3" -> {
                    //FIXME
                    System.out.println("Insert the date of the reservations: ");
                    String date = scanner.nextLine();
                    adminController.removeAllReservationsByDate(date);
                }
                case "4" -> handleAdminReservationsAction();
            }
        }while (true);
    }
    private static void handleAdminReservationsViewAction() throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        AdminController adminController = new AdminController();
        ReserveController reserveController = new ReserveController();
        String input;
        do { 
            System.out.println(
                    """
                    \s
                     Reservation view Actions
                     1. View precise reservation via user and date
                     2  View precise reservation via postation and date
                     3. View all reservations by user
                     4. View all reservations by date
                     5. View all reservations
                     6. Back to Reservation menu
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    System.out.println("Insert the username of the reservation: ");
                    String username = scanner.nextLine();
                    System.out.println("Insert the date of the reservation: ");
                    String date = scanner.nextLine();
                    System.out.println("Insert the turn of the reservation: ");
                    String turno = scanner.nextLine();
                    ArrayList<Reservation> reservations = adminController.getDateUserReservations(reserveController.TimeRecordFixer(date, turno), username);
                    for (Reservation reservation : reservations){
                        System.out.println(reservation.getInfo());
                    }
                }
                case "2" -> {
                    System.out.println("Insert the postation of the reservation: ");
                    String posId = scanner.nextLine();
                    System.out.println("Insert the date of the reservation: ");
                    String date = scanner.nextLine();
                    System.out.println("Insert the turn of the reservation: ");
                    String turno = scanner.nextLine();
                    ArrayList<Reservation> reservations = adminController.getDatePostationReservation(Integer.parseInt(posId), reserveController.TimeRecordFixer(date, turno));
                    for (Reservation reservation : reservations){
                        System.out.println(reservation.getInfo());
                    }
                }
                case "3" -> {
                    System.out.println("Insert the username of the reservations: ");
                    String username = scanner.nextLine();
                    ArrayList<Reservation> reservations = adminController.getUserReservations(username);
                    for (Reservation reservation : reservations){
                        System.out.println(reservation.getInfo());
                    }
                }
                case "4" -> {
                    System.out.println("Insert the date of the reservations: ");
                    String date = scanner.nextLine();
                    System.out.println("Insert the turn of the reservation: ");
                    String turno = scanner.nextLine();
                    ArrayList<Reservation> reservations = adminController.getDateReservations(reserveController.TimeRecordFixer(date, turno));
                    for (Reservation reservation : reservations){
                        System.out.println(reservation.getInfo());
                }
                }
                case "5" ->{
                    ArrayList<Reservation> reservations = adminController.getAllReservations();
                    for (Reservation reservation : reservations){
                        System.out.println(reservation.getInfo());
                }
                }
                case "6" -> handleAdminReservationsAction();
            }
        }while (true);
    }
    //OKAY
    private static void handleAdminExtraAction()throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        AdminController adminController = new AdminController();
        String input;
        do { 
            System.out.println(
                    """
                    \s
                     Reservation view Actions
                     1. Update Admin password
                     2  Generate default database
                     3. Reset database
                     4. Back to Admin menu
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    System.out.println("Insert the new password: ");
                    String newPwd = scanner.nextLine();
                    adminController.updatePassword(newPwd);   
                }
                
                case "2" -> {
                    adminController.generateDefaultDatabase();
                }
                case "3" -> {
                    adminController.resetDatabase();
                }
                case "4" -> handleAdminAction();
            }
        }while (true);
    }
    //ADD HANDLER TO UPDATE RESERVATIONS INFO'S
    private static void handleUserAction(User user) throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();
        String input;
        do { 
            System.out.println(
                    """
                    \s
                     User Actions
                     1. Reserve or cancel a reservation
                     2. Manage profile
                     3. Log out
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> handleUserReserveAction(user);
                case "2" -> handleUserProfileAction(user);
                case "3" -> handleLoginAction();
            }
        } while (true);
    } 

    private static void handleUserReserveAction(User user) throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();
        ReserveController reserveController = new ReserveController();
        String input;
        do { 
            System.out.println(
                    """
                    \s
                     User Reservation Actions
                     1. Check user's reservation
                     2. Add reservation
                     3. Remove reservation
                     4. Log out
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    ArrayList<Reservation> reservations = userController.getUserReservations(user.getMail());
                    for (Reservation reservation : reservations) {
                        System.out.println(reservation.getInfo());
                    }
                }
                case "2" -> {
                    //FIX ME
                    String postation, date, pm;
                    System.out.println("Insert the number of the postation you want to reserve: ");
                    postation = scanner.nextLine();
                    System.out.println("Insert the date of the day you want to reserve the postation: ");
                    date = scanner.nextLine();
                    System.out.println("Insert the payment method of the reservation (optional): ");
                    pm = scanner.nextLine();
                    userController.addReservation(user.getMail(), Integer.parseInt(postation), Integer.parseInt(pm), date);
                }
                case "3" -> {
                    //FIX ME                    
                    String date;
                    System.out.println("Insert the date of the day you want to cancel the reservation: ");
                    date = scanner.nextLine();
                    userController.removeReservation(user.getMail(), date);
                }
                case "4" -> {
                    //FIX ME
                    String date, pm;
                    System.out.println("Insert the date of the day you want to update the reservation's payment method: ");
                    date = scanner.nextLine();
                    System.out.println("Insert the new payment method: ");
                    pm = scanner.nextLine();
                    userController.updatePaymentMethods(user.getMail(), date, Integer.parseInt(pm));
                }
                case "5" -> handleUserAction(user);
            }
        } while (true);
    }
    private static void handleUserProfileAction(User user) throws ClassNotFoundException, SQLException{

    Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();
        String input;
        do { 
            System.out.println(
                    """
                    \s
                     User Profile Actions
                     1. Check user's information
                     2. Update password
                     3. Update telephone
                     4. Back to User menu
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    System.out.println(user.getInfo());
                }
                case "2" -> {
                    String newPwd;
                    System.out.println("Insert the new password: ");
                    newPwd = scanner.nextLine();
                    userController.updatePassword(user.getMail(), newPwd);;
                }
                case "3" -> {
                    String telephone;
                    System.out.println("Insert the telephone number: ");
                    telephone = scanner.nextLine();
                    userController.updateTelephone(user.getMail(), telephone);;
                }
                case "4" -> handleUserAction(user);
            }
        } while (true);
    }


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

    private static String[] addTypo(){
        Scanner scanner2 = new Scanner(System.in);

        // personal data
        System.out.println("\n Please provide the following information to add the typology:");
        //login
        String name, n_chairs, n_deckchairs, n_sunbeds, m_sunbeds, gazebo;
        do { 
            System.out.println("Name: ");
            name = scanner2.nextLine();
            System.out.println("Number of chairs: ");
            n_chairs = scanner2.nextLine();
            System.out.println("Number of deckchairs: ");
            n_deckchairs = scanner2.nextLine();
            System.out.println("Number of sunbeds: ");
            n_sunbeds = scanner2.nextLine();
            System.out.println("Material of sunbeds: ");
            m_sunbeds = scanner2.nextLine();
            System.out.println("Insert 'True' if there is a gazebo, insert 'False' otherwise: ");
            gazebo = scanner2.nextLine();
        } while (name==null||name.isEmpty()||n_chairs == null || n_chairs.isEmpty() || n_deckchairs == null || n_deckchairs.isEmpty() || n_sunbeds == null || n_sunbeds.isEmpty() 
        || m_sunbeds == null || m_sunbeds.isEmpty() || gazebo == null || gazebo.isEmpty()); 

        

        return new String[] {n_chairs,n_deckchairs,n_sunbeds,m_sunbeds,gazebo, name};

    }
    private static String[] addReservation(){                    //FIX ME
        Scanner scanner2 = new Scanner(System.in);

        // personal data
        System.out.println("\n Please provide the following information to add the reservation:");
        //login
        String username, postation, date, pm;
        do { 
            System.out.println("Mail: ");
            username = scanner2.nextLine();
            System.out.println("Number of postation: ");
            postation = scanner2.nextLine();
            System.out.println("Date: ");
            date = scanner2.nextLine();
            System.out.println("Payment method: ");
            pm = scanner2.nextLine();
        } while (username==null||username.isEmpty()||postation == null || postation.isEmpty() || date == null || date.isEmpty()); 

        

        return new String[] {username, postation, date, pm};

    } 
}

//javac -cp ../../lib/postgresql-42.7.3.jar -d ../../out BusinessLogic/*.java DomainModel/*.java ORM/*.java Main.java

//java -cp ../../out:../../lib/postgresql-42.7.3.jar Main
