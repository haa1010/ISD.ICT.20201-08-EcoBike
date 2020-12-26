package common.exception;
/**
 * This exception is thrown when there is error related to viewing bike
 * @author Tran Thi Hang
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
   	 * @param message
   	 */
    public ViewBikeException(String message) {
        super(message);
    }
}
