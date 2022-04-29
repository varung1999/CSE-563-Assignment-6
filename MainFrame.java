import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    private static final int DEFAULT_WINDOW_HEIGHT = 500;
    private static final int DEFAULT_WINDOW_WIDTH = 600;
    public static String temp;
    WorkSpace workSpace;
    WorkSpacePanel workSpacePanel;
    
    
    public MainFrame(){
        super();// set title over here
        
        workSpace = new WorkSpace();
        workSpacePanel = new WorkSpacePanel(workSpace);

        setLayout(new BorderLayout());

        add(workSpacePanel,BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu FileMenu = new JMenu("File");

        

        JLabel LogLabel = new JLabel("",JLabel.CENTER);

        JMenuItem SaveMenuItem = new JMenuItem("Save File");
        JMenuItem LoadMenuItem = new JMenuItem("Load File");

        
        //adding menuitems to menu 
        FileMenu.add(SaveMenuItem);
        FileMenu.add(LoadMenuItem);

        //adding menu to menu bar
        menuBar.add(FileMenu);

        //action listener to Save Menu
        SaveMenuItem.addActionListener(ev->{
            File selectedFile = displayFileSaveDialog();
            if(selectedFile!= null)
            {
                try{ // send the selected file to required place.
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

        //action listener to Load Menu
        LoadMenuItem.addActionListener(ev->{
            File selectedFile = displayFileSelectionDialog();
            if (selectedFile != null) {
                try { // send the selected file to the required place.
                }
                catch (IOException e) {
                    String msg = String.format("Failed To Load Data From File\nException: %s", e);
                    JOptionPane.showMessageDialog(this, msg);
                }
               
            }
            LogLabel.setText("Load Option from File menu is selected");
        });
        
        //adding to the frame
        add(menuBar,BorderLayout.NORTH);
        //adding log label to the frame
        add(LogLabel,BorderLayout.PAGE_END);
        
    }

    //private method for file selection
    private File displayFileSelectionDialog() {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            return jFileChooser.getSelectedFile();
        else return null;
    }

    //private method for File saving
    private File displayFileSaveDialog() {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
            return jFileChooser.getSelectedFile();
        else return null;
    }

    //main method
    public static void main(String args[])
    {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        mainFrame.setVisible(true);
    }
}