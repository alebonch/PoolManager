package BusinessLogic;
import DomainModel.*;
import ORM.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReserveController{
    
    public void Reserve(int n_chairs,int n_deckchairs,int n_sunbeds, int n_tables, int n_umbrellas, String date, String turn, int location, String username) throws SQLException, ClassNotFoundException{
        ResourcesController resourcesController = new ResourcesController();
        if(resourcesController.CheckValues(n_chairs, n_deckchairs, n_sunbeds, n_tables, n_umbrellas, date)){
            PostationDAO postationDAO = new PostationDAO();
            int id = postationDAO.getCountPostations()+1;
            postationDAO.insertPostation(id, n_chairs, n_tables, n_umbrellas, n_deckchairs, n_sunbeds, location);
            ReservationDAO reservationDAO = new ReservationDAO();
            reservationDAO.addReservation(username, id, TimeRecordFixer(date, turn));
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
    public void updateChairs(String username, String date, String turno, int n_chair) throws SQLException, ClassNotFoundException{
        ResourcesController resourcesController = new ResourcesController();
        if(resourcesController.CheckValues(n_chair, 0, 0, 0, 0, date))
        {ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByUserAndDate(username, TimeRecordFixer(date, turno));
        ArrayList<Integer> postations = new ArrayList<>();
        for (Reservation reservation:reservations){
            postations.add(reservation.getPosto().getId());
        }
        PostationDAO postationDAO = new PostationDAO();
        for(int postation:postations){
            postationDAO.updateChairs(postation, n_chair);
            reservationDAO.updateCosts(postation, TimeRecordFixer(date, turno));
        }}
    }
    public void updateDeckchairs(String username, String date, String turn, int n_deckchair) throws SQLException, ClassNotFoundException{
        ResourcesController resourcesController = new ResourcesController();
        if(resourcesController.CheckValues(0, n_deckchair, 0, 0, 0, date)){
        ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByUserAndDate(username, TimeRecordFixer(date, turn));
        ArrayList<Integer> postations = new ArrayList<>();
        for (Reservation reservation:reservations){
            postations.add(reservation.getPosto().getId());
        }
        PostationDAO postationDAO = new PostationDAO();
        for(int postation:postations){
            postationDAO.updateDeckchairs(postation, n_deckchair);
            reservationDAO.updateCosts(postation, TimeRecordFixer(date, turn));
        }}
    }
    public void updateSunbeds(String username, String date, String turn, int n_sunbed) throws SQLException, ClassNotFoundException{
        ResourcesController resourcesController = new ResourcesController();
        if(resourcesController.CheckValues(0, 0, n_sunbed, 0, 0, date)){ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByUserAndDate(username, TimeRecordFixer(date, turn));
        ArrayList<Integer> postations = new ArrayList<>();
        for (Reservation reservation:reservations){
            postations.add(reservation.getPosto().getId());
        }
        PostationDAO postationDAO = new PostationDAO();
        for(int postation:postations){
            postationDAO.updateSunbed(postation, n_sunbed);
            reservationDAO.updateCosts(postation, TimeRecordFixer(date, turn));
        }}
    }
    public void updateTables(String username, String date, String turn, int n_table) throws SQLException, ClassNotFoundException{
        ResourcesController resourcesController = new ResourcesController();
        if(resourcesController.CheckValues(0, 0, 0, n_table, 0, date))
        {ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByUserAndDate(username, TimeRecordFixer(date, turn));
        ArrayList<Integer> postations = new ArrayList<>();
        for (Reservation reservation:reservations){
            postations.add(reservation.getPosto().getId());
        }
        PostationDAO postationDAO = new PostationDAO();
        for(int postation:postations){
            postationDAO.updateTable(postation, n_table);
            reservationDAO.updateCosts(postation, TimeRecordFixer(date, turn));
        }}
    }
    public void updateUmbrellas(String username, String date, String turn, int n_umbrella) throws SQLException, ClassNotFoundException{
        ResourcesController resourcesController = new ResourcesController();
        if(resourcesController.CheckValues(0, 0, 0, 0, n_umbrella, date))
        {ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByUserAndDate(username, TimeRecordFixer(date, turn));
        ArrayList<Integer> postations = new ArrayList<>();
        for (Reservation reservation:reservations){
            postations.add(reservation.getPosto().getId());
        }
        PostationDAO postationDAO = new PostationDAO();
        for(int postation:postations){
            postationDAO.updateUmbrella(postation, n_umbrella);
            reservationDAO.updateCosts(postation, TimeRecordFixer(date, turn));
        }}
    }
    public int TimeRecordFixer(String date, String turno) throws SQLException, ClassNotFoundException{
        TimeRecordDAO timeRecordDAO = new TimeRecordDAO();
        if(timeRecordDAO.getTimeRecord(date, turno) == null){
            timeRecordDAO.addTimeRecord(date, turno);
            return timeRecordDAO.getTimeRecord(date, turno).getId();
        }
        return timeRecordDAO.getTimeRecord(date, turno).getId();
    }
}