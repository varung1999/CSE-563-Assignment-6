import java.awt.Color;
import java.awt.Graphics;

public class Point {

    int x, y;

    public Point(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void draw(Graphics g)
    {
    	int x1=x-4;
    	int y1=y-4;
        g.fillOval(x1,y1,8,8);
        g.setColor(Color.black);
    }
    
}
