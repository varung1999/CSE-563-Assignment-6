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
    
    
    public void setLine(int ind1,int ind2,Color color) {
    	int x1=workSpace.pointList.get(ind1).x;
    	int y1=workSpace.pointList.get(ind1).y;
    	int x2=workSpace.pointList.get(ind2).x;
    	int y2=workSpace.pointList.get(ind2).y;
    	
    	workSpace.lineList.add(new Line(x1,y1,x2,y2,color));
    }
    
    
    public double euclideanDist(int ind1,int ind2) {
    	int x1=workSpace.pointList.get(ind1).x;
    	int y1=workSpace.pointList.get(ind1).y;
    	int x2=workSpace.pointList.get(ind2).x;
    	int y2=workSpace.pointList.get(ind2).y;
    	
    	double ac = Math.abs(y2 - y1);
        double cb = Math.abs(x2 - x1);
            
        return Math.hypot(ac, cb);
    }
    
    public void Clustering() {
    	
    	ArrayList<Boolean> visited=new ArrayList<>();
    	int maxPoints=workSpace.pointList.size();
    	int visitedPoints=0;
    	int givenDist = 100;
    	for(int i=0;i<maxPoints;i++) {
    		visited.add(false);
    	}
    	while(visitedPoints<maxPoints) {
    		int i=0;
    		Random rnd = new Random();
    		
    		Color randColor = new Color(rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
    		
    		while(visited.get(i)) {
    			i++;
    		}
    		
    		Queue<Integer> toVisit=new LinkedList<>();
    		toVisit.offer(i);
    		try {
    			while(toVisit.size()>0) {
	    			int index = toVisit.poll();
	    			
	        		visited.set(index, true);
	        		visitedPoints++;
	        		
	        		
	    			for(int j=0;j<maxPoints;j++) {
	    				if(visited.get(j))
	    					continue;
	    				else if(euclideanDist(index,j)<=givenDist) {
	    					setLine(i,j,randColor);
	    					repaint();
	    					toVisit.offer(j);
	    				}
	    			}
    			}
				//TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				System.out.println("ERROR HERE");
				e.printStackTrace();
			}
    	}
    		JOptionPane.showMessageDialog(null, "RUN COMPLETED", "Messageeeer", JOptionPane.INFORMATION_MESSAGE);
    	
    	
    	System.out.println("POINTS");
    	for(Point p:workSpace.pointList) {
    		System.out.println(p.x+","+p.y);
    	}
    	System.out.println("LINES");
    	for(Line l:workSpace.lineList) {
    		System.out.println(l.x1+","+l.y1+"--"+l.x2+","+l.y2);
    	}
    	
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
