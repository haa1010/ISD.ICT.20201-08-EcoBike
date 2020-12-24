package common.exception;

;

/**
 * The EcobikeException wraps all unchecked exceptions You can use this
 * exception to inform
 *
 * @author nguyenlm
 */
public class EcobikeException extends RuntimeException {

    public EcobikeException() {

    }

    public EcobikeException(String message) {
        super(message);
    }
}