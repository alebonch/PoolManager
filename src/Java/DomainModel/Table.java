package DomainModel;

public class Table extends Object{
    private String color, material;

    public Table(String color, String material, int totalNumber, int price, String name){
        super(totalNumber, price, name);
        this.color=color;
        this.material=material;
    }
    public String getColor(){
        return color;
    }
    public String getInfo(){
        String info= ("| Name: "+name+" | Color: "+color+" | Material: "+material+" |");
        info += String.format(" Total number: %d |", totalNumber);
        return info;
    }
}