package entity.station;

public class Station {
    private int numEmptyDockPoint;

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
