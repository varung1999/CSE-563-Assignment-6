import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The class MainFrame handles all the GUI related functionalities such as the listeners and consists of the menu options
 * such as the Load, save, clear , and run the randon generator. It also handles the file selection for load and save options.
 * @author Pragada Anurag - 1223151007
 * @author Gollapalli Varun - 1223130805
 * @author Amulya Bodla - 1219477220
 * @author Piyush Mudireddy - 1219456537
 */
public class MainFrame extends JFrame {

    private static final int DEFAULT_WINDOW_HEIGHT = 500;
    private static final int DEFAULT_WINDOW_WIDTH = 600;
    WorkSpace workSpace;
    WorkSpacePanel workSpacePanel;
    
    public MainFrame(){
        super();
        
        workSpace = new WorkSpace();
        workSpacePanel = new WorkSpacePanel(workSpace);

        setLayout(new BorderLayout());

        add(workSpacePanel,BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu FileMenu = new JMenu("File");
        JMenu RunMenu = new JMenu("Run");
        JMenu randomGenerator = new JMenu("Random Generator");

        JLabel LogLabel = new JLabel("",JLabel.CENTER);

        JMenuItem ClearCanvasItem = new JMenuItem("Clear Canvas");
        JMenuItem SaveMenuItem = new JMenuItem("Save File");
        JMenuItem LoadMenuItem = new JMenuItem("Load File");
        JMenuItem randomItem = new JMenuItem("Give Input");
        JMenuItem runnerItem = new JMenuItem("Run with cutom input");

        FileMenu.add(ClearCanvasItem);
        FileMenu.add(SaveMenuItem);
        FileMenu.add(LoadMenuItem);
        randomGenerator.add(randomItem);
        RunMenu.add(runnerItem);

        menuBar.add(FileMenu);
        menuBar.add(randomGenerator);
        menuBar.add(RunMenu);

        ClearCanvasItem.addActionListener(ev->{

            workSpace.pointList.clear();
            workSpace.lineList.clear();
            workSpacePanel.repaint();

        });

        SaveMenuItem.addActionListener(ev->{
            File selectedFile = displayFileSaveDialog();
            if(selectedFile!= null)
            {
                try{
                    workSpacePanel.save(selectedFile);
                }
                catch(IOException e)
                {
                    String message = String.format("Failed To Save Data To File\nException: %s", e);
                    JOptionPane.showMessageDialog(this, message);
                }
            }
            LogLabel.setText("Save option from File menu is clicked");
        });

        LoadMenuItem.addActionListener(ev->{
            File selectedFile = displayFileSelectionDialog();
            if (selectedFile != null) {
                try
                {
                    workSpacePanel.load(selectedFile);
                }
                catch (IOException e)
                {
                    String msg = String.format("Failed To Load Data From File\nException: %s", e);
                    JOptionPane.showMessageDialog(this, msg);
                }
               
            }
            LogLabel.setText("Load Option from File menu is selected");
        });

        randomItem.addActionListener(ev->{

        String temp = JOptionPane.showInputDialog(this, "Enter the number of random dots");

        int numberOfRandomDots = Integer.valueOf(temp);

        randomGeneratorHelper(numberOfRandomDots);

        });
        
        runnerItem.addActionListener(ev->{
        	String euclidDist = JOptionPane.showInputDialog(this, "Enter cluster euclidian  distance parameter value");
        	workSpace.lineList.clear();
        	workSpacePanel.Clustering(Integer.parseInt(euclidDist));
        });

        add(menuBar,BorderLayout.NORTH);
        add(LogLabel,BorderLayout.PAGE_END);
    }

    /**
     * The method displayFileSelectionDialog is used for selecting a file using JFileChooser.
     * @return It returns the file selected or it returns null
     */
    private File displayFileSelectionDialog()
    {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            return jFileChooser.getSelectedFile();
        else return null;
    }

    /**
     * The method displayFileSaveDialog is used for saving a file using JFileChooser.
     * @return It returns the file selected or it returns null
     */

    private File displayFileSaveDialog()
    {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
            return jFileChooser.getSelectedFile();
        else return null;
    }

    /**
     * The method randomGeneratorHelper is used to generate random points on the canvas(GUI).
     *
     */
    private void randomGeneratorHelper(int numberOfRandomDots)
    {
        for(int i =0;i<numberOfRandomDots;i++)
        {
            int x = (int) (Math.random()*599);
            int y = (int) (Math.random()*499);

            Point p = new Point(x, y);
            workSpace.pointList.add(p);
        }
        for(Point p:workSpace.pointList){

        	workSpacePanel.repaintLoadedPoints();
        }
    }

    /**
     * This is the driver method as the main method is the one which launches the GUI at the start of the program execution.
     * @param args
     */

    public static void main(String args[])
    {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        mainFrame.setVisible(true);
    }
}