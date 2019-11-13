import java.awt.Point;
import java.util.Stack;

public class UndoRedo {

	private Stack<Point[]> undo;
	private Stack<Point[]> redo;
	
	public UndoRedo() {
		undo = new Stack<Point[]>();
		redo = new Stack<Point[]>();
	}
	
	public Point[] undoMove() {
		redo.add(undo.pop());
		return undo.peek();
	}
	
	public void addMove(Point initial, Point fin) {
		redo.clear();
		Point[] move = {initial,fin};
		undo.add(move);
	}
	
	public Point[] redoMove() {
		undo.add(redo.pop());
		return undo.peek();
	}
}
