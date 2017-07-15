import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Tree {
    NodeT root; //declare a root

    NodeT ATree(Scanner sc) { // build a tree with an answer
    NodeT a = new NodeT();
        if (sc.nextLine().equals("A:")) { // move cursor to next Line check in text file if it is an answer
            a.qa = false; // declare it's an answer
            a.text = sc.nextLine();// take a text to a node
            a.YES = a.NO = null; //empty the branches of node
            return (a);} //return the tree node 

        a.qa=true; // the question
        a.text=sc.nextLine();// take a text to node
        a.YES= ATree(sc); // Scan on YES branches 
        a.NO=ATree(sc); // Scan on No branches 
        return (a); // return a tree node
    }

//-----------------------------------------
    void QTree(Scanner sc) { 
    	try{
        if (sc.nextLine().equals("Q:")) { // if the tree question
            root = new NodeT(); // a new node
            root.qa = true; // a question
            root.text = sc.nextLine(); // check next line
            root.YES =ATree(sc);//Scan on Yes branches 
            root.NO =ATree(sc);}//Scan on No branches 
        }catch(Exception e){System.out.println("No file");}}
//------------------------------------------
        NodeT Write(FileWriter w,NodeT root) // Write to a text file
            {NodeT x=root;//  start from a root node
                if(x.qa==false){ //if it's an answer
                    try {
                        w.write("A:\n"); //Write into a text file
                      
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
						w.write(x.text + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//Write into a text file
             x.YES=x.NO=null; return (x);
            }
                try {
                    w.write("Q:\n");//Write into a text file
                
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
					w.write(x.text + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//Write into a text file
                Write(w,x.YES);Write(w,x.NO);// Write recursively to branches.
                return (x);//return a complete root node.
            }
}