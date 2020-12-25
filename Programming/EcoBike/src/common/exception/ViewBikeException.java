package common.exception;
/**
 * This exception is thrown when there is error related to viewing bike
 * @author hangtt
 * @version 1.0
 */
public class ViewBikeException extends EcobikeException {
	 /**
	  * Exception Construction
	  */
    public ViewBikeException() {

    }
    
    /**
   	 * Exception Construction
   	 * @param String message
   	 */
    public ViewBikeException(String message) {
        super(message);
    }
}
