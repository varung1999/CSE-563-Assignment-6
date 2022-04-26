import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    private static final int DEFAULT_WINDOW_HEIGHT = 500;
    private static final int DEFAULT_WINDOW_WIDTH = 600;
    
    public MainFrame(){
        super();// set title over here
        setLayout(new BorderLayout());

        WorkSpace workSpace = new WorkSpace();
        WorkSpacePanel workSpacePanel = new WorkSpacePanel(workSpace);
        add(workSpacePanel,BorderLayout.CENTER);
    }

    public static void main(String args[])
    {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        mainFrame.setVisible(true);
    }
}