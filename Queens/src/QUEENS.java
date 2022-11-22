import java.util.ArrayList;
import processing.core.*; 
import ddf.minim.*;
public class QUEENS extends PApplet {

	String name;///the name of player
	String help="What is the game\nIt is a cards game composed of 52 cards (13 spades, 13 diamonds, 13 hearts, and 13 clubs)\n "
			+ "and four players, each of them will have 13 cards randomly.\n***********\n\nHow to play the game"
			+ "\nThe player who has the �two club card� will start first by throwing it."
			+ "\nEvery round the players will throw one card from his hand\n(All the  cards that the player hold them in his"
			+ " hand) \nshould be the same type that the first player had thrown,\n but if you do not have any, you are allowed "
			+ "to throw any card you want,\n and the player who thrown the biggest card of that type will add the cards to his "
			+ "deck\n(every player has a deck which  contains the cards which he collected them from the playground)\n independently "
			+ "of his hand, and he will start the next round by throwing any card from his hand so on,\n until the players run out of card,"
			+ " which is after 13 rounds.\n***********\n\nScore calculating\nThe score will be calculated at the end of the 13 rounds\n by adding 25 points"
			+ " on every queen you had collected in your deck\n so that the game ends when one of the players reach 150 points\n and the player"
			+ " with the minimum score will be considered as a winner. ";

	AudioPlayer player;
	Minim minim;
	
	///the  main arrays of cards
	ArrayList<field> PlayGround=new ArrayList<field>();
	ArrayList<Cards> AllCards=new ArrayList<Cards>();
	ArrayList<Cards> playedCards=new ArrayList<Cards>();

	//players hand arrays 
	ArrayList<Cards> playerhand=new ArrayList<Cards>();
	ArrayList<Cards> computer1hand=new ArrayList<Cards>();
	ArrayList<Cards> computer2hand=new ArrayList<Cards>();
	ArrayList<Cards> computer3hand=new ArrayList<Cards>();
	
	//// player objects
	Player player1;
	ComputerPlayer computer1, computer2,computer3;
	
	
	PFont f;
	PImage Bg;
	PImage card1;
	PImage card2;
	
	boolean pressed=false;
	boolean contans=false;
	
	int j1=0;
	int i=50;
	int x=330;
	int round;
	int cplayer;
	
	
	// for the first page
	String typing = "";      // Variable to store text currently being typed
	String saved = "";		// Variable to store saved text when return is hit
	
	
	
	public void keyPressed() {
		  // If the return key is pressed, save the String and clear it
		  if (key == '\n' ) {
		    saved ="this will be your name: "+ typing;
		    name =typing;
			  
		    // A String can be cleared by setting it equal to ""
		    typing = ""; 
		  } else if (key==BACKSPACE&&typing.length()!=0){
		   // to delete the last char by backspace 
		    typing = typing.substring(0,typing.length()-1);
		   }
		 else {
		    // Otherwise, concatenate the String
		    // Each character typed by the user is added to the end of the String variable.
		    
			 typing = typing + key;
		   
		}
		
	}
	
	
	
	
	
