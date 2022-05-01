import java.util.*;
import javax.swing.JPanel;

/**
 * The class WorkSpace consists of two lists one for the points - pointList and one for the lines - lineList.
 * @author Pragada Anurag - 1223151007
 * @author Gollapalli Varun - 1223130805
 * @author Amulya Bodla - 1219477220
 * @author Piyush Mudireddy - 1219456537
 */
public class WorkSpace{

    ArrayList<Point> pointList = new ArrayList<>();   
    ArrayList<Line> lineList = new ArrayList<>();

    /**
     * The method addPoint takes the input as a point and just adds the point to the pointList.
     * @param point
     */
    public void addPoint(Point point){
        pointList.add(point);
    }

    /**
     * The method addLine takes the input as a line and just adds the line to the lineList.
     * @param line
     */
    public void addLine(Line line) {
    	lineList.add(line);
    }

}