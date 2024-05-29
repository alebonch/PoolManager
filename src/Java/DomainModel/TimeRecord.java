//TODO TUTTA LA CLASSE
package DomainModel;

public class TimeRecord{
    private String date, turno;
    public TimeRecord(String date, String turno){
        this.date = date;
        this.turno = turno;
    }
    public String getDate(){
        return date;
    }
    public String getTurno(){
        return turno;
    }
}