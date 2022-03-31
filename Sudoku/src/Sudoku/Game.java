package Sudoku;

import static javafx.animation.Animation.INDEFINITE;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Game {
	
	static VBox Box = new VBox ();
	static Scene NewGame= new Scene(Box,1920,1080);
	static ImageView arrow = new ImageView(); 
	static ImageView back_arrow = new ImageView(); 
	static Button retry=new Button();
	static Button back=new Button();
	static RotateTransition rt = new RotateTransition();
	HBox Hbox= new HBox ();
	static GridPane grid = new GridPane();
	static Label mistakes_label=new Label("Mistakes: "+Main.mistakes);
	static String min;
	static String sec;
	static Label timerLabel=new Label();
	static int s, m, h;
	static Timeline timeline = new Timeline();
	public static void counter(int seconds,int minutes) {
	
			 s =seconds;
			 m=minutes;
//			 h=hours;
			     KeyFrame key= new KeyFrame(Duration.seconds(1),e->{
			    	if (Main.retry_pressed==true) { m=0;s=0;}	
			    	if (Main.back_pressed==true || game_is_over()) {timeline.stop(); }	
			 min=Integer.toString(m);
           	 sec=Integer.toString(s);
                	
              	System.out.print(s);
                	
                		if (s==60){
                		m++;
                		s=0;
                		
//                		if (m==60)
//                		{h++;
//                		m=0;
//                		}
                	}	
                		if (s<10)
                		{
                			sec="0"+s;
                			
                		}
                		if (m<10)
                		{
                			min="0"+m;
                		}
                		s++;
                		
                	timerLabel.setText(new TIMER(min,sec).toString());
                	Main.retry_pressed=false;
                });
			     timeline.getKeyFrames().add(key);
		
	}
public static void reset_counter() {
	min="0";
	sec="0";
	
}
public static boolean game_is_over() {
	 if(Main.mistakes>=3 ||Main.same_matrix==true)
		 return true;
	 else return false;
	
}


       
public Game(){
	NewGame.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	NewGame.setCursor(Menu.cursor);
	arrow.setId("arrow");
	back_arrow.setId("back-arrow");
	timeline.setCycleCount(INDEFINITE);
	  
	//back.setPrefHeight(50);
	//back.setPrefWidth(50);
	back.setId("retry-button");
	back.setGraphic(back_arrow);
	back.setCursor(Menu.cursor2);
	
	retry.setPrefHeight(50);
	retry.setPrefWidth(50);
	retry.setId("retry-button");
	retry.setGraphic(arrow);
	retry.setCursor(Menu.cursor2);

	rt.setNode(arrow);
	rt.setAxis(Rotate.Z_AXIS);  
	rt.setDuration(Duration.millis(800));
	rt.setByAngle(-360);
	rt.setCycleCount(1);
	rt.setAutoReverse(true);
  

	Hbox.getStyleClass().add("hbox");
	grid.setAlignment(Pos.CENTER);
	//grid.setCursor(Menu.cursor2);

	Hbox.getChildren().add(back);
	Hbox.getChildren().add(retry);
	
	Hbox.getChildren().add(timerLabel);
	Hbox.getChildren().add(mistakes_label);

	

	Box.getChildren().add(Hbox);
	Box.getChildren().add(grid);
}

}