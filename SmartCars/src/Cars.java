//import java.io.FileInputStream;
import java.util.Random;

//import javax.imageio.ImageIO;
//import javax.swing.JOptionPane;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL; 
//import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;

import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Hold down an arrow key to have your car move around the screen.
 * Hold down the shift key to have the car to move faster.
 */
public class Cars extends Application implements BackgroundTo, LosingConditions, MessageInGame{

    private static final double W = 512, H = 1024;
    
    private static final String CAR_IMAGE = "https://img.icons8.com/color/2x/f1-race-car-top-veiw.png";
   // URL urlCar = new URL(CAR_IMAGE);
    
    private static final String BACK_IMAGE = "https://linkpicture.com/q/output-onlinepngtools-1_10.png"; 
  //  URL urlBack = new URL(BACK_IMAGE);
    
    private static final String BOLT = "https://img.icons8.com/color/2x/break.png";
    
    private static final String BAD_CAR = "https://images-ext-1.discordapp.net/external/8m41qiXkILIWLUg495Ysx9OnVkKWDHdMcsnGnm1Urmc/https/img.icons8.com/officel/2x/truck-top-view.png";
   // URL urlBad = new URL(BACK_IMAGE);
    
    private static final String HACK = "https://icons.iconarchive.com/icons/iron-devil/ids-game-world/32/Danger-zone-icon.png";
   // URL urlHack = new URL(BACK_IMAGE);
    
    private static final String SCORE = "https://img.icons8.com/cotton/2x/rating.png";
    

    
    private Image scoreImage;
    
    private Image hackImage;
    private Node hacker;   
    
    private Image carImage;
    private Node  gCar;
    
    private Image badCar;
    private Node bCar;
    
    private Image backImage;
    private Node back;
    private Node back2;
    private Node back3;
    private Node back4;
    
    private Image boltImg;
    private Node bolts;
    
    private int count = 0;
    private int count2 = 0;
    
    private int score = 0;
    
    private boolean hacked = false;
    private int hackCount = 0;
    
    private double gCarX;
    private double gCarY;
    private double bCarX;
    private double bCarY;
    private double hackerX;
    private double hackerY;
    
    private boolean crashed = false;
    
    boolean running, goNorth, goSouth, goEast, goWest;
    

