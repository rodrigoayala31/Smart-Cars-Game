
public interface LosingConditions {
	static boolean isOutOfBounds(double x, boolean crashed) {
    	//checks conditions if the car is off the road
    	
    	if (x > 410 || x < 100) {
    		crashed = true;
    		return true;
    	}else {
    		return false;
    	}
    }

    
    static boolean isHacked(double gCarX, double gCarY, double hackerX, double hackerY, boolean hacked) {
    	//checks conditions if the car is hacked
    	
    	double xDiff = gCarX - hackerX;
    	double yDiff = gCarY - hackerY;
    	if (xDiff > -20 && xDiff < 50) {
    		if (yDiff > -60 && yDiff < 60) {
    			hacked =  true;
    			return true;
    		} else {
    			hacked = false;
    			return false;
    		}
    	}else {
    		hacked = false;
    		return false;
    	}
	
    }
    
    
    static boolean isCrashed(double gCarX, double gCarY, double bCarX, double bCarY) {
    	//checks conditions if the cars have collided
    	
    	double xDiff = gCarX - bCarX;
    	double yDiff = gCarY - bCarY;
    	
    	if (xDiff > -36 && xDiff < 110) {
    		if (yDiff > -120 && yDiff < 112) {
    			return true;
    		}
    	}
    	return false;
    }    
}
