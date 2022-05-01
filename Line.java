import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.concurrent.TimeUnit;

/**
 * The class Line has the method draw which would connect two points with a line between them.
 * @author Pragada Anurag - 1223151007
 * @author Gollapalli Varun - 1223130805
 * @author Amulya Bodla - 1219477220
 * @author Piyush Mudireddy - 1219456537
 */
public class Line {
	
	int x1;int y1;int x2;int y2;Color lineColor;
	
    public Line(int x1,int y1,int x2,int y2,Color lineColor)
    {
    	this.x1=x1;this.x2=x2;this.y1=y1;this.y2=y2;
        this.lineColor=lineColor;
    }

    /**
     * The draw method takes g as an input parameter and draws the line along with setting the color of the line.
     * @param g
     */
        
    public void draw(Graphics g)
    {
        g.setColor(lineColor);
      g.drawLine(x1, y1, x2, y2);
  	 }
    
}
