public class NodeT {
	String text;
	boolean qa; // true = question, false= answer
	NodeT YES; // The node for left/right is yes/no
	NodeT NO;
}