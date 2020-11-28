package entity.station;

public class Station {
    private int numEmptyDockPoint;
    public Station(){}
    public Station(int x){
        this.numEmptyDockPoint=x;
    }
public int getNumEmptyDockPoint(){
    return this.numEmptyDockPoint;
}
}
