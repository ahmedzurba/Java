
public class Cards {
	
	public int value;
	public String type;
	
	public Cards(String str){
		this.type=str.substring(0,1);
		this.value=Integer.parseInt(str.substring(1));
	}
	public boolean equals(Cards card1){
		if ((this.value==card1.value)&&(this.type.equals(card1.type))){
			return true;
		}
		else return false;
		}
	public int compareTo(Cards a){
		if((this.type.equals(a.type))&&(this.value>a.value))
			return +1;
		else  return this.type.compareTo(a.type);
	}
	public String toString(){
		return type+value;
	}
	
	
	
	}
	


