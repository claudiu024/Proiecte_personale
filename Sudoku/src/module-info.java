module Sudoku {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.base;
	
	opens Sudoku to javafx.graphics, javafx.fxml;
}
