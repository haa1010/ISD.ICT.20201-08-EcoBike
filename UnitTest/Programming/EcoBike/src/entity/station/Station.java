package entity.station;

public class Station {
    protected int id;
    protected String name;
    protected int numAvailableBike;
    protected float area;
    protected String address;
    private int numEmptyDockPoint;

    public Station() {}
    public Station(int x){
        this.numEmptyDockPoint = x;
    }
    public Station(int id,
                   String name,
                   int numAvailableBike,
                   int numEmptyDockPoint,
                   float area,
                   String address){
        this.id = id;
        this.name = name;
        this.numAvailableBike = numAvailableBike;
        this.numEmptyDockPoint = numEmptyDockPoint;
        this.area = area;
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumAvailableBike(int numAvailableBike) {
        this.numAvailableBike = numAvailableBike;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setArea(float area) {
        this.area = area;
    }
    public void setNumEmptyDockPoint(int numEmptyDockPoint) {
        this.numEmptyDockPoint = numEmptyDockPoint;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getNumAvailableBike() {
        return numAvailableBike;
    }
    public float getArea() {
        return area;
    }
    public String getAddress() {
        return address;
    }
    public int getNumEmptyDockPoint(){
        return this.numEmptyDockPoint;
    }
}
