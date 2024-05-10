package DomainModel;

public class Prenotazione {
    private User user;
    private Postazione posto;
    private String data;
    private PaymentMethods pm;
    public Prenotazione(User user, Postazione postazione, PaymentMethods paymentMethods,String data){
        this.user=user;
        this.posto=postazione;
        this.pm=paymentMethods;
        this.data=data;
    }

    public User getUser(){
        return user;
    }
    public String getData(){
        return data;
    }
    public Postazione getPosto(){
        return posto;
    }
    public PaymentMethods getPm(){
        return pm;
    }
    public String getInfo(){
        String info=user.getInfo();
        info += posto.getInfo();
        info+=pm.getInfo();
        info+= String.format("Data: %s",
                data);
        return info;
    }
}
