package DomainModel;

public class Typology {
    private int n_sunbeds, n_chairs, n_deckchairs;
    private boolean gazebo;
    private String m_sunbeds, id;
    public Typology(String id, int n_chairs, int n_deckchairs, int n_sunbeds, String m_sunbeds, boolean gazebo){
        this.id=id;
        this.n_chairs=n_chairs;
        this.n_deckchairs=n_deckchairs;
        this.n_sunbeds=n_sunbeds;
        this.gazebo=gazebo;
        this.m_sunbeds=m_sunbeds;
    }
    public String getId(){
        return id;
    }
    public int getChairs(){
        return n_chairs;
    }
    public int getSunbed(){
        return n_sunbeds;
    }
    public int getDeckchairs(){
        return n_deckchairs;
    }
    public boolean getGazebo(){
        return gazebo;
    }
    public String getMaterial(){
        return m_sunbeds;
    }
    public String getInfo(){
        String info= String.format("| Id: %s |",
        this.id);
        if(n_chairs!=0)
            info+=String.format("  Sedie: %d |", this.n_chairs);
        if(n_deckchairs!=0)
            info+=String.format("  Sdraio: %d |", this.n_deckchairs);
        if(n_sunbeds!=0)
            info+=String.format("  Lettini di %s: %d |",this.m_sunbeds, this.n_sunbeds);
        if(gazebo)
            info += "   Gazebo |";
        return info;
    }
}
