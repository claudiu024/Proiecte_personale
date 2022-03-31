   	//not working properly
package Sudoku;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Game_data implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int mistakes;
	String counter;
	public Game_data() {}
   	public Game_data(int mistakes,String counter) {
   		this.mistakes=mistakes;
   		this.counter=counter;
   		
   		}

   	public static void Save_to_xml(int mistakes,String counter) {
   		
   		try{Game_data last_game=new Game_data();
   			FileOutputStream file=new FileOutputStream(new File("last_game.xml"));
   		XMLEncoder encoder=new XMLEncoder(file);
   		encoder.writeObject(last_game);
   		encoder.flush();
   		encoder.close();
   		file.close();
   			//String xml = ToXMLStream(last_game);
   		}catch(IOException ex) {
   			ex.printStackTrace();
   		}}

	public int getMistakes() {
		return mistakes;
	}

	public void setMistakes(int mistakes) {
		this.mistakes = mistakes;
	}

	public String getCounter() {
		return counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}}