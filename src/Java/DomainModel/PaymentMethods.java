package DomainModel;

public class PaymentMethods {
    private int id;
    private String name;

    public PaymentMethods(int id, String name){
        this.id=id;
        this.name=name;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getInfo(){
        String info=String.format("Id: %d    Name: %s",
        id,name);
        return info;
    }
}
