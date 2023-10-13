package exercise.exception;

// BEGIN
public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String ex){
        super(ex);
    }
}
// END
