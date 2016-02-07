package chords;

import java.util.LinkedList;


public class InterMod {
	
	private LinkedList<Integer> inter = new LinkedList<Integer>();
	

	@Override
	public String toString() {
		return "InterMod [inter=" + inter + ", name=" + name + "]";
	}

	private String name;
	
	public InterMod(){
		
	}

	public LinkedList<Integer> getInter(){
		return inter;
	}
	
	public void addInter(Integer value){
		inter.addLast(value);
	}
	
	public void removeFist(){
		inter.removeFirst();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
