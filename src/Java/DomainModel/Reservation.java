package DomainModel;
//TODO RIMUOVERE PAYMENTMETHODS E AGGIUNGERE TIME RECORDS E CONTROLLARE COME VENGONO GESTITE LE POSTAZIONI
public class Reservation {
    private User user;
    private Postation postation;
    private TimeRecord timeRecord;
    public Reservation(User user, Postation postazione,TimeRecord timeRecord){
        this.user=user;
        this.postation=postazione;
        this.timeRecord=timeRecord;
    }


    public User getUser(){
        return user;
    }
    public TimeRecord getData(){
        return timeRecord;
    }
    public Postation getPosto(){
        return postation;
    }

    public String getInfo(){
        String info=("| "+user.getMail()+" |");
        info += String.format(" Postation info: %s |", postation.getInfo());
        info+= String.format(" Date: %s | ",
                timeRecord.getDate());
        info+= ("Turn: "+timeRecord.getTurno());
        return info;
    }
}
