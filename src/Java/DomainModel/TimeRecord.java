//TODO TUTTA LA CLASSE
package DomainModel;

public class TimeRecord{
    private int id;
    private String date, turno;
    public TimeRecord(int id, String date, String turno){
        this.date = date;
        this.turno = turno;
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public String getDate(){
        return date;
    }
    public String getTurno(){
        return turno;
    }
}