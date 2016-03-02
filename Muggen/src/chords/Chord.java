package chords;
import java.util.ArrayList;
import java.util.LinkedList;

import com.sun.org.apache.xpath.internal.operations.Mod;

import piano.Piano;
import scales.Scale;
import note.HarmonicNote;
import note.RythmicNote;


public class Chord {
	private ArrayList<HarmonicNote> notes;
	private LoadInter inter;
	public Chord(ArrayList<HarmonicNote> notes){
		this.notes = notes;
		inter = new LoadInter("chords.txt");
	}
	public Chord(){
		notes = new ArrayList<HarmonicNote>();
		inter = new LoadInter("chords.txt");
	}
	
	public ArrayList<HarmonicNote> getNotes(){
		return notes;
	}
	public Chord getHarmonicChord(){ //Notes 0 to 12, without height.
		ArrayList<HarmonicNote> fhn = new ArrayList<HarmonicNote>();
		for(HarmonicNote note : notes){
			fhn.add(new HarmonicNote(note.getHeight()%12));
		}
		return new Chord(fhn);
	}
	public void addNote(HarmonicNote n){
		notes.add(n);
	}
	
	public void clear(){
		notes.clear();
	}
	
	/*
	 * Converts the ArrayList to a playable form, adding a note duration. This will make the Chord playable in a Player.
	 */
	
	
	
	public String toString(){
		return notes.toString();
	}
	
	@Override
	public boolean equals(Object other){
		if(other == null) return false;
		if(!(other instanceof Chord )) return false;
		Chord otherChord = (Chord) other;
		if(otherChord.getNotes().equals(this.getNotes())){
			return true;
		}else{
			return false;
		}
	}
	
	public void generateChord(HarmonicNote fundamental,String intermod, Scale scale){
		
		InterMod mod = inter.getIntermod().searchByNametoMod(intermod);
		
		int ht = fundamental.getHeight();
		notes = new ArrayList<HarmonicNote>();// on reset la arraylist
		Piano p = new Piano(scale);
		int j;
		for(int i=0;i<mod.getInter().size();i++){
			j= p.getIndice(fundamental);
			j = j+ (mod.getInter().get(i));
			System.out.println(j);
			this.notes.add(new HarmonicNote(p.getPiano(j).getHeight()));
			
			
		}
		
	}
	public ArrayList<RythmicNote> toRythmicNote(){
		return toRythmicNote(4);
	}
	public ArrayList<RythmicNote> toRythmicNote(int defaultNoteDuration){
		ArrayList<RythmicNote> ret = new ArrayList<RythmicNote>();
		for(HarmonicNote hn : notes ){
			ret.add(hn.toRythmicNote(defaultNoteDuration));
		}
		return ret;
	}
}
