package DAOExample_Wine;

public class Winery{
    private int winery_id;
    private String winery_name;
    private int viticultural_area_id;
    private int Offering_tours_flag;

    // constructor 1
    public Winery(){

    }

    // constructor 2
    public Winery(int winery_id, String winery_name, int viticultural_area_id, int Offering_tours_flag){
        this.winery_id = winery_id;
        this.winery_name = winery_name;
        this.viticultural_area_id = viticultural_area_id;
        this.Offering_tours_flag = Offering_tours_flag;
    }

    // getter and setter for winery_id
    public int getWineryID(){
        return winery_id;
    }

    public void setWineryID(int winery_id){
        this.winery_id = winery_id;
    }

    public String getWineryName(){
        return winery_name;
    }

    public void setWineryName(String winery_name){
        this.winery_name = winery_name;
    }

    public int getViticulturalAreaID(){
        return viticultural_area_id;
    }

    public void setViticulturalAreaID(int viticultural_area_id){
        this.viticultural_area_id = viticultural_area_id;
    }

    public int getOfferingToursFlag(){
        return Offering_tours_flag;
    }

    public void setOfferingToursFlag(int Offering_tours_flag){
        this.Offering_tours_flag = Offering_tours_flag;
    }
}
