   	//not working properly
package Sudoku;

public class TIMER {
private String minutes;
private String seconds;
public TIMER(String minutes,String seconds)
{
	this.minutes=minutes;
	this.seconds=seconds;
//	minutes=Integer.toString(Game.minutes);
//	seconds=Integer.toString(Game.seconds);
//	if (Game.seconds<10)
//	{
//		seconds=0+seconds;
//	}
//	if (Game.minutes<10)
//	{
//		minutes=0+minutes;
//	}
	
	}
@Override
public String toString() {
	String out=minutes + ":" +seconds ;
	return  out;
}
	
}

