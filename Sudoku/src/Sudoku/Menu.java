package Sudoku;
import java.awt.AWTException;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Menu {
	static VBox Menu_Box= new VBox ();
	static Scene Menu_Scene = new Scene(Menu_Box,1920,1080);
	static boolean played=false;
	Button Continue_button =new Button("Continue Game");
	Button NewGame_button=new Button("New Game");
	Button Settings_button =new Button("Settings");	
	Button Exit_button =new Button("Exit");
	String h;
	String m;
   	String s;
   	static Cursor cursor, cursor2;
   
	public Menu() {
	
	
	Continue_button.getStyleClass().add("menu-button");
	NewGame_button.getStyleClass().add("menu-button");
	Settings_button.getStyleClass().add("menu-button");
	Exit_button.getStyleClass().add("menu-button");
	cursor=(new ImageCursor(new Image("MetroAlt.png")));
	//	Image cursor=new Image("MetroAlt.png");
//		Image cursor2=new Image("tap.png");
		Menu_Scene.setCursor(cursor);
		
		
		cursor2 =(new ImageCursor(new Image("tap.png")));
		Continue_button.setCursor(cursor2);
		NewGame_button.setCursor(cursor2);
		Settings_button.setCursor(cursor2);
		Exit_button.setCursor(cursor2);
		

		Menu_Box.getChildren().addAll(NewGame_button,Settings_button,Exit_button);	
		String line;
   			
   	        BufferedReader br = null;
   	        try {
   	            File file = new File("src\\Sudoku\\data\\last_game.csv"); // java.io.File
   	            FileReader fr = new FileReader(file); // java.io.FileReader
   	            br = new BufferedReader(fr); // java.io.BufferedReader
   	           int i=0;
   	            while ((line = br.readLine()) != null) {
   	              // process the line
   	     		if(i==0 && !line.equals(""))
 	 			{
//   	     		Menu_Box.getChildren().add(0, Continue_button);
   	            	if(i==1)
   	            	{
   	            	char ch= line.charAt(10);
   	     		Main.mistakes=Character.getNumericValue(ch);
				Game.mistakes_label.setText("Mistakes: "+Main.mistakes);}
   	            	}

   	            	i++;
   	            }
   	          }
   	          catch(IOException e) { e.printStackTrace();}
   	          finally
   	          {
   	              try { if (br != null) br.close(); }
   	              catch(IOException e) { e.printStackTrace(); }
   	          }
   		
		new Game();
		new Settings();
		Menu_Scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
	//not working properly
//		Continue_button.setOnAction(game->{
//			Main.back_pressed=false;
//			played=true;
//			Game.timeline.play();
//			Main.draw_sudoku();
//			Main.read_sudoku(Main.last_game);
//			Main.save_sudoku();
//			//Main.window.setFullScreen(true);
//			Main.window.setScene(Game.NewGame);
//			Main.window.setMaximized(true);
//			Main.window.show();
//			Game.counter(Integer.valueOf(s),Integer.valueOf(m));
//			Game.timeline.play();
//			Game.Box.setBackground(Menu_Box.getBackground());
//		});
		NewGame_button.setOnAction(newgame->{
			Main.back_pressed=false;
			System.out.print("M:"+Main.mistakes);
			Main.draw_sudoku();
			Main.read_sudoku(Main.sudoku_collection);
		
			Main.save_sudoku();
			
			Main.window.setScene(Game.NewGame);
			Main.window.setMaximized(true);
			
			Main.window.show();
//	not working properly
	//		Game.counter(0,0);
			Main.mistakes=0;
			System.out.print(Game.timeline.getStatus());

			Game.timeline.play();
			Game.Box.setBackground(Menu_Box.getBackground());
			
			System.out.print("\nM:"+Main.mistakes);
		});
		Settings_button.setOnAction(e->{
			Main.window.setScene(Settings.scene);
			//
			Main.window.setMaximized(true);
			Main.window.show();	
			Settings.Box.setBackground(Menu_Box.getBackground());
			
		
		});

	  
	Exit_button.setOnAction(e->{
		Platform.exit();
	});
}
	}
