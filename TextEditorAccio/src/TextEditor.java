import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // Declaring the properties of TextEditor
    JFrame frame;

    JMenuBar menuBar;
    JMenu file, edit;

    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;

    TextEditor(){
        // Initialize a frame
        frame = new JFrame();

        // Initialise MenuBar
        menuBar = new JMenuBar();

        // Initialize File Menu
        file = new JMenu("File");
        // Initialize Edit Menu
        edit = new JMenu("Edit");

        // Initialize File menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        // Add action listeners to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Initialize Edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // Adding action listeners to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Initialize text area to frame
        textArea = new JTextArea();

        // Set MenuBar to frame
        frame.setJMenuBar(menuBar);

        //Create content Pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //Create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add scroll pane to panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);
        //Set dimensions of frame
            frame.setBounds(100,100,400,400);
            frame.setTitle("Text Editor");
            frame.setVisible(true);
            frame.setLayout(null);

        // Set Menu to MenuBar
        menuBar.add(file);
        menuBar.add(edit);

        // Add MenuItems to fileMenu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Add MenuItems to editMenu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            //Perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            //Perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            //Perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            //Perform select all operation
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            //Perform close operation
            System.exit(0);
        }

        if(actionEvent.getSource() == openFile){
            //Open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //If we have clicked an open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Getting selected file
                File file = fileChooser.getSelectedFile();
                //Get the path of selected file
                String filePath = file.getPath();
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //Read contents of file line by line
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate+"\n";
                    }
                    //Set the output string to next area
                    textArea.setText(output);
                }
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile){
            //Initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            //Get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //Check if we have clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Create a file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //Write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    TextEditor textEditor = new TextEditor();
                }
            }
        }
        if(actionEvent.getSource() == newFile){
            TextEditor textEditor = new TextEditor();
        }
    }


    public static void main(String[] args){
        TextEditor textEditor = new TextEditor();
    }
}
