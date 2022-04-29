import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;


public class WorkSpacePanel extends JPanel implements MouseListener, MouseMotionListener {

    private final WorkSpace workSpace;
    int clickCount;

    public WorkSpacePanel(WorkSpace workSpace){
        this.workSpace=workSpace;
        addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        workSpace.addPoint(new Point(e.getX(), e.getY()));
        repaint(); 
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D graphics2 = (Graphics2D) g;
        for(Point p: workSpace.pointList)
        {
            p.draw(graphics2);
        }
    }

    public void repaintLoadedPoints()
    {
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void load(File file) throws IOException{

        workSpace.pointList.clear();
        String text = readTextFile(file);
        String[] line = text.split("\n");

        for(String lines: line)
        {
            String[] temp = lines.split(" ");
            Point tempPoint = new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
            workSpace.pointList.add(tempPoint);
        }
        repaint();
        
    }
    
    private String readTextFile(File file) throws IOException {
        String lineText;
        StringBuilder fileTextStringBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((lineText = br.readLine()) != null) {
            fileTextStringBuilder.append(lineText).append("\n");
        }
        br.close();
        return fileTextStringBuilder.toString();
    }
    
    public void save(File file) throws IOException{

        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
        for(Point p: workSpace.pointList)
        {
            writer.write(String.format("X -" +p.getX() +"\tY -"+p.getY() +"\n"));
        }
        writer.close();
    }
    
}
