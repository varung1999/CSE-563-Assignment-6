import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


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
        System.out.println("Inside repaint component \n");
        for(Line l:workSpace.lineList) {
        	l.draw(graphics2);
        }
    }

    public void repaintLoadedPoints()
    {
        repaint();
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
    
    
    public void setLine(Point ind1,Point ind2, Color color) {
    	int x1=ind1.x;
    	int y1=ind1.y;
    	int x2=ind2.x;
    	int y2=ind2.y;
    	
    	
    	workSpace.lineList.add(new Line(x1,y1,x2,y2,color));
    }
    
    
    public double euclideanDist(Point ind1,Point ind2) {
    	int x1=ind1.x;
    	int y1=ind1.y;
    	int x2=ind2.x;
    	int y2=ind2.y;
    	
    	double ac = Math.abs(y2 - y1);
        double cb = Math.abs(x2 - x1);
            
        return Math.hypot(ac, cb);
    }
    
    public void Clustering(int maxEuclidDist) {
    	
    	Thread thread = new Thread(){
    	    public void run(){
    	    	int numOfPoints = workSpace.pointList.size();
    	    	HashSet<Point> hs = new HashSet<>();
    	    	Queue<Point> queue = new LinkedList<>();
    	    	for(int i=0;i<numOfPoints;i++)
    	    		hs.add(workSpace.pointList.get(i));
    	    	Random rnd = new Random();
    	    	while(!hs.isEmpty()) {
    	    		Point p = hs.iterator().next();
    	    		
    	    		Color randColor = new Color(rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
    	    		hs.remove(p);
    	    		queue.add(p);
    	    		while(!queue.isEmpty()) {
    	    			p = queue.poll();
	    	    		for(int i=0;i<numOfPoints;i++) {
	    	    			Point q = workSpace.pointList.get(i);
	    	    			if(hs.contains(q) && euclideanDist(q,p)<=maxEuclidDist) {
	    	    				queue.add(q);
	    	    				hs.remove(q);
	    	    				setLine(p,q,randColor);
	    	    				repaint();
	    	    				}
	    	    			}			
    	    		}
    	    		try {
    	    		    Thread.sleep(1L * 1000L);
    	    		} catch (InterruptedException e) {
    	    		    e.printStackTrace();
    	    		}
    	    	}
    	    	JOptionPane.showMessageDialog(null, "RUN COMPLETED", "Message", JOptionPane.INFORMATION_MESSAGE);   
    	    }
    	  };
    	  thread.start();
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
}
