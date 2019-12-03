package source;

import java.awt.Point;

public interface LevelBuilderListener {
	public void handleEvent(GameObject piece, boolean removeState);
}
