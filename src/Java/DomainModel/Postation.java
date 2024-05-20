package DomainModel;

public class Postation{
    private int id;
    private Typology typo;
    private String zone;
    private boolean availability;

    public Postation(int id, Typology type, String zone, boolean availability){
        this.id=id;
        this.typo=type;
        this.zone=zone;
        this.availability=availability;
    }
    public Postation(int id, Typology type, String zone){
        this.id=id;
        this.typo=type;
        this.zone=zone;
        this.availability=true;
    }

    public String getInfo(){
        String info = String.format("ID: %d   Typology: %d   Zone: %s  ",
                id, typo, zone);
        info += typo.getInfo();
        if (availability){
            info += " Postation is usable";
        }
        else{
            info += " Postation is under maintenance";
        }
        return info;
    }

    public Typology getTypo(){
        return typo;
    }
    public int getId(){
        return id;
    }
    public String getZone(){
        return zone;
    }

    public void setTypo(Typology typo){
        this.typo=typo;
    }
    public void changeAvailability(){
        availability = !availability;
    }
    public void changeZone(String zone){
        this.zone = zone;
    }
    public boolean getAvailability(){
        return availability;
    }
}
