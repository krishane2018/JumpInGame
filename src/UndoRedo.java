import java.util.Stack;

public class UndoRedo {

	private Stack<Move> undo;
	private Stack<Move> redo;
	
	public UndoRedo() {
		undo = new Stack<Move>();
		redo = new Stack<Move>();
	}
	
	public Move undoMove() {
		redo.add(undo.pop());
		return undo.peek();
	}
	
	public void addMove(Move move) {
		redo.clear();
		undo.add(move);
	}
	
	public Move redoMove() {
		undo.add(redo.pop());
		return undo.peek();
	}
}
