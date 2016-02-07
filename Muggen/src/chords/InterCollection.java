package chords;

import java.util.Iterator;
import java.util.LinkedList;

public class InterCollection {
	
	private LinkedList<InterMod> collection ;
	
	public InterCollection(){
		collection = new LinkedList<InterMod>();
	}

	public LinkedList<InterMod> getCollection() {
		return collection;
	}

	public void setCollection(LinkedList<InterMod> collection) {
		this.collection = collection;
	}

	public void add(InterMod inter){
		collection.addFirst(inter);
	}

	public String toString() {
		return "InterCollection [collection=" + collection + "]";
	}
	
	
	public int searchByName(String name){
		int i=0;
		InterMod mod = new InterMod();
		 Iterator<InterMod> iter = collection.iterator();
		    while (iter.hasNext())
		   {
		    	mod = iter.next();
		    	if(mod.getName().equals(name)){
		    		return i;
		    	}
		    	i++;
		    }
		    
		return -1;
	}
	
	public InterMod searchByNametoMod(String name){
		InterMod mod = new InterMod();
		 Iterator<InterMod> iter = collection.iterator();
		    while (iter.hasNext())
		   {
		    	mod = iter.next();
		    	if(mod.getName().equals(name)){
		    		return mod;
		    	}
		    }
		    
		return null;
	}
}

