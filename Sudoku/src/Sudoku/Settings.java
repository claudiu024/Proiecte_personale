package Sudoku;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Settings {
	 static VBox Box = new VBox ();
	 static Scene scene= new Scene(Box,1920,1080);
	 
	 ToggleButton[][] square=new ToggleButton[5][5];
	 Settings()
	 {
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

	Box.getStyleClass().add("Vbox-settings");
	GridPane color_grid =new GridPane();
	Label l1 =new Label("Choose background color");
	Button back_button=new Button();
//	Game.arrow.setId("arrow");
//	Game.back_arrow.setId("back-arrow");
//	back_button.getStyleClass().add("menu-button");
	//back_button.setPrefHeight(30);
	//back_button.setPrefWidth(30);
	back_button.setId("retry-button");
	back_button.setGraphic(Game.back_arrow);
	back_button.setCursor(Menu.cursor2);
	back_button.setOnAction(e->{
		
		Main.window.setScene(Menu.Menu_Scene);
		//Main.window.setFullScreen(true);
		Main.window.show();
		Main.window.setMaximized(true);
	//	Main.window.setFullScreen(true);
		Menu.Menu_Box.setBackground(Box.getBackground());
	
	});
	Box.getChildren().addAll(l1,color_grid,back_button);
	
	for(int i=0;i<5;i++)
		for(int j=0;j<5;j++)
	{
			square[i][j] = new ToggleButton();
	
			int a=i,b=j;
	square[i][j].setCursor(Menu.cursor2);
	square[i][j].setPrefHeight(80);
	square[i][j].setPrefWidth(80);
	square[i][j].setOnAction(e->{
		

		Box.setBackground(square[a][b].getBackground());
	
		
	});
	
	color_grid.add(square[i][j], j, i);
	}
	square[0][0].setId("purple-gradient-box");
	square[0][1].setId("green-gradient-box");
	square[0][2].setStyle("-fx-background-color: #FFFFFF; ");
	square[0][3].setStyle("-fx-background-color: #2F2F2F; ");
	square[0][4].setStyle("-fx-background-color: #EB8E8E; ");
	square[1][0].setStyle("-fx-background-color: #B78FDF; ");
	square[1][1].setStyle("-fx-background-color: #888CC0; ");
	
	
color_grid.setAlignment(Pos.CENTER);
	 }}
