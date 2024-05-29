package DomainModel;

public class Chair extends Object{
    private String color;
    public Chair(String name, int totalNumber, String color, int price){
        super(totalNumber, price, name);
        this.color=color;
        
    }
    public String getColor(){
        return color;
    }
    public String getInfo(){
        String info= ("| Name: "+name+" | Color: "+color+" |");
        info += String.format(" Total number: %d |", totalNumber);
        return info;
    }
}