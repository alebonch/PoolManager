package DomainModel;
import DomainModel.Object;
import java.util.ArrayList;

public abstract class Object{
    protected int totalNumber, price;    
    protected String name;

    public Object(int total, int price, String name){
        this.totalNumber=total;
        this.price=price;
        this.name=name;
    }

    public String getName(){
        return name;
    }
    public int getNumber(){
        return totalNumber;
    }
    public int getPrice(){
        return price;
    }
    public String getInfo(){return null;}
    public static void printObjects(ArrayList<Object> objects){
        int i = 1;
        for(Object object:objects){
            System.out.printf("%d) " + object.getInfo() +"\n", i);
            i++;

    }
    }

}