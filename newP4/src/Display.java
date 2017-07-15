
/*Name: Phu Pham
 *Course: CSE223 
 *Comment: The assignment is based on the "20 Questions" game to import a new data into the file.Using Swing to make it familiar to users
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
 

public class Display extends JFrame {
	  private JMenuBar menuBar; // Declare for menu bar
	  private JMenuItem Exit,Reset;
	  private JMenu File;
	  private JPanel Pane; // declare the Panel		
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Display frame = new Display();  //Run the program.
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    Tree t; // declare a Node of tree
    File NameFile= new File( "Questions"); // The text file to read/ write
  
    File file = new File("Questions");
    NodeT temp,temproot,question; // nodes for temporary values
    boolean answer=false;
    boolean prev= false;
    //-------------------------------------------------
    public Display() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);// cannot change the size of Panel
        setBounds(100, 100, 664, 508);
        Pane = new JPanel();
        Pane.setBorder(new EmptyBorder(5, 5, 5, 5));
        Pane.setBackground(Color.BLACK);
        setContentPane(Pane);
        Pane.setLayout(null);   // Set up the Panel

        menuBar = new JMenuBar(); setJMenuBar(menuBar); // set up a menu
        File= new JMenu("File"); menuBar.add(File);
        
        Exit = new JMenuItem("Exit"); File.add(Exit); // the item Exit in menu
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
       
        JButton Yes = new JButton("YES");            // Set up button Yes with decoration
        Yes.setFont(new Font("Arial",Font.PLAIN,70));
        Yes.setBackground(Color.ORANGE);
        Yes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        Yes.setBounds(10, 222, 240, 200);
        Pane.add(Yes);             // add it to a Panel
        	

        JButton No = new JButton("NO");              // Set up button No with decoration
        No.setFont(new Font("Arial",Font.PLAIN,70));
        No.setBackground(Color.BLUE);
        No.setBounds(390, 222, 240, 200);
        Pane.add(No); // Add it to a Panel

        JTextField textPane = new JTextField();   // The area for appearing question and type a new question.
        textPane.setBounds(44, 30, 548, 79);
        textPane.setFont(new Font("Arial",Font.PLAIN,20));
        Pane.add(textPane);// Add it to a Panel

        JLabel Choice = new JLabel("Make a choice"); // The line 
        Choice.setBounds(207, 150, 261, 61);
        Choice.setFont(new Font("Arial",Font.PLAIN,40));
        Choice.setForeground(Color.WHITE);
        Pane.add(Choice);// Add it to a Panel

        JButton NewText = new JButton("New"); // Button for add a new text.
        NewText.setBounds(207, 120, 261, 34);
        Pane.add(NewText);
        NewText.setBackground(Color.GRAY);
        NewText.setFont(new Font("Arial",Font.PLAIN,30));
        NewText.setEnabled(false);
        
        NodeT tempA=new NodeT();
        NodeT tempQ= new NodeT();

        Reset = new JMenuItem("Reset"); File.add(Reset); // a reset program button.
        Reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Display();
                NewText.setEnabled(false);Yes.setEnabled(true);No.setEnabled(true);
                
               Display a= new Display(); //show a display again. reset.
               a.setVisible(true);
           
            }
        });  
        
        //----------------------------------------------------------

        try {
            Scanner sc = new Scanner(NameFile); // Scan a text file
            t=new Tree();           //declare a tree.
            t.QTree(sc);        	// Start the tree by a question
            temp=t.root;
            temproot=t.root;	// set 2 temp value as a root
            question=temp;			
            System.out.println(temp.text); //Show the question
            textPane.setText(temp.text);}	//Show the question inside system.
            catch (FileNotFoundException e) {
            e.printStackTrace();}

        Yes.addActionListener(new ActionListener() { // Action in button YES
            @Override
            public void actionPerformed(ActionEvent e) {
                prev=true;
                if(temp.YES==null){   // If it reach to the bottom of a tree YES with an empty node
                    textPane.setText("YOU ARE WINNER");
                    NewText.setEnabled(false);Yes.setEnabled(false);No.setEnabled(false);// This disable buttons prompt user click reset.

                }
                else {temp = temp.YES; // If it doesnt reach a bottom of tree reach to the YES nodes parts.
                	if(temp.qa==true)
                			question=temp; // make a temp value question to check later
                    System.out.println(temp.text); 
                	textPane.setText(temp.text);} //display the current text node on screen.
            }
        });
        
        No.addActionListener(new ActionListener() {// Action in button NO
            @Override
            public void actionPerformed(ActionEvent e) {
                if (temp.NO==null){ // If it reach to the bottom of a tree NO with an empty node
              
                    textPane.setText("Answer a new question & click New: "); // Asking for a new question on screen
                    Yes.setEnabled(false); No.setEnabled(false); NewText.setEnabled(true); // make sure user only add a new data.



            NewText.addActionListener(new ActionListener() { // Action after No ,adding new question on part No, then ASking the quesion on part Yes
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (answer == false) { // if it doesnt find the answer
                    	// set data to node tempA
                        tempA.text = textPane.getText(); //get a text into Node tempA
                        tempA.qa = false;
                        tempA.YES = tempA.NO = null; //temp A just the main node without branches
                        System.out.println("Answer: " + tempA.text);
                        textPane.setText("Get YES question to get a new question & click New: ");
                        answer = true;
                        }
                    NewText.addActionListener(new ActionListener() { // after adding question on Yes part
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            NewText.setEnabled(false);// turn of a "NEW" button
                            tempQ.text = textPane.getText();//get a text from a question
                            tempQ.qa = true; //set a Node tempQ
                            tempQ.YES = tempA;// it will be Node tempA which have the answer
                            tempQ.NO = temp;
                            System.out.println("Question: " + tempQ.text); //show inside system
                            if (prev == true) //switching between question and answer
                                question.YES = tempQ; 
                            else question.NO = tempQ;

                            try {
                                FileWriter w = new FileWriter(NameFile); 
                                t.Write(w, temproot); //write in into a text file for new information
                                w.close(); // close file
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            textPane.setText("New data is updated. Reset and start again.");
                            return;
                        }
                });
            }
        });
    }
        else { // if temp.No is not empty
                prev=false;
                temp=temp.NO; // keep tranverse the tree till it reach the bottom
                if(temp.qa==true) // if it's a question
                    {question=temp;} // node question stored
                
                    textPane.setText(temp.text);// Display the content
            }
            }
            });
    }}