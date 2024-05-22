import BusinessLogic.AdminController;
import BusinessLogic.LoginController;
import BusinessLogic.UserController;
import DomainModel.Postation;
import DomainModel.Reservation;
import DomainModel.Typology;
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
        loginController.print();
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
    private static void handleAdminTypologiesAction()throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        AdminController adminController = new AdminController();
        String input;
        do { 
            System.out.println(
                    """
                    \s
                     Admin Typologies Actions
                     1. View typologies
                     2. Add typology
                     3. Remove typology
                     4. Change typology's attributes
                     5. Back to main menu
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    ArrayList<Typology> typologies = adminController.getAllTypologies();
                    for (Typology typology : typologies) {
                        System.out.println(typology.getInfo());
                    }
                }
                case "2" -> {
                    String[] data = addTypo();
                    adminController.AddTypology(data[5],Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3], Boolean.parseBoolean(data[4]));
                }
                case "3" -> {
                    System.out.println("Insert the id of the typology you want to remove:");
                    String input1 = scanner.nextLine();
                    adminController.removeTypology(input1);
                }
                case "4" -> {
                    handleAdminTypologiesUpdateAction();
                }
                case "5" -> handleAdminAction();
            }
        } while (true);
    }
    private static void handleAdminTypologiesUpdateAction()throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        AdminController adminController = new AdminController();
        String input;
        do { 
            System.out.println(
                    """
                    \s
                     Update Typologies Actions
                     1. Change number of chairs
                     2. Change number of deckchairs
                     3. Change number of sunbeds
                     4. Change material of sunbeds
                     5. Add/remove gazebo
                     6. Back to Typology menu
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    System.out.println("Insert the id of the typology you want to change (n_chairs):");
                    String id = scanner.nextLine();
                    System.out.println("Insert the new number of chairs for the Typology: ");
                    String n_chairs = scanner.nextLine();
                    adminController.updateChairs(id, Integer.parseInt(n_chairs));
                }
                case "2" -> {
                    System.out.println("Insert the id of the typology you want to change (n_deckchairs):");
                    String id = scanner.nextLine();
                    System.out.println("Insert the new number of deckchairs for the Typology: ");
                    String n_deckchairs = scanner.nextLine();
                    adminController.updateDeckchairs(id, Integer.parseInt(n_deckchairs));
                }
                case "3" -> {
                    System.out.println("Insert the id of the typology you want to change (n_subdeds):");
                    String id = scanner.nextLine();
                    System.out.println("Insert the new number of sunbeds for the Typology: ");
                    String n_sunbeds = scanner.nextLine();
                    adminController.updateSunbeds(id, Integer.parseInt(n_sunbeds));
                }
                case "4" -> {
                    System.out.println("Insert the id of the typology you want to change (m_sunbeds):");
                    String id = scanner.nextLine();
                    adminController.removeTypology(id);
                    System.out.println("Insert the new material of the sunbeds for the Typology: ");
                    String m_sunbeds = scanner.nextLine();
                    adminController.updateMaterialSunbeds(id, m_sunbeds);
                }
                case "5" -> {
                    System.out.println("Insert the id of the typology you want to change (gazebo):");
                    String id = scanner.nextLine();
                    adminController.updateGazebo(id);
                }
                case "6" -> handleAdminTypologiesAction();
            }
        } while (true);
    }
    private static void handleAdminPostationsAction()throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        AdminController adminController = new AdminController();
        String input;
        do { 
            System.out.println(
                    """
                    \s
                     Admin Postation Actions
                     1. View postations
                     2. Add postation
                     3. Remove postation
                     4. Change attributes of postations
                     5. Back to Admin menu
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    ArrayList<Postation> postations = adminController.getAllPostations();
                    for (Postation postation : postations) {
                        System.out.println(postation.getInfo());
                    }
                }
                case "2" -> {
                    String[] data = addPostation();
                    adminController.addPostation(Integer.parseInt(data[0]), data[2], data[1]);
                }
                case "3" -> {
                    System.out.println("Insert the number of the postation you want to remove:");
                    String number = scanner.nextLine();
                    adminController.removePostation(Integer.parseInt(number));
                }
                case "4" -> handleAdminPostationsUpdateAction();
                
                case "5" -> handleAdminAction();
            }
        }while (true);
    }

    private static void handleAdminPostationsUpdateAction() throws ClassNotFoundException, SQLException{Scanner scanner = new Scanner(System.in);
        AdminController adminController = new AdminController();
        String input;
        do { 
            System.out.println(
                    """
                    \s
                     Update Postation Actions
                     1. Change typology
                     2. Change zone
                     3. Change availability
                     4. Back to Postation menu
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    System.out.println("Insert the number of the postation you want to change (typology):");
                    String postation = scanner.nextLine();
                    System.out.println("Insert the new typology for the Postation: ");
                    String typo = scanner.nextLine();
                    adminController.updateTypology(typo, Integer.parseInt(postation));
                }
                case "2" -> {
                    System.out.println("Insert the number of the postation you want to change (zone):");
                    String postation = scanner.nextLine();
                    System.out.println("Insert the new zone for the Postation: ");
                    String zone = scanner.nextLine();
                    adminController.updateZone(Integer.parseInt(postation), zone);
                }
                case "3" -> {
                    System.out.println("Insert the number of the postation you want to change (zone):");
                    String postation = scanner.nextLine();
                    System.out.println("Insert true if the postation should be available, anything else otherwise: ");
                    String available = scanner.nextLine();
                    adminController.updateAvailability(Integer.parseInt(postation),Boolean.parseBoolean(available));
                }
                case "4" -> handleAdminPostationsAction();
            }
        } while (true);
    }

    private static void handleAdminReservationsAction() throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        AdminController adminController = new AdminController();
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
                    String[] data = addReservation();
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
                    System.out.println("Insert the username of the reservation: ");
                    String username = scanner.nextLine();
                    System.out.println("Insert the date of the reservation: ");
                    String date = scanner.nextLine();
                    adminController.removeReservation(username, date);
                }
                case "2" -> {
                    System.out.println("Insert the username of the reservations: ");
                    String username = scanner.nextLine();
                    adminController.removeAllReservationsByUser(username);
                }
                case "3" -> {
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
                     6. Back to Admin menu
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    System.out.println("Insert the username of the reservation: ");
                    String username = scanner.nextLine();
                    System.out.println("Insert the date of the reservation: ");
                    String date = scanner.nextLine();
                    ArrayList<Reservation> reservations = adminController.getDateUserReservations(date, username);
                    for (Reservation reservation : reservations){
                        System.out.println(reservation.getInfo());
                    }
                }
                case "2" -> {
                    System.out.println("Insert the postation of the reservation: ");
                    String posId = scanner.nextLine();
                    System.out.println("Insert the date of the reservation: ");
                    String date = scanner.nextLine();
                    ArrayList<Reservation> reservations = adminController.getDatePostationReservation(Integer.parseInt(posId), date);
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
                    ArrayList<Reservation> reservations = adminController.getDateReservations(date);
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
                case "1" -> {
                    ArrayList<Postation> postations = userController.getAvailablePostations();
                    for (Postation postation : postations) {
                        System.out.println(postation.getInfo());
                    }
                }
                case "2" -> handleUserReserveAction(user);
                case "3" -> handleUserProfileAction(user);
                case "4" -> handleLoginAction();
            }
        } while (true);
    } 

    private static void handleUserReserveAction(User user) throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();
        String input;
        do { 
            System.out.println(
                    """
                    \s
                     User Reservation Actions
                     1. Check user's reservation
                     2. Add reservation
                     3. Remove reservation
                     4. Update payment method
                     5. Log out
                   \s"""
            );
            input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    ArrayList<Postation> postations = userController.getAvailablePostations();
                    for (Postation postation : postations) {
                        System.out.println(postation.getInfo());
                    }
                }
                case "2" -> {
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
                    String date;
                    System.out.println("Insert the date of the day you want to cancel the reservation: ");
                    date = scanner.nextLine();
                    userController.removeReservation(user.getMail(), date);
                }
                case "4" -> {
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
    private static String[] addPostation(){
        Scanner scanner2 = new Scanner(System.in);

        // personal data
        System.out.println("\n Please provide the following information to add the postation:");
        //login
        String number, typo, zone;
        do { 
            System.out.println("Number: ");
            number = scanner2.nextLine();
            System.out.println("Typology: ");
            typo = scanner2.nextLine();
            System.out.println("Zone: ");
            zone = scanner2.nextLine();
        } while (number==null||number.isEmpty()||typo == null || typo.isEmpty() || zone == null || zone.isEmpty()); 

        

        return new String[] {number,typo,zone};

    } 
    private static String[] addReservation(){
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
