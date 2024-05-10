package DomainModel;

public class Postazione {
    private int id;
    private Tipologia typo;
    private String zone;

    public Postazione(int id, Tipologia type, String zone){
        this.id=id;
        this.typo=type;
        this.zone=zone;
    }

    public String getInfo(){
        String info = String.format("ID: %d   Tipologia: %d   Zona: %s  ",
                id, typo, zone);
        info += typo.getInfo();
        return info;
    }

    public Tipologia getTypo(){
        return typo;
    }
    public int getId(){
        return id;
    }
    public String getZone(){
        return zone;
    }
}
