import java.util.Stack;

public class UndoRedo {

	private Stack<JumpInEvent> undo;
	private Stack<JumpInEvent> redo;
	private boolean state;
	
	public UndoRedo() {
		undo = new Stack<JumpInEvent>();
		redo = new Stack<JumpInEvent>();
		state = false;
	}
	
	public JumpInEvent undoMove() {
		if(!undo.isEmpty()) {
			JumpInEvent e = undo.pop();
			redo.add(e);
			state = true;
			return e;
		}
		return new JumpInEvent();
		
	}
	
	public void addMove(JumpInEvent e) {
		if(!state) {
			redo.clear();
			undo.add(e);
		}
	}
	
	/**
	 * @return the state
	 */
	public boolean isState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	public JumpInEvent redoMove() {
		if(!redo.isEmpty()) {
			JumpInEvent e = redo.pop();
			undo.add(e);
			return e;
		}
		return new JumpInEvent();
	}

	public Stack<JumpInEvent> getUndo() {
		return undo;
	}

	public Stack<JumpInEvent> getRedo() {
		return redo;
	}
	
}