import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public interface MessageInGame {
	static boolean crashed(boolean crashed, int count, int score) throws InterruptedException{
    	//display an alert if the car is crashed
    	
    	crashed = true;
    	
    	ButtonType answer = new ButtonType("EXIT");
    	Alert alert = new Alert(AlertType.NONE, "GAME OVER!", answer);
    	alert.setTitle("GAME OVER!");
    	alert.setHeaderText("You lost the game! Try again.");
    	alert.setContentText("You just crashed into the hacker truck! Your score was " + score + "!");
    	alert.show();
    	
    	count += 1;
    	
    	return crashed;
//    	if (count==2) {
//    		System.exit(0);
//    	}
    	
    }
    
    static boolean offTheRoad(boolean crashed, int count, int score) throws InterruptedException{
    	//display an alert if the car is off the road
    	
    	crashed = true;
    	
    	ButtonType answer = new ButtonType("EXIT!");
    	Alert alert = new Alert(AlertType.NONE, "GAME OVER!", answer);
    	alert.setTitle("GAME OVER!");
    	alert.setHeaderText("You lost the game! Try again.");
    	alert.setContentText("You just went off the road! Your score was " + score + "!");
    	
    	alert.show();
    	

    	count += 1;
    	
    	return crashed;
//    	if (count==2) {
//    		System.exit(0);
//    	}
    	
    }
  
    static void instructions() {
    	//display instructions at the start of the game
    	ButtonType answer = new ButtonType("Start the Game!");
    	Alert alert = new Alert(AlertType.NONE, "Instructions", answer);
    	alert.setTitle("INSTRUCTIONS");
    	alert.setHeaderText("Avoid going off the road or hitting the on coming car!");
    	alert.setContentText("Beware: Hitting the Yellow object will get your car hacked!");
    	
    	alert.showAndWait();
    }
  
}
