package DomainModel;

public class Reservation {
    private User user;
    private Postation postation;
    private String date;
    private PaymentMethods pm;
    public Reservation(User user, Postation postazione, PaymentMethods paymentMethods,String data){
        this.user=user;
        this.postation=postazione;
        this.pm=paymentMethods;
        this.date=data;
    }
    public Reservation(User user, Postation postazione,String data){
        this.user=user;
        this.postation=postazione;
        this.pm = null;
        this.date=data;
    }

    public User getUser(){
        return user;
    }
    public String getData(){
        return date;
    }
    public Postation getPosto(){
        return postation;
    }
    public PaymentMethods getPm(){
        return pm;
    }
    public String getInfo(){
        String info=("| "+user.getMail()+" |");
        info += String.format(" Postation: %d |", postation.getId());
        if(pm!=null)
            info+=pm.getInfo();
        else
            info+=" Payment method: Not inserted |";
        info+= String.format(" Date: %s | ",
                date);
        return info;
    }
}
