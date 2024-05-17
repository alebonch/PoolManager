package DomainModel;

public class Reservation {
    private User user;
    private Position posto;
    private String data;
    private PaymentMethods pm;
    private String hourOfArrival;
    public Reservation(User user, Position postazione, PaymentMethods paymentMethods,String data, String hourOfArrival){
        this.user=user;
        this.posto=postazione;
        this.pm=paymentMethods;
        this.data=data;
        this.hourOfArrival= hourOfArrival;
    }
    public Reservation(User user, Position postazione, PaymentMethods paymentMethods,String data){
        this.user=user;
        this.posto=postazione;
        this.pm=paymentMethods;
        this.data=data;
        this.hourOfArrival = "Not inserted";
    }
    public Reservation(User user, Position postazione,String data){
        this.user=user;
        this.posto=postazione;
        this.pm = null;
        this.data=data;
        this.hourOfArrival = "Not inserted";
    }

    public String getHour(){
        return hourOfArrival;
    }

    public void setHour(String hour){
        this.hourOfArrival=hour;
    }

    public User getUser(){
        return user;
    }
    public String getData(){
        return data;
    }
    public Position getPosto(){
        return posto;
    }
    public PaymentMethods getPm(){
        return pm;
    }
    public String getInfo(){
        String info=user.getInfo();
        info += posto.getInfo();
        info+=pm.getInfo();
        info+= String.format("Date: %s  Hour of arrival: %s",
                data, hourOfArrival);
        return info;
    }
}
