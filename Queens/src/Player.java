import java.util.ArrayList;
public class Player {

	public String name;// we will take it from the user
	public int score;// it should start by zero 
	public ArrayList<Cards> hand;//we will choose them randomly from the cards
	public ArrayList<Cards> deck;//the cards that the player collected them in every round
	
	public Player (String name,ArrayList<Cards> hand){
		this.deck=new ArrayList<Cards>();
		this.name=name;
		this.hand=hand;
		this.score=0;
	}
	
	public int pressedIndex(int x,int y)
	{
		
		if(y>=475&&y<=571){
			for(int k=0;k<hand.size();k++){
				if(k==hand.size()-1&&x>=165+50*k&&x<165+50*(k+1)+21)
					return k;
				else if (x>=165+50*k&&x<165+50*(k+1))
					return k;
			}
		}
		
		return -1;
	}
	public String toString(){
		return this.name+":  "+this.score;
	}
	public void  calscore(){
	
		for (int i=0;i<deck.size();i++)
		   if(deck.get(i).value==12)
			  score+=25;
		
		
	}
	
	
	
	
	
	
	
	
}
