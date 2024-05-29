package DomainModel;
import DomainModel.Object;
import java.util.ArrayList;

public class Postation{
    private int id;
    private ArrayList<Object> objects;
    private Location location;

    public Postation(int id, ArrayList<Object> type, Location zone){
        this.id=id;
        this.objects=type;
        this.location=zone;
    }

    public String getInfo(){
        String info = String.format("| ID: %d ",
                id);
        info += location.getInfo();
        for (Object object : objects) {
            info+=object.getInfo();   
        }
        return info;
    }
    public ArrayList<Object> getObjects(){
        return objects;
    }
    public int getId(){
        return id;
    }
    public Location getLocation(){
        return location;
    }
    public void addObject(Object object){
        objects.add(object);
    }
}
