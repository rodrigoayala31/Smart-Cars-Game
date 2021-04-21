
import javafx.scene.*;

public interface BackgroundTo {
	
	static void moveBgTo(double x, double y, Node back, double cond, double yCoor) {
		final double cy = back.getBoundsInLocal().getHeight() / 2;
		if (y -cy >= cond) {
        	back.relocate(0, yCoor);
        } else {
        	back.relocate(0 , y - cy);
        } 	
	}
}
