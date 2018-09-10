package kalaha.game;

public class NotPlayersTurnException extends Throwable {
    public NotPlayersTurnException(){
        super("It is not that players turn");
    }
}
