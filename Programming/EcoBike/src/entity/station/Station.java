package entity.station;

public class Station {
    private int numEmptyDockPoint;
    private String name;
    private  int id;
    private int numAvailableBike;


    public void setNumEmptyDockPoint(int numEmptyDockPoint) {
        this.numEmptyDockPoint = numEmptyDockPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumAvailableBike() {
        return numAvailableBike;
    }

    public void setNumAvailableBike(int numAvailableBike) {
        this.numAvailableBike = numAvailableBike;
    }

    public Station() {
    }

    public Station(int x) {

        try {
            this.numEmptyDockPoint = x;
        } catch (Exception e) {
            this.numEmptyDockPoint=-1;

        } ;
    }

    public int getNumEmptyDockPoint() {
        return this.numEmptyDockPoint;
    }
}
