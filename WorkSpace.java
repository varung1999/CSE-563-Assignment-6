import java.util.*;

import javax.swing.JPanel;

public class WorkSpace{

    ArrayList<Point> pointList = new ArrayList<>();   
    ArrayList<Line> lineList = new ArrayList<>();  
    public void addPoint(Point point){
        pointList.add(point);
    }    
    
    public void addLine(Line line) {
    	lineList.add(line);
    }
    
    
}