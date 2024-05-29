package DomainModel;

public class Umbrella extends Object{
    private String color;

    public Umbrella(String color, int totalNumber, int price, String name){
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