    @Override
    public void start(Stage stage) throws Exception {
    	
    	MessageInGame.instructions();
    	
    	carImage = new Image(CAR_IMAGE);
        gCar = new ImageView(carImage);
        gCar.setRotate(270);
        
        boltImg = new Image(BOLT);
        bolts = new ImageView(boltImg);
        
        
        backImage = new Image(BACK_IMAGE);
        back = new ImageView(backImage);
        back2 = new ImageView(backImage);
        back3 = new ImageView(backImage);
        back4 = new ImageView(backImage);

        
        
        badCar = new Image(BAD_CAR);
        bCar = new ImageView(badCar);
        bCar.setRotate(90);
        
       
        
        hackImage = new Image(HACK);
        hacker = new ImageView(hackImage);
      
        
        scoreImage = new Image(SCORE);
        ImageView scores = new ImageView(scoreImage);
        scores.setFitHeight(30);
        scores.setFitWidth(30);
        
        
        Text text = new Text();
        text.setFont(new Font(20));
        text.setFill(Color.WHITE);
        text.setText("Score: " + Integer.toString(score));
        
        
        
        
        
        Group root = new Group(back, back2, back3, hacker, gCar, bCar, scores, text, bolts);

        //moving all the objects to its default positions
        
        moveGCarTo(W/2, 3 * H / 4);
        BackgroundTo.moveBgTo(0, -H / 4, back, 0, -H/2);
        BackgroundTo.moveBgTo(0, H / 4, back2, H/2, 0);
        BackgroundTo.moveBgTo(0, 3* H / 4, back3, H, H/2);
        BackgroundTo.moveBgTo(0, 5 * H/ 4, back4, 3*H/2, H);
        
        //the bad car and its hacker component moved to random starting position
        Random rand = new Random();
        moveBCarTo(rand.nextInt(245) + 120,-100);
        moveHackerTo(rand.nextInt(245)+120, 0);
        scores.relocate(40, 10);
        text.relocate(70, 13);
        bolts.relocate(-100, -100);
        
        Scene scene = new Scene(root, W, H, Color.FORESTGREEN);
        ImagePattern pattern = new ImagePattern(backImage);
        scene.setFill(pattern);
        
        

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goNorth = true; break;
                    case DOWN:  goSouth = true; break;
                    case LEFT:  goWest  = true; break;
                    case RIGHT: goEast  = true; break;
                    case SHIFT: running = true; break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goNorth = false; break;
                    case DOWN:  goSouth = false; break;
                    case LEFT:  goWest  = false; break;
                    case RIGHT: goEast  = false; break;
                    case SHIFT: running = false; break;
                }
            }
        });
        
        stage.setScene(scene);
        stage.setTitle("BOLT");
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
           

			

			@Override
            public void handle(long now) {
                
            	int dx = 0, dy = 0;

                if (goNorth) dy -= 5;
                if (goSouth) dy += 5;
                if (goEast)  dx += 5;
                if (goWest)  dx -= 5;
                if (running) { dx *= 3; dy *= 3; }
                
                if (hacked) {
                		dx *= 2; dy *= 2;}   
             
                moveGCarBy(dx, dy); 
                
               //Background back = new Background();
                
                moveBgBy(dx,-dy, back); 
                moveBgBy(dx, -dy, back2);      
                moveBgBy(dx, -dy, back3);   
                moveBgBy(dx, -dy, back4);  
                
                moveBCarBy(0, -dy * 2);               
                moveHackerBy(0, -dy);
                changeScore(text);
                             
                if (crashed == true) {
                	stop();
                }
                
            }
        };
        
        		timer.start();
  
    }
    

    private void moveGCarBy(int dx, int dy) {
    	//calls the function which changes the location of the driving car
    	
        if (dx == 0 && dy == 0) return;

        final double cx = gCar.getBoundsInLocal().getWidth()  / 2;
        final double cy = gCar.getBoundsInLocal().getHeight() / 2;

        double x = cx + gCar.getLayoutX() + dx;
        double y = cy + gCar.getLayoutY() + dy;
        
        this.gCarX = x;
        this.gCarY = y;
        

        moveGCarTo(x, 3 * H/4);
    }
    
    private void moveBCarBy(int dx, int dy) {
    	//calls the function which moves the bad car in the opposite direction
    	
        if (dx == 0 && dy == 0) return;
        
        if (dy < 0) return;
        
        final double cy = bCar.getBoundsInLocal().getHeight() / 2;

        double x = bCar.getLayoutX() + dx;
        double y = cy + bCar.getLayoutY() + dy;
        
        this.bCarX = x + 40;
        this.bCarY = y;

        moveBCarTo(x, y);
    }
    
    
    private void moveBgBy (int dx, int dy, Node back) {
    		if (dx == 0 && dy == 0) return;
           
            if (dy < 0) return;
           
            final double cy = back.getBoundsInLocal().getHeight() / 2;

            double x = back.getLayoutX() + dx;
            double y = cy + back.getLayoutY() + dy;
            
            if (back == this.back) {
            	BackgroundTo.moveBgTo(0, y, back, 0, -H/2);
            } else if (back == this.back2) {
            	BackgroundTo.moveBgTo(0, y, back2, H/2, 0);
            } else if (back == this.back3) {
            	BackgroundTo.moveBgTo(0, y, back3, H, H/2);
            } else {
            	BackgroundTo.moveBgTo(0, y, back4, 3*H/2, H);
            }
    	}
    
   
    private void moveHackerBy(int dx, int dy) {
    	//calls the function which moves the hacker component of the bad car
    	if (dx == 0 && dy == 0) return;
        
        if (dy < 0) return;
        
        final double cy = hacker.getBoundsInLocal().getHeight() / 2;

        double x = hacker.getLayoutX() + dx;
        double y = cy + hacker.getLayoutY() + dy;
       
        this.hackerX = x;
        this.hackerY = y;

        moveHackerTo(x, y);
    }
    
    private void moveHackerTo(double x, double y) {
    	//the hacker component of the bad car is being moved here to its next position
    	//also checking if the car has been hacked or not
    	
    	
    	final double cy = hacker.getBoundsInLocal().getHeight() / 2;
    	
    	if(score != 0) {
    		if (this.hacked) {
    			this.hackCount = this.hackCount + 1;
    			bolts.relocate(W/2 - 50 , H/4);
    		} else {
    			boolean hack = LosingConditions.isHacked(gCarX, gCarY, hackerX, hackerY,this.hacked);
    			this.hacked = hack;
    		}
    	
    		if (this.hackCount > 100 ) {
    			this.hackCount = 0;
    			this.hacked = false;
    			bolts.relocate(-100, -100);
    		}
    	}
    	
    	
    	
    	if (y -cy >= H) {
    		Random rand = new Random();
        	hacker.relocate(gCarX, -100);
        } else {
        	hacker.relocate(x , y - cy);  
        }
    }
    
    
    private void moveGCarTo(double x, double y) {
    	//moves the good car to the next location
    	//the out of bounds accident event is checked here
    	
    	
        final double cx = gCar.getBoundsInLocal().getWidth()  / 2;
        final double cy = gCar.getBoundsInLocal().getHeight() / 2;
        
        	boolean accident = LosingConditions.isOutOfBounds(x, this.crashed);
        	if (accident == true) {
        		
        		try {
        			this.crashed = MessageInGame.offTheRoad(this.crashed, count, score);
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
        	}
        
        
        if (x - cx >= 0 &&
            x + cx <= W &&
            y - cy >= 0 &&
            y + cy <= H) {
        	gCar.relocate(x - cx, y - cy);
        } 
    }
    

    private void moveBCarTo(double x, double y)  {        
    	//moves the bad car to its next position
    	//the crashed event is checked here to see if the cars have collided
    	final double cy = bCar.getBoundsInLocal().getHeight() / 2;
    	count2 += 1;
        
        if (count2 > 1) {
        	boolean accident = LosingConditions.isCrashed(this.gCarX, this.gCarY, this.bCarX, this.bCarY);
    		if (accident == true) {
    			try {
    				this.crashed = MessageInGame.crashed(this.crashed, count, score);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
        }
    	
    	
    	if (y -cy >= H) {
    		Random rand = new Random();
        	bCar.relocate(rand.nextInt(300) + 100, -100);
        	score += 1;
        	
        } else {
        	bCar.relocate(x , y - cy);  
        }
     }
    
    
    private void changeScore(Text text) {
    	text.setText("Score: " + Integer.toString(score));
    	text.relocate(70, 13);
    }
    
    
    public static void main(String[] args) {
    	
    	 launch(args); 
    }
} 
