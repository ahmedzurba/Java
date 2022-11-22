import java.util.ArrayList;


public class ComputerPlayer extends Player{

	public ComputerPlayer(String name,ArrayList<Cards> hand){
		super(name,hand);
	}
	public int Throw(ArrayList<field> playground,ArrayList<Cards> hand1,ArrayList<Cards> hand2,ArrayList<Cards> hand3){

		int a=0;
		if (!playground.isEmpty()&&min(playground.get(0).type)>-1){
			if(playground.size()==3&&!findqueen(playground)&&hand.get(max(playground.get(0).type)).value!=12)
				a=max(playground.get(0).type);
			else if(findkingOrAs(playground,playground.get(0).type)){
				if(findqueen(playground.get(0).type)!=-1)
				a=findqueen(playground.get(0).type);}
			else a=min(playground.get(0).type);}
		else if(!playground.isEmpty()&&min(playground.get(0).type)==-1){
			if(findqueen()!=-1)
				a=findqueen();
		else a=max();
		}
		else if (playground.isEmpty())
		   a=min();
		return a;
		
	}
	public int Throw(){
		
		return 0;
	}
	public int min(String type){
		int min=-1;
		Cards a=new Cards(type+15);
		for(int i=0;i<hand.size();i++)
			if(a.type.equals(hand.get(i).type)&&a.value>hand.get(i).value){
				a=hand.get(i);
				min=i;}
			return min;
		}
	public int min(){
		int min=-1;
		Cards a=new Cards("c"+15);
		for(int i=0;i<hand.size();i++)
			if(a.value>hand.get(i).value){
				a=hand.get(i);
				min=i;}
			return min;
		}
	public int findqueen(){
		int index=-1;
		for(int i=0;i<hand.size();i++)
			if(hand.get(i).value==12)
				index=i;
		return index;
	}
	public int max(){
		int max=-1;
		Cards a=new Cards("c"+0);
		for(int i=0;i<hand.size();i++)
			if(a.value<hand.get(i).value){
				a=hand.get(i);
				max=i;}
			return max;
	}
	public int max(String type){
		int max=-1;
		Cards a=new Cards(type+0);
		for(int i=0;i<hand.size();i++)
			if(a.type.equals(hand.get(i).type)&&a.value<hand.get(i).value){
				a=hand.get(i);
				max=i;}
			return max;
		}
	
	public boolean findqueen(ArrayList<field> playground){///////////for playground
		int index=-1;
		for(int i=0;i<playground.size();i++)
			if(playground.get(i).value==12)
				index=i;
		return index!=-1;
	}
	public boolean findkingOrAs(ArrayList<field> playground,String type){
	int index=-1;
	for(int i=0;i<playground.size();i++)
		if((playground.get(i).value==13&&playground.get(i).type.equals(type))||(playground.get(i).value==14&&playground.get(i).type.equals(type)))
			index=i;
	return index!=-1;}
	
	
	
public int findqueen(String type){///////////for playground
	int index=-1;
	for(int i=0;i<hand.size();i++)
		if(hand.get(i).value==12&&hand.get(i).type.equals(type))
			index=i;
	return index;
}
}

