package BusinessLogic;
import DomainModel.Reservation;
import ORM.*;
import java.sql.SQLException;
import java.util.ArrayList;
public class ResourcesController {
    public int[] getAvailableResources(int date) throws SQLException, ClassNotFoundException{
        ObjectDAO objectDAO = new ObjectDAO();
        ArrayList<DomainModel.Object> objects = objectDAO.getAllObjects();
        int[] resources = new int[5];
        for (DomainModel.Object object:objects){
            if(object.getName().equals("chair")){
                resources[0]=object.getNumber();
            }
            if(object.getName().equals("deckchair")){
                resources[1]=object.getNumber();
            }
            if(object.getName().equals("sunbed")){
                resources[2]=object.getNumber();
            }
            if(object.getName().equals("table")){
                resources[3]=object.getNumber();
            }
            if(object.getName().equals("umbrella")){
                resources[4]=object.getNumber();
            }
        }
        int[] usedResources = new int[5];
        ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByDate(date);
        PostationDAO postationDAO = new PostationDAO();
        usedResources[0]=0;
        usedResources[1]=0;
        usedResources[2]=0;
        usedResources[3]=0;
        usedResources[4]=0;
        for(Reservation reservation:reservations){
            usedResources[0]+=postationDAO.getUsedResources(reservation.getPosto().getId())[0];
            usedResources[1]+=postationDAO.getUsedResources(reservation.getPosto().getId())[1];
            usedResources[2]+=postationDAO.getUsedResources(reservation.getPosto().getId())[2];
            usedResources[3]+=postationDAO.getUsedResources(reservation.getPosto().getId())[3];
            usedResources[4]+=postationDAO.getUsedResources(reservation.getPosto().getId())[4];
        }
        for (int i=0; i<5;i++){
            resources[i]-=usedResources[i];
        }
        return resources;
    }
    public boolean CheckValues(int n_chairs,int n_deckchairs,int n_sunbeds, int n_tables, int n_umbrellas, int date) throws SQLException, ClassNotFoundException{
        // Prendo il numero di risorse utilizzate
        ReservationDAO reservationDAO = new ReservationDAO();
        PostationDAO postationDAO = new PostationDAO();
        ArrayList<Reservation> reservations = reservationDAO.SelectReservationsByDate(date);
        int sumChairs = 0, sumDeckchairs = 0, sumSunbed = 0, sumTables = 0, sumUmbrellas = 0;
        int[] tmp;
        for(Reservation reservation:reservations){
            tmp = postationDAO.getUsedResources(reservation.getPosto().getId());
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
    public void updateChairs(int n_chairs){
        ObjectDAO objectDAO = new ObjectDAO();
        objectDAO.updateTotalNumber(n_chairs, "chair");
    }
    public void updateDeckchairs(int n_deckchairs){
        ObjectDAO objectDAO = new ObjectDAO();
        objectDAO.updateTotalNumber(n_deckchairs, "deckchair");
    }
    public void updateSunbeds(int n_sunbeds){
        ObjectDAO objectDAO = new ObjectDAO();
        objectDAO.updateTotalNumber(n_sunbeds, "sunbed");
    }
    public void updateTables(int n_tables){
        ObjectDAO objectDAO = new ObjectDAO();
        objectDAO.updateTotalNumber(n_tables, "table");
    }
    public void updateUmbrellas(int n_umbrellas){
        ObjectDAO objectDAO = new ObjectDAO();
        objectDAO.updateTotalNumber(n_umbrellas, "umbrella");
        
    }
    public ArrayList<DomainModel.Object> getResources() throws SQLException, ClassNotFoundException{
        ObjectDAO objectDAO = new ObjectDAO();
        return objectDAO.getAllObjects();
    }
}