	public void setup(){	
		
		minim=new Minim(this);
		player=minim.loadFile("../aa.mp3",2048);
		
		
		for(int j=0;j<4;j++){ ///create the main array of the cards 
			for (int i = 2;i<=14;i++){
				if(j==0)
					AllCards.add(new Cards("c"+(i)));///name 
				else if(j==1)
					AllCards.add(new Cards("d"+(i)));///name 
				else if(j==2)
					AllCards.add(new Cards("h"+(i)));
				else if(j==3)
					AllCards.add(new Cards("s"+(i)));
			}
		}
	/////////////////////////////////	
	
	cardsdistribute();
		///////////////////////////////////////////////
	     player1=new Player ("",playerhand);
	     computer1=new ComputerPlayer("East",computer1hand);
		 computer2=new ComputerPlayer("North",computer2hand);
	     computer3=new ComputerPlayer("West",computer3hand);
	
		background(224,224,224);////set the color of background
		size (1000,650);////set the size of window
		
		
		
		Bg=loadImage("../cards/background.jpg");
		card1=loadImage("../cards/Untitled.png");
		card2=loadImage("../cards/c2.gif");
		round=-1;
		
		// f = createFont("Arial",16,true); ///font
		f=loadFont("../ComicSansMS-BoldItalic-20.vlw");
	}
	public void draw(){
		if (round==-2){
			 background(0);
			 text(help,25,110);
			 noFill();
		     stroke(255);
		     text("back to menu",width-110,height-12);
		     rect(width-120,height-35,130,50);
		     if(mousePressed&&mouseX>width-120&&mouseY>height-35)
		    	 round=-1;
		     
		}
		else if(round==-1){// the first page
			background(0);
			 int indent = 25;
		 image(loadImage("../start.png"),200,350);
		 image(loadImage("../help.png"),600,350);	
		  
		  
		 
			  // Set the font and fill for text
			  textFont(f,19);
			  fill(255);
			  frameRate(150);
			  // Display everything
			  text("  Enter your name (Enter to confirm)\n         then press start to begin", indent+320, 40);
		  text(typing,indent+410,110);
			  
			  text(saved,indent+320,150);
			  textFont(f,16);
			  if(mousePressed&&mouseX>600&&mouseX<871&&mouseY>350&mouseY<474){//mousePressed&&mouseX>412&&mouseX<586&mouseY>500&mouseY<565){
				  round=-2;
			  }
			  if(mousePressed&&mouseX>200&&mouseX<471&&mouseY>350&mouseY<476){//mousePressed&&mouseX>412&&mouseX<586&mouseY>350&mouseY<415){
				  if(name==null)
					  player1.name="Player1";
				  else
					  player1.name=name;
			  round=0;}
			}

		
		else if(round==0){
			
			frameRate(10);	
			if(!player.isPlaying()){
				player=minim.loadFile("../aa.mp3",2048);
				player.play();
			}
        image(Bg,0,0);
        text(player1.toString(),x-120,height-200);
        text(computer1.toString(),width-160,25);
        text(computer2.toString(),x,25);
        text(computer3.toString(),x/2-70,25);
        noFill();
        stroke(255);
        text("restart",width-75,height-12);
        rect(width-87,height-35,100,50);
        text("menu",25,height-12);
        rect(0,height-35,87,50);
       // if(PlayGround.size()==1)
        //	playGround
        
        
        for(int k=0;k<computer1.hand.size();k++)	//east
			image(card1,width-160,i+25*k);
		
		for(int k=0;k<computer2.hand.size();k++)	//north
			image(card1,x+20*k,i);
		
		for(int k=0;k<player1.hand.size();k++)        //player	
		image(loadImage("../cards/"+player1.hand.get(k)+".gif"),(x-165)+50*k,height-175);
		
		for(int k=0;k<computer3.hand.size();k++)	//west
        	image(card1,89,i+25*k);
		
		Cards temp;
		if(player1.hand.get(0).toString().equals("c2")){
			if (mousePressed&&player1.pressedIndex(mouseX,mouseY)==0){
				temp=player1.hand.remove(0);
				PlayGround.add(new field(temp.toString(),0));
				pressed=true;
			}
		}
		if(computer1.hand.get(0).toString().equals("c2")){
			temp=computer1.hand.remove(0);
			PlayGround.add(new field(temp.toString(),1));
			pressed=true;
		}
		if(computer2.hand.get(0).toString().equals("c2")){
			temp=computer2.hand.remove(0);
			PlayGround.add(new field(temp.toString(),2));
			pressed=true;
		}
		if(computer3.hand.get(0).toString().equals("c2")){
			temp=computer3.hand.remove(0);
			pressed=true;
			PlayGround.add(new field(temp.toString(),3));
		}
		
		if(pressed){
		
		
		for(int p=0;p<PlayGround.size();p++){
			if(PlayGround.get(p).cardplayer==0)
				image(loadImage("../cards/"+PlayGround.get(p).toString()+".gif"),width/2-35,height/2);
			else if(PlayGround.get(p).cardplayer==1)///////////////////////////////////////////
				image(loadImage("../cards/"+PlayGround.get(p).toString()+".gif"),width/2+24,height/2-40);
			else if(PlayGround.get(p).cardplayer==2)///////////////////////
				image(loadImage("../cards/"+PlayGround.get(p).toString()+".gif"),width/2-35,height/2-90);
			else if(PlayGround.get(p).cardplayer==3)
				image(loadImage("../cards/"+PlayGround.get(p).toString()+".gif"),width/2-90,height/2-40);
		}
		
		
		
		if(PlayGround.size()<4&&!PlayGround.isEmpty()){
			if(PlayGround.get(j1).cardplayer==0){
				temp=computer1.hand.remove(computer1.Throw(PlayGround,computer2.hand,computer3.hand,player1.hand));
				PlayGround.add(new field(temp.toString(),1));
				j1++;}	
			else if(PlayGround.get(j1).cardplayer==1){
				temp=computer2.hand.remove(computer2.Throw(PlayGround,computer1.hand,computer3.hand,player1.hand));
				PlayGround.add(new field(temp.toString(),2));
				j1++;
			}	
			else if(PlayGround.get(j1).cardplayer==2){
				temp=computer3.hand.remove(computer3.Throw(PlayGround,computer2.hand,computer1.hand,player1.hand));
				PlayGround.add(new field(temp.toString(),3));
				j1++;
			}	
			else if(PlayGround.get(j1).cardplayer==3){
				int startindex=0;
				int endindex=0;
				int correct=0;
				contans=false;
				for (int k=0;k<player1.hand.size();k++ ){
				if(player1.hand.get(k).type.equals(PlayGround.get(0).type)){
					contans=true;
					if(correct==0){
						startindex=k;
						correct=1;}
				endindex=k;}}
				if (contans&&mousePressed&&player1.pressedIndex(mouseX,mouseY)>=startindex&&player1.pressedIndex(mouseX,mouseY)<=endindex){
					temp=player1.hand.remove(player1.pressedIndex(mouseX,mouseY));
					PlayGround.add(new field(temp.toString(),0));
					j1++;	
				}
				if (!contans&&mousePressed&&player1.pressedIndex(mouseX,mouseY)<=player1.hand.size()&&player1.pressedIndex(mouseX,mouseY)>=0){
					temp=player1.hand.remove(player1.pressedIndex(mouseX,mouseY));
					PlayGround.add(new field(temp.toString(),0));
					j1++;
				}	
			}
			
		}
		else{
			printfield();
		}
	}///////////////////////////////////////////////////////////////////////
		if(mousePressed&&mouseX>width-87&&mouseY>height-35){
			restart();
			
			}
		if(mousePressed&&mouseX<87&&mouseY>height-35){
			menu();
			
			}
			
  }else if(round>0&&round<13){
	  
		if(!player.isPlaying()){
			player=minim.loadFile("../aa.mp3",2048);
			player.play();
		}
	  
	  image(Bg,0,0);
      text(player1.toString(),x-120,height-200);
      text(computer1.toString(),width-160,25);
      text(computer2.toString(),x,25);
      text(computer3.toString(),x/2-70,25);
      noFill();
      stroke(255);
      text("restart",width-75,height-12);
      rect(width-87,height-35,100,50);
      text("menu",25,height-12);
      rect(0,height-35,87,50);
      // if(PlayGround.size()==1)
      //	playGround
      
      // Insert the cards of the player in the correct place
      for(int k=0;k<computer1.hand.size();k++)	//east
			image(card1,width-160,i+25*k);
		
		for(int k=0;k<computer2.hand.size();k++)	//north
			image(card1,x+20*k,i);
		
		for(int k=0;k<player1.hand.size();k++)        //player	
		image(loadImage("../cards/"+player1.hand.get(k)+".gif"),(x-165)+50*k,height-175);
		
		for(int k=0;k<computer3.hand.size();k++)	//west
      	image(card1,89,i+25*k);
		
		Cards temp;
		if(!pressed){
		if(cplayer==0){///play the first card in the rounds after the first one
			if (mousePressed&&player1.pressedIndex(mouseX,mouseY)<=player1.hand.size()&&player1.pressedIndex(mouseX,mouseY)>=0){
				temp=player1.hand.remove(player1.pressedIndex(mouseX,mouseY));
				PlayGround.add(new field(temp.toString(),0));
				pressed=true;}
		}
		else if(cplayer==1){
			temp=computer1.hand.remove(computer1.Throw(PlayGround,computer2.hand,computer3.hand,player1.hand));
		PlayGround.add(new field(temp.toString(),1));
		pressed=true;}
		else if(cplayer==2){
			temp=computer2.hand.remove(computer2.Throw(PlayGround,computer1.hand,computer3.hand,player1.hand));
		PlayGround.add(new field(temp.toString(),2));
		pressed=true;}
		else if(cplayer==3){
			temp=computer3.hand.remove(computer3.Throw(PlayGround,computer2.hand,computer1.hand,player1.hand));
		PlayGround.add(new field(temp.toString(),3));
		pressed=true;}
		}
		if(pressed){//// to be sure that player1 ply his turn
			
			for(int p=0;p<PlayGround.size();p++){ ///// Insert the card of the field in the interface 
				if(PlayGround.get(p).cardplayer==0)
					image(loadImage("../cards/"+PlayGround.get(p).toString()+".gif"),width/2-35,height/2);
				else if(PlayGround.get(p).cardplayer==1)///////////////////////////////////////////
					image(loadImage("../cards/"+PlayGround.get(p).toString()+".gif"),width/2+24,height/2-40);
				else if(PlayGround.get(p).cardplayer==2)///////////////////////
					image(loadImage("../cards/"+PlayGround.get(p).toString()+".gif"),width/2-35,height/2-90);
				else if(PlayGround.get(p).cardplayer==3)
					image(loadImage("../cards/"+PlayGround.get(p).toString()+".gif"),width/2-90,height/2-40);
			}
			
			///throttle
			
		///////////////////////////////////////////////	
		if(PlayGround.size()<4&&!PlayGround.isEmpty()){//give the order of turns in every round
			if(PlayGround.get(j1).cardplayer==0){
				temp=computer1.hand.remove(computer1.Throw(PlayGround,computer2.hand,computer3.hand,player1.hand));
				PlayGround.add(new field(temp.toString(),1));
				j1++;}	
			else if(PlayGround.get(j1).cardplayer==1){
				temp=computer2.hand.remove(computer2.Throw(PlayGround,computer1.hand,computer3.hand,player1.hand));
				PlayGround.add(new field(temp.toString(),2));
				j1++;
			}	
			else if(PlayGround.get(j1).cardplayer==2){
				temp=computer3.hand.remove(computer3.Throw(PlayGround,computer2.hand,computer1.hand,player1.hand));
				PlayGround.add(new field(temp.toString(),3));
				j1++;
			}	
			else if(PlayGround.get(j1).cardplayer==3){
				int startindex=0;
				int endindex=0;
				int correct=0;//to get the correct input when there is the same type at index 0
				contans=false;
				for (int k=0;k<player1.hand.size();k++ ){
				if(player1.hand.get(k).type.equals(PlayGround.get(0).type)){
					contans=true;
					if(correct==0){
						startindex=k;
						correct=1;}
				endindex=k;}}
				if (contans&&mousePressed&&player1.pressedIndex(mouseX,mouseY)>=startindex&&player1.pressedIndex(mouseX,mouseY)<=endindex){
					temp=player1.hand.remove(player1.pressedIndex(mouseX,mouseY));
					PlayGround.add(new field(temp.toString(),0));
					j1++;	
				}
				if (!contans&&mousePressed&&player1.pressedIndex(mouseX,mouseY)<=player1.hand.size()&&player1.pressedIndex(mouseX,mouseY)>=0){
					temp=player1.hand.remove(player1.pressedIndex(mouseX,mouseY));
					PlayGround.add(new field(temp.toString(),0));
					j1++;
				}	
			}
			
		}
		////////////////////////////////////
		else{
			printfield();
		}}
		if(mousePressed&&mouseX>width-87&&mouseY>height-35){
			restart();}
		if(mousePressed&&mouseX<87&&mouseY>height-35){
			menu();
			}
  }else if(round==13){
	  
	  player1.calscore();
	  computer1.calscore();
	  computer2.calscore();
	  computer3.calscore();
	  player1.deck.clear();
	  computer1.deck.clear();
	  computer2.deck.clear();
	  computer3.deck.clear();
	  if(player1.score<150 && computer1.score<150&& computer2.score<150&& computer3.score<150){
		  cardsdistribute();
			round=0;
	  }else{ round=77;
	  image(Bg,0,0);/////////////////////////to print last score
	  textFont(f,35);
	  text("Game Over",x+50,height-550);
	  textFont(f,18);
	  text(player1.toString(),x,height-250);
      text(computer1.toString(),x,height-300);
      text(computer2.toString(),x,height-350);
      text(computer3.toString(),x,height-400);
     noFill();
     stroke(255);
     text("restart",width-75,height-12);
     rect(width-87,height-35,100,50);
     
    }
  }else	if(mousePressed&&mouseX>width-87&&mouseY>height-35){
	
	  player1.score=0;
	  computer1.score=0;
	  computer2.score=0;
	  computer3.score=0;
	  player1.deck.clear();
	  computer1.deck.clear();
	  computer2.deck.clear();
	  computer3.deck.clear();
	 
	  cardsdistribute();
	  
	  round=0;}
	  
	  
	  
	  
  }
	public void printfield(){
		cplayer=field.PlayerIndex(PlayGround);//add the cards in the field to the correct player
		if(cplayer==0)
			for(int k=0;k<PlayGround.size();k++)
				player1.deck.add(new Cards(PlayGround.get(k).toString()));
		else if(cplayer==1)
			for(int k=0;k<PlayGround.size();k++)
				computer1.deck.add(new Cards(PlayGround.get(k).toString()));
		else if(cplayer==2)
			for(int k=0;k<PlayGround.size();k++)
				computer2.deck.add(new Cards(PlayGround.get(k).toString()));
		else if(cplayer==3)
			for(int k=0;k<PlayGround.size();k++)
				computer3.deck.add(new Cards(PlayGround.get(k).toString()));
		
		PlayGround.clear();
		j1=0;	
		pressed=false;
		round++;
		contans=false;
	}
	public void menu(){
		restart();
		round=-1;
		player.close();
		player=minim.loadFile("../aa.mp3",2048);
	}
		public void restart(){
			j1=0;
			contans=false;
			pressed=false;
			 player1.score=0;
			  computer1.score=0;
			  computer2.score=0;
			  computer3.score=0;
			  player1.deck.clear();
			  computer1.deck.clear();
			  computer2.deck.clear();
			  computer3.deck.clear();
			  player1.hand.clear();
			  computer1.hand.clear();
			  computer2.hand.clear();
			  computer3.hand.clear();
			 PlayGround.clear();
			  cardsdistribute();
			  round=0;
			  
		}
		
		
	public  void cardsdistribute(){
		for(int o=0 ;o<52;o++)              //create a new array of all cards to create the players hands
			playedCards.add(AllCards.get(o));
		/////////////////////////////
		for(int i=0 ;i<13;i++){
			int a =(int)(Math.random()*(playedCards.size()));
			Cards temp=playedCards.remove(a);
			if(playerhand.size()==0)          //sorting
				playerhand.add(temp);
			else{int pos;
				for( pos =0;pos<playerhand.size()&&temp.compareTo(playerhand.get(pos))>0;pos++){}
				playerhand.add(pos,temp);
			}	
			///////////////////////////////////////
			a =(int)(Math.random()*(playedCards.size()));
			 temp=playedCards.remove(a);
			if(computer1hand.size()==0)       //sorting
				computer1hand.add(temp);
			else{int pos;
				for( pos =0;pos<computer1hand.size()&&temp.compareTo(computer1hand.get(pos))>0;pos++){}
				computer1hand.add(pos,temp);
			}	
			//////////////////////////////////////
			a =(int)(Math.random()*(playedCards.size()));
			 temp=playedCards.remove(a);
				if(computer2hand.size()==0)         //sorting
					computer2hand.add(temp);
				else{int pos;
					for( pos =0;pos<computer2hand.size()&&temp.compareTo(computer2hand.get(pos))>0;pos++){}
					computer2hand.add(pos,temp);
				}	
			//////////////////////////////////////
			a =(int)(Math.random()*(playedCards.size()));
			 temp=playedCards.remove(a);
				if(computer3hand.size()==0)            //sorting
					computer3hand.add(temp);
				else{int pos;
					for( pos =0;pos<computer3hand.size()&&temp.compareTo(computer3hand.get(pos))>0;pos++){}
					computer3hand.add(pos,temp);
				}	
		}
	}
		
		
		
		
		
 }

	
	
	
	
	
	
	
	
	
	
	


