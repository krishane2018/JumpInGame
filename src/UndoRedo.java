import java.util.Stack;

public class UndoRedo {

	private Stack<JumpInEvent> undo;
	private Stack<JumpInEvent> redo;
	
	public UndoRedo() {
		undo = new Stack<JumpInEvent>();
		redo = new Stack<JumpInEvent>();
	}
	
	public JumpInEvent undoMove() {
		redo.add(undo.pop());
		return undo.peek();
	}
	
	public void addMove(JumpInEvent e) {
		redo.clear();
		undo.add(e);
	}
	
	public JumpInEvent redoMove() {
		undo.add(redo.pop());
		return undo.peek();
	}
}
