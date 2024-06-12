package BusinessLogic;
import DomainModel.*;
import ORM.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReserveController{
    
    public boolean CheckValues(int n_chairs,int n_deckchairs,int n_sunbeds, int n_tables, int n_umbrellas, int date) throws SQLException, ClassNotFoundException{
        // Aggiungere calcolo per i valori parziali delle risorse
        ReservationDAO reservationDAO = new ReservationDAO();
        PostationDAO postationDAO = new PostationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByDate(date);
        int sumChairs = 0, sumDeckchairs = 0, sumSunbed = 0, sumTables = 0, sumUmbrellas = 0;
        for(Reservation reservation:reservations){
            int[] tmp = postationDAO.getUsedResources(reservation.getPosto().getId());
            sumChairs += tmp[0];
            sumDeckchairs += tmp[1];
            sumSunbed += tmp[2];
            sumTables += tmp[3];
            sumUmbrellas += tmp[4];
        }
        //Checker vero e proprio
        ObjectDAO objectDAO = new ObjectDAO();
        boolean result = true;
        ArrayList<DomainModel.Object> objects = objectDAO.getAllObjects();
        for(DomainModel.Object object: objects){
            if(object.getName().equals("chair")){
                if((object.getNumber()-sumChairs) < (n_chairs)){
                    System.out.println("Chairs out of bound"+Integer.toString((object.getNumber()-sumChairs)));
                    return false;
                }
            }
            if(object.getName().equals("deckchair")){
                if((object.getNumber()-sumDeckchairs) < (n_deckchairs)){
                    System.out.println("Deckchairs out of bound"+Integer.toString((object.getNumber()-sumDeckchairs)));
                    result = false;
                }
            }
            if(object.getName().equals("sunbed")){
                if((object.getNumber()-sumSunbed) < (n_sunbeds)){
                    System.out.println("Sunbeds out of bound"+Integer.toString((object.getNumber()-sumSunbed)));
                    result = false;
                }
            }
            if(object.getName().equals("table")){
                if((object.getNumber()-sumTables) < (n_tables)){
                    System.out.println("Tables out of bound: "+Integer.toString((object.getNumber()-sumTables)));
                    result = false;
                }
            }
            if(object.getName().equals("umbrella")){
                if((object.getNumber()-sumUmbrellas) < (n_umbrellas)){
                    System.out.println("Umbrellas out of bound"+Integer.toString((object.getNumber()-sumUmbrellas)));
                    result = false;
                }
            }
        }
        return result;
    }

    public void addReservation(int n_chairs,int n_deckchairs,int n_sunbeds, int n_tables, int n_umbrellas, int date, int location, String username) throws SQLException, ClassNotFoundException{
        if(CheckValues(n_chairs, n_deckchairs, n_sunbeds, n_tables, n_umbrellas, date)){
            PostationDAO postationDAO = new PostationDAO();
            int id = postationDAO.getCountPostations()+1;
            postationDAO.insertPostation(id, n_chairs, n_tables, n_umbrellas, n_deckchairs, n_sunbeds, location);
            ReservationDAO reservationDAO = new ReservationDAO();
            reservationDAO.addReservation(username, id, date);
        }
    }

    public void removeReservation(String username, int date) throws SQLException, ClassNotFoundException{
        ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByUserAndDate(username, date);
        ArrayList<Integer> postations = new ArrayList<>();
        for (Reservation reservation:reservations){
            postations.add(reservation.getPosto().getId());
        }
        reservationDAO.removeReservation(username, date);
        PostationDAO postationDAO = new PostationDAO();
        for(int postation:postations){
            postationDAO.removePos(postation);
        }
    }

    public void removeUserReservation(String username) throws SQLException, ClassNotFoundException{
        ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByUser(username);
        ArrayList<Integer> postations = new ArrayList<>();
        for (Reservation reservation:reservations){
            postations.add(reservation.getPosto().getId());
        }
        reservationDAO.removeAllReservationsByUser(username);
        PostationDAO postationDAO = new PostationDAO();
        for(int postation:postations){
            postationDAO.removePos(postation);
        }
    }

    public void removeAllReservationsByDate(int date)throws SQLException, ClassNotFoundException{
        ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByDate(date);
        ArrayList<Integer> postations = new ArrayList<>();
        for (Reservation reservation:reservations){
            postations.add(reservation.getPosto().getId());
        }
        reservationDAO.removeAllReservationsByDate(date);
        PostationDAO postationDAO = new PostationDAO();
        for(int postation:postations){
            postationDAO.removePos(postation);
        }
    }
    public void updateChairs(String username, int date, int n_chair) throws SQLException, ClassNotFoundException{
        ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByUserAndDate(username, date);
        ArrayList<Integer> postations = new ArrayList<>();
        for (Reservation reservation:reservations){
            postations.add(reservation.getPosto().getId());
        }
        PostationDAO postationDAO = new PostationDAO();
        for(int postation:postations){
            postationDAO.updateChairs(postation, n_chair);
        }
    }
    public void updateDeckchairs(String username, int date, int n_deckchair) throws SQLException, ClassNotFoundException{
        ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByUserAndDate(username, date);
        ArrayList<Integer> postations = new ArrayList<>();
        for (Reservation reservation:reservations){
            postations.add(reservation.getPosto().getId());
        }
        PostationDAO postationDAO = new PostationDAO();
        for(int postation:postations){
            postationDAO.updateDeckchairs(postation, n_deckchair);
        }
    }
    public void updateSunbeds(String username, int date, int n_sunbed) throws SQLException, ClassNotFoundException{
        ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByUserAndDate(username, date);
        ArrayList<Integer> postations = new ArrayList<>();
        for (Reservation reservation:reservations){
            postations.add(reservation.getPosto().getId());
        }
        PostationDAO postationDAO = new PostationDAO();
        for(int postation:postations){
            postationDAO.updateSunbed(postation, n_sunbed);
        }
    }
    public void updateTables(String username, int date, int n_table) throws SQLException, ClassNotFoundException{
        ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByUserAndDate(username, date);
        ArrayList<Integer> postations = new ArrayList<>();
        for (Reservation reservation:reservations){
            postations.add(reservation.getPosto().getId());
        }
        PostationDAO postationDAO = new PostationDAO();
        for(int postation:postations){
            postationDAO.updateTable(postation, n_table);
        }
    }
    public void updateUmbrellas(String username, int date, int n_umbrella) throws SQLException, ClassNotFoundException{
        ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByUserAndDate(username, date);
        ArrayList<Integer> postations = new ArrayList<>();
        for (Reservation reservation:reservations){
            postations.add(reservation.getPosto().getId());
        }
        PostationDAO postationDAO = new PostationDAO();
        for(int postation:postations){
            postationDAO.updateUmbrella(postation, n_umbrella);
        }
    }
    public int TimeRecordFixer(String date, String turno) throws SQLException, ClassNotFoundException{
        TimeRecordDAO timeRecordDAO = new TimeRecordDAO();
        if(timeRecordDAO.getTimeRecord(date, turno).isEmpty()){
            timeRecordDAO.addTimeRecord(date, turno);
            return timeRecordDAO.getTimeRecord(date, turno).get(0).getId();
        }
        return timeRecordDAO.getTimeRecord(date, turno).get(0).getId();
    }
}