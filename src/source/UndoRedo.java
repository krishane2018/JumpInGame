package source;
import java.util.Stack;

/**
 * 
 * @author Aashna Narang and Kush Gopeechund
 *
 */
public class UndoRedo {

	private Stack<JumpInEvent> undo;
	private Stack<JumpInEvent> redo;
	private boolean state;
	
	public UndoRedo() {
		undo = new Stack<JumpInEvent>();
		redo = new Stack<JumpInEvent>();
		state = false;
	}
	
	/**
	 * Get the stack used for undoing moves
	 * @return the undo stack
	 */
	public Stack<JumpInEvent> getUndo() {
		return undo;
	}

	/**
	 * Get the stack for redoing moves
	 * @return the redo stack
	 */
	public Stack<JumpInEvent> getRedo() {
		return redo;
	}

	/**
	 * Undo an event by getting the last event that was added to the stack. Add that event to the
	 * redo stack.
	 * @return JumpInEvent Object of the last move that occured
	 */
	public JumpInEvent undoMove() {
		if(!undo.isEmpty()) {
			JumpInEvent e = undo.pop();
			redo.add(e);
			state = true;
			return e;
		}
		return new JumpInEvent();
		
	}
	
	/**
	 * Adds an event to the stack of moves the user can undo
	 * @param e - the JumpInEvent object
	 */
	public void addMove(JumpInEvent e) {
		if(!state) {
			redo.clear();
			undo.add(e);
		}
	}
	
	/**
	 * @return the state, whether the user in undoing moves or not
	 */
	public boolean isState() {
		return state;
	}

	/**
	 * @param state - the state to set the object in. Either in undoing moves state 
	 * or not
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * Redo a move by removing the last event added to the redo stack. The event 
	 * is added back to the undo stack.
	 * @return the JumpInEvent object with all the information about the move the user would
	 * like to redo
	 */
	public JumpInEvent redoMove() {
		if(!redo.isEmpty()) {
			JumpInEvent e = redo.pop();
			undo.add(e);
			return e;
		}
		return new JumpInEvent();
	}
	
	public void clearRedo() {
		redo.clear();
	}
	
}