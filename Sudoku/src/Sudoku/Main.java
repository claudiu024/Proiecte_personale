package Sudoku;

import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Random;
import java.util.stream.Stream;

//import Sudoku.Menu.Game_data;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;


public class Main extends Application {
	static int mistakes=0;
	static boolean same_matrix;
	static String sudoku_collection="src\\Sudoku\\data\\sudoku.csv";
	static boolean retry_pressed =false;
	static String last_game="src\\Sudoku\\data\\last_game.csv";
	static String solved="";
	static char x[]= {};
	static Button number[]=new Button[9];	
	static ToggleButton square[][]=new ToggleButton[9][9];	
	static ToggleButton aux_button=new ToggleButton();

	Button retry2 =new Button();
	VBox Exit_Box=new VBox();
	Scene Exit=new Scene (Exit_Box,300,300);

	static Stage gameover;//=new Stage();

	static Label exit_label=new Label("Game Over");
	static String solved_sudoku[][]=new String[10][10];
	static String given_sudoku[][]=new String[10][10];
	static Stage window;

	public static boolean back_pressed;
	@Override
	public void start(Stage primaryStage) {	
		try {new Menu();
			 Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			        public void run() {
			            // Do what you want when the application is stopping
			        	if(Menu.played)
			        	save_sudoku();
			        }
			    }));
			//Menu Window

			gameover=new Stage();
			primaryStage.setScene(Menu.Menu_Scene);
			primaryStage.setMaximized(true);
			//primaryStage.setFullScreen(true);
			
			primaryStage.show();
			primaryStage.setTitle("Sudoku");
			primaryStage.centerOnScreen();
			window=primaryStage;
		
			Game.back.setOnAction(e->{
				back_pressed=true;
				save_sudoku();
				window.setScene(Menu.Menu_Scene);
				//Main.window.setFullScreen(true);
				Main.window.show();
				Main.window.setMaximized(true);
			//	Main.window.setFullScreen(true);
				Menu.Menu_Box.setBackground(Game.Box.getBackground());
				
			});
			//Game Window	
			Game.retry.setOnAction(e->{
				Game.rt.play();
				 retry_pressed = true;
				gameover.close();
				 disable_Buttons(true);
				 disable_ToggleButtons(false);
				 Main.mistakes=0;
				 Game.mistakes_label.setText("Mistakes: "+Main.mistakes);
				for(int i=0;i<9;i++)
				for(int j=0;j<9;j++)
				{
				square[j][i].getStyleClass().remove("wrong_number");
				square[j][i].setText(given_sudoku[i][j]);
				square[j][i].setSelected(false);
				square[j][i].setDisable(ifNotEmpty(i,j));
					}
			
				Game.Box.setOpacity(1);
			});
			
	retry2.setOnAction(Game.retry.getOnAction());
	retry2.setText("Retry");
		
			//Exit Window	
		
			Exit.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		
			Exit_Box.getChildren().add(exit_label);
	
			Exit_Box.setAlignment(Pos.CENTER);
			exit_label.setAlignment(Pos.CENTER);
			gameover.setScene(Exit);	
		 	gameover.setResizable(false);
		

	Exit_Box.getChildren().add(retry2);
	//Settings 
	
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
			
	}

	//Functions
	public static void read_sudoku(String filepath){
		int a;
		 String line;
		int k=0;
		
	   		try (Stream<String> lines = Files.lines(Paths.get(filepath))) {
	   			
	   			if(filepath.equals(last_game))
	   			{try (Stream<String> liness = Files.lines(Paths.get(filepath))) {
	   				line = liness.skip(0).findFirst().get();
//	   				String line2 = liness.skip(1).findFirst().get();
		   		 x=line.toCharArray();
//		   		 char[] mistakes_csv = line2.toCharArray();
//		   		 System.out.print(mistakes_csv);
	   			}
	   			}
	   			else {Random nr=new Random();
	   			 a =1+nr.nextInt(98);
	   			 
	   			line = lines.skip(a).findFirst().get();
	   			 x=line.toCharArray(); }
	   		
	   			for(int i=0;i<9;i++)	 
	   					for(int j=0;j<9;j++)
	   					{
	   					solved_sudoku[i][j]=""+x[k+82];
	   					 solved=solved+x[k+82];
	   					given_sudoku[i][j]="";
	   					if(x[k]!='0')
	   					{	square[j][i].setText(""+x[k]);
	   						given_sudoku[i][j]=""+x[k];
	   					}
	   					k++;
	   					}
	   			for(int i=0;i<9;i++)
					for(int j=0;j<9;j++)
					{	
						square[j][i].setDisable(ifNotEmpty(i,j));
					int b=i,c=j;
					
					
						square[j][i].setOnAction(e1->{
//						square[c][b].setText("d");
						disable_Buttons(false);
						aux_button.setSelected(false);
						square[c][b].setSelected(true);
						aux_button = square[c][b];
						
						for(int l=0;l<9;l++)
						{		
							String nr=number[l].getText();
					
						number[l].setOnAction(e->{
						
												square[c][b].setText(nr);
					
												compare_square(b,c);
					
												compare_matrix();
					
												 save_sudoku();							
												 });
						
						}		
						
													});}
	   		} catch (IOException e) {
				
				e.printStackTrace();
			}
	}
	
	public static void draw_sudoku()
	{
	for(int i=0;i<9;i++)
	{	 
		for(int j=0;j<9;j++)	
		{
			
			square[i][j] = new ToggleButton();
			
			if(i==2||i==5||i==8)
			{
				if(j==2||j==5||j==8) 
				square[i][j].setId("colt");
				else 
					square[i][j].setId("vertical");
			}
			else if(j==2||j==5||j==8)
			{
				if(i==2||i==5||i==8) 
				square[i][j].setId("colt");
				else square[i][j].setId("orizontal");
			}
	
		
			square[i][j].setCursor(Menu.cursor2);;
			square[i][j].setPrefHeight(60);
			square[i][j].setPrefWidth(60);
			 Game.grid.add(square[i][j],i,j);
			
			
			int n=i+1;
		
			 number[i] =new Button(""+n);
			 number[i].setId("fancy-button");	
			number[i].setCursor(Menu.cursor2);
			 Game.grid.add(number[i], i,10);
			 
		
	}}}
	
	public static void  save_sudoku() {
		try {String counter=Game.timerLabel.getText();
			Game_data.Save_to_xml(mistakes,counter);
			FileWriter writer=new FileWriter(last_game);
			BufferedWriter bw=new BufferedWriter(writer);
			PrintWriter out=new PrintWriter(bw);
			String  y="";
			for(int i=0;i<9;i++)	 
				for(int j=0;j<9;j++)
				{if (square[j][i].getText().equals("")) 
				{y=y+"0";}else
						//if(square[j][i].getID()=
					y=y+square[j][i].getText();
					
				}
			y=y+","+solved;
				x=y.toCharArray();
//				System.out.print(x);
				out.print(x);
				out.print("\nMistakes: "+Main.mistakes);
				out.print("\n"+Game.timerLabel.getText());
			out.flush();
			out.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	private static boolean ifNotEmpty(int i,int j)
	{
		if(!square[j][i].getText().isEmpty())
		return true;
		else return false;
	}
	private static void compare_square(int i,int j) {
	
		if(!square[j][i].getText().equals(String.valueOf(solved_sudoku[i][j])))
		{
			System.out.println(square[j][i].getText()+"="+solved_sudoku[i][j]);
			 mistakes++;
			 Game.mistakes_label.setText("Mistakes: "+Main.mistakes);
			 square[j][i].getStyleClass().add("wrong_number");
			 
			 if(mistakes>=3)
			 {
				Game.Box.setOpacity(0.75);
			gameover.show();
				disable_Buttons(true);
				 disable_ToggleButtons(true);
				
				}
		}else
		{
			 square[j][i].getStyleClass().add("correct_number");
			// square[j][i].getStyleClass().add("correct_number");
			 System.out.print(square[j][i].getId());
			 square[j][i].setDisable(true);
			 square[j][i].setSelected(false);
			 disable_Buttons(true);
		}
		
	}

	private static void compare_matrix() {
		
		same_matrix=true;
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
			{
				  if(!square[j][i].getText().equals(solved_sudoku[i][j]))
						  {
					  same_matrix=false;
					  break;
					  
						  }
			}
		if(same_matrix)
		{	exit_label.setText("Congratulations!");
		gameover.show();
			}
	}
	
	private static void disable_Buttons(boolean x) {
		for(int i=0;i<9;i++)
			number[i].setDisable(x);}
		
	private static void disable_ToggleButtons(boolean x) {
		for(int i=0;i<9;i++)
		{	
		for(int j=0;j<9;j++)
		{
			square[i][j].setDisable(x);
		
		}
	 }	
	}
	
	public static void main(String[] args) {
	
		launch(args);
		
	}
	
}
