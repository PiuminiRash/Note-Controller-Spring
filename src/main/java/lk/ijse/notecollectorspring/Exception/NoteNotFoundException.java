package lk.ijse.notecollectorspring.Exception;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException() {
        super();
    }
    public NoteNotFoundException(String message) {
        super(message);
    }
    public NoteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
