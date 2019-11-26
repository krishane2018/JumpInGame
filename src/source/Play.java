package source;

import gui.JumpInController;
import gui.JumpInView;

/**
 * 
 * @author Aashna Narang
 *
 */

public class Play {
	
	private static JumpIn model;
	private static JumpInView view;
	private static JumpInController controller;
	public static int level;
	
	/**
	 * Create a model, view, controller for the level
	 * @param level - Level of the game 
	 */
	public static void play(int level) {
		Play.level = level;
		if (!(level > 0 && level <= 3)) level = 1;
		model = new JumpIn(level);
		view = new JumpInView(model);
		controller = new JumpInController(view, model);
	}
	
	/**
	 * Move onto the next level by updating the view with the new model, remove listeners from the old 
	 * controller and create a new model + controller.
	 */
	public static void nextLevel() {
		Play.level = level + 1;
		if (Play.level > 3) {
			view.handleDone();
		} else {
			controller.removeListener();
			model = new JumpIn(level);
			view.setModel(model);
			view.createNextBoard();
			controller = new JumpInController(view, model);
			view.getMMenu().showGame();
		}
	}	
	
	public static void loadGame() {
		
	}
}
