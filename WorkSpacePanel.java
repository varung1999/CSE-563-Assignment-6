import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * The class WorkSpacePanel is where all the mail logic is written such the clustering algorithm, load and save
 * functionalities, and the calculation of Eucledian distance between two points. This class extends the JPanel and
 * implements both MouseListener and MouseMotionListener
 * @author Pragada Anurag - 1223151007
 * @author Gollapalli Varun - 1223130805
 * @author Amulya Bodla - 1219477220
 * @author Piyush Mudireddy - 1219456537
 */

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

    /**
     * The paintComponent method is used at various points in the project by calling the repaint() method which is defined
     * in the Component class. This method helps in clearing the graphics and redraws the graphics on the panel.
     * @param g
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D graphics2 = (Graphics2D) g;
        for(Point p: workSpace.pointList)
        {
            p.draw(graphics2);
        }
        for(Line l:workSpace.lineList) {
        	l.draw(graphics2);
        }
    }

    /**
     * The method repaintLoadedPoints basically plots all the points which are present in the pointList on the canvas
     */
    public void repaintLoadedPoints()
    {
        repaint();
    }

    /**
     * The Load method takes in a file as input and reads the text file using readTextFile method and store the data as String.
     * Once this is done we split the data into individual lines because we store individual coordinates of the points in
     * each line. Then we store the coordinates into Point object.
     * @param file
     * @throws IOException
     */
    
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

    /**
     * The readTextFile method takes the input as file and using the BufferedReader we read the data from the file. We
     * also append the new line character after every line for the StringBuilder.
     * @param file
     * @return String
     * @throws IOException
     */
    private String readTextFile(File file) throws IOException
    {
        String lineText;
        StringBuilder fileTextStringBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((lineText = br.readLine()) != null) {
            fileTextStringBuilder.append(lineText).append("\n");
        }
        br.close();
        return fileTextStringBuilder.toString();
    }

    /**
     * The save method takes the file as input and using the BufferedWriter we write the point coordinates into the file
     * by iterating over the pointList where we have stored all the points.
     * @param file
     * @throws IOException
     */
    public void save(File file) throws IOException{

        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
        for(Point p: workSpace.pointList)
        {
            writer.write(String.format("X -" +p.getX() +"\tY -"+p.getY() +"\n"));
        }
        writer.close();
    }

    /**
     * The setLine method adds the coordinates of the points for which we need to draw the lines for into a list - LineList
     * It also adds color to the line drawn from the provided input color.
     * @param ind1
     * @param ind2
     * @param color
     */
    public void setLine(Point ind1,Point ind2, Color color) {
    	int x1=ind1.x;
    	int y1=ind1.y;
    	int x2=ind2.x;
    	int y2=ind2.y;
    	
    	workSpace.lineList.add(new Line(x1,y1,x2,y2,color));
    }

    /**
     * The euclideanDist method takes the input of two points and returns the distance value between the two points by using
     * math operations.
     * @param ind1
     * @param ind2
     * @return It returns the distance value of the two points.
     */
    public double euclideanDist(Point ind1,Point ind2) {
    	int x1=ind1.x;
    	int y1=ind1.y;
    	int x2=ind2.x;
    	int y2=ind2.y;
    	
    	double ac = Math.abs(y2 - y1);
        double cb = Math.abs(x2 - x1);
            
        return Math.hypot(ac, cb);
    }

    /**
     * The Clustering method takes the euclidean distance as input and forms the different clusters with the help of
     * two data structures. It also makes use of threads to ensure there is a delay between mutliple clusters. It also
     * picks a random color for the lines.
     * @param maxEuclidDist
     */
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
