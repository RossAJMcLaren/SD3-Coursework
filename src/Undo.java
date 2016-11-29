/**
 * Created by Ross on 28/11/2016.
 */
public class Undo implements order{
    private Fleet newFleet;

    public Undo(Fleet newFleet)
    {
        this.newFleet = newFleet;
    }

    public void execute()
    {
        newFleet.undo();
    }
}
