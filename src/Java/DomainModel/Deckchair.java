package DomainModel;

public class Deckchair extends Object{
    private String color, material;

    public Deckchair(String color, String material, int totalNumber, String name, int price){
        super(totalNumber, price, name);
        this.color=color;
        this.material=material;
    }
    public String getColor(){
        return color;
    }
    public String getMaterial(){
        return material;
    }
    public String getInfo(){
        String info= ("| Name: "+name+" | Color: "+color+" | Material: "+material+" |");
        info += String.format(" Total number: %d |", totalNumber);
        return info;
    }
}