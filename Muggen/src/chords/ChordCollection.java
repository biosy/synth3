package chords;

import java.util.ArrayList;
import java.util.LinkedList;

import scales.Scale;


public class ChordCollection {
	LinkedList<Chord> harmonizedChords = new LinkedList();
	Scale scale;

	
	public ChordCollection(Scale scale){
		this.scale = scale;
		
	}
	
	public void add(Chord chord){
		harmonizedChords.addLast(chord);
	}
	
	public LinkedList<Chord> getHarmonizedChords(){
		return harmonizedChords;
	}
	
	public String toString(){
		return harmonizedChords.toString();
	}
	public Scale getScale(){
		return scale;
	}
}
