package common.exception;

/**
 * The ProcessInvoiceException wraps all unchecked exceptions You can use this
 * exception to inform
 *
 * @author Tran Thi Hang
 * @version 1.0
 */
public class ProcessInvoiceException extends EcobikeException {

    private static final long serialVersionUID = 1091337136123906298L;

    /**
	 * Exception Construction
	 */
    public ProcessInvoiceException() {

    }

    /**
	 * Exception Construction
	 * @param String message
	 */
    public ProcessInvoiceException(String message) {
        super(message);
    }

}
