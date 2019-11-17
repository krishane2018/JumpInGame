
public class Play {
	private static JumpIn model;
	private static JumpInView view;
	private static JumpInController controller;
	public static int level;
	
	public static void play(int level) {
		Play.level = level;
		if (!(level > 0 && level <= 3)) level = 1;
		model = new JumpIn(level);
		view = new JumpInView(model);
		controller = new JumpInController(view, model);
	}
	
	public static void nextLevel() {
		Play.level = level + 1;
		view.handleDone();
		controller.removeListener();
		model = new JumpIn(level);
		view.setModel(model);
		view.createNextBoard();
		controller = new JumpInController(view, model);
		view.getMMenu().showGame();
		
	}	
}
