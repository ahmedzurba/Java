import java.util.ArrayList;


public class field extends Cards {
	public int cardplayer;
	
	public field(String str,int player){
		super(str);
		this.cardplayer=player;
	}
	public static int PlayerIndex(ArrayList<field> pg){
		field card=pg.get(0);
		for(int i=1;i<4;i++){
			if(card.type.equals(pg.get(i).type))
				if(card.value<pg.get(i).value)
					card=pg.get(i);}
		return card.cardplayer;
		
	}
	

}
