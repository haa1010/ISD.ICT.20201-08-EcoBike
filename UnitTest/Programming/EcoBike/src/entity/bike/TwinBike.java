package entity.bike;

public class TwinBike extends Bike {
    /**
     * set default attribute for twin bike
     * @author hue
     */
    public TwinBike(){
        this.numSaddle=2;
        this.numPedal=2;
        this.numRearSeat=1;
        this.type="Twin bike";
        this.value=550000;
        this.coefficient=1.5;
    }
}
