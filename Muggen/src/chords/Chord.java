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
	String name="";
	
	public String getName(){
		return name;
	}
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
			//System.out.println(j);
			this.notes.add(new HarmonicNote(p.getPiano(j).getHeight()));
			
			
		}
		
		if(notes.size()==3){
			if((notes.get(1).getHeight()-notes.get(0).getHeight() == 4)&&(notes.get(2).getHeight()-notes.get(1).getHeight() == 3)){

				switch(notes.get(0).getHeight()){
				case 60 : 
					name = "C";
					break;
					
				case 61 : 
					name = "C#";
					break;
					
				case 62 : 
					name = "D";
					break;
					
				case 63 : 
					name = "Eb";
					break;
					
				case 64 : 
					name = "E";
					break;
					
				case 65 : 
					name = "F";
					break;
					
				case 66 : 
					name = "F#";
					break;
					
				case 67 : 
					name = "G";
					break;
					
				case 68 : 
					name = "G#";
					break;
					
				case 69 : 
					name = "A";
					break;
					
				case 70 : 
					name = "Bb";
					break;
					
				case 71 : 
					name = "B";
					break;
				}
			}
				if((notes.get(1).getHeight()-notes.get(0).getHeight() == 3)&&(notes.get(2).getHeight()-notes.get(1).getHeight() == 4)){
					switch(notes.get(0).getHeight()){
					case 60 : 
						name = "Cm";
						break;
						
					case 61 : 
						name = "C#m";
						break;
						
					case 62 : 
						name = "Dm";
						break;
						
					case 63 : 
						name = "Ebm";
						break;
						
					case 64 : 
						name = "Em";
						break;
						
					case 65 : 
						name = "Fm";
						break;
						
					case 66 : 
						name = "F#m";
						break;
						
					case 67 : 
						name = "Gm";
						break;
						
					case 68 : 
						name = "G#m";
						break;
						
					case 69 : 
						name = "Am";
						break;
						
					case 70 : 
						name = "Bbm";
						break;
						
					case 71 : 
						name = "Bm";
						break;
					}
			}
			}
			
		
		
		else if(notes.size()==4){
			
			if((notes.get(1).getHeight()-notes.get(0).getHeight() == 4)&&(notes.get(2).getHeight()-notes.get(1).getHeight() == 3)){
				switch(notes.get(0).getHeight()){
				case 60 : 
					name = "C7";
					break;
					
				case 61 : 
					name = "C#7";
					break;
					
				case 62 : 
					name = "D7";
					break;
					
				case 63 : 
					name = "Eb7";
					break;
					
				case 64 : 
					name = "E7";
					break;
					
				case 65 : 
					name = "F7";
					break;
					
				case 66 : 
					name = "F#7";
					break;
					
				case 67 : 
					name = "G7";
					break;
					
				case 68 : 
					name = "G#7";
					break;
					
				case 69 : 
					name = "A7";
					break;
					
				case 70 : 
					name = "Bb7";
					break;
					
				case 71 : 
					name = "B7";
					break;
				}
			}
				if((notes.get(1).getHeight()-notes.get(0).getHeight() == 3)&&(notes.get(2).getHeight()-notes.get(1).getHeight() == 4)){
					switch(notes.get(0).getHeight()){
					case 60 : 
						name = "Cm7";
						break;
						
					case 61 : 
						name = "C#m7";
						break;
						
					case 62 : 
						name = "Dm7";
						break;
						
					case 63 : 
						name = "Ebm7";
						break;
						
					case 64 : 
						name = "Em7";
						break;
						
					case 65 : 
						name = "Fm7";
						break;
						
					case 66 : 
						name = "F#m7";
						break;
						
					case 67 : 
						name = "Gm7";
						break;
						
					case 68 : 
						name = "G#m7";
						break;
						
					case 69 : 
						name = "Am7";
						break;
						
					case 70 : 
						name = "Bbm7";
						break;
						
					case 71 : 
						name = "Bm7";
						break;
					}
			}
			
		}
		System.out.println(name);

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
