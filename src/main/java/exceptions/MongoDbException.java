package exceptions;

public class MongoDbException extends UserException {

    public MongoDbException() {
        super();
    }

    public MongoDbException(String msg) {
        super(msg);
    }

    public MongoDbException(String msg, Exception e) {
        super(msg, e);
    }
}
