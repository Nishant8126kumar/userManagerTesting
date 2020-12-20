package exceptions;



public class NotAllowedException extends UserException {

    public NotAllowedException() {
        super();
    }

    public NotAllowedException(String msg) {
        super(msg);
    }
}
