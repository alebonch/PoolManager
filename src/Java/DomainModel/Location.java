package DomainModel;

public class Location{
    private int id;
    private String description;

    public Location(int id, String description){
        this.id=id;
        this.description=description;
    }
    public int getId(){
        return id;
    }
    public String getDesc(){
        return description;
    }
    public String getInfo(){
        String info = String.format("| Location's ID: %d", id);
        info+=(" | Description: "+description);
        return info;
    }
}