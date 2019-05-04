import java.awt.*;
import java.util.EmptyStackException;

/**
 * Created by Весёлый студент on 17.09.2015.
 */
public class Train {

    private int length;
    private Coach coaches[];
    private Point location;
    private static int serialNumber = 1;

    public Train(int mlength){
        this.length = mlength;
        location = new Point(50,50);
        coaches = new Coach[mlength];
        for( int i = 0; i < mlength; i++)
            coaches[i] = new Coach(new Point(this.location.x + (i * 35), this.location.y));
        coaches[0].getOccupationBar().setStringPainted(true);
        coaches[0].getOccupationBar().setString(String.valueOf(serialNumber));
        serialNumber++;
    }

    synchronized public Coach getEmptyCoach(ItemType mtype) throws EmptyStackException {
        for(int i = 0; i < this.length; i++)
            if ((coaches[i].getType() == mtype) && ((Coach.getCapacity() - coaches[i].container.remainingCapacity()) < Coach.getCapacity()))
                return coaches[i];

        throw new EmptyStackException();
    }

    public Point getLocation(){return this.location;}
    public void setLocation(Point mlocation){this.location = mlocation;}

    public int getLength(){return this.length;}
    public Coach getCoach(int n){return coaches[n];}

}
