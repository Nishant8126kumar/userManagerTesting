package exceptions;


public class ResourceNotFoundException extends UserException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
