import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
        g.fillOval(x,y,5,5);
        g.setColor(Color.black);
    }
    
}
