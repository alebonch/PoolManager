package DomainModel;

public class Sunbed extends Object{
    private String material;

    public Sunbed(String material, int totalNumber, int price, String name){
    super(totalNumber, price, name);
        this.material=material;
    }
    public String getInfo(){
        String info= ("| Name: "+name+" | Material: "+material+" |");
        info += String.format(" Total number: %d |", totalNumber);
        return info;
    }

    public int getNumber(){
        return totalNumber;
    }
    public String getMaterial(){
        return material;
    }
}