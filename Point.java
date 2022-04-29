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
        g.drawOval(x,y,8,8);
        g.setColor(Color.black);
    }
    
}
