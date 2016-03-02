package chords;

import note.HarmonicNote;
import scales.Scale;

public class InitChordCollection {
	private ChordCollection chords;
	private Scale scale;
	private LoadInter li;
	public InitChordCollection(Scale scale){
		setChords(new ChordCollection(scale));
		this.setScale(scale);
		li = new LoadInter("chords.txt");
	}
	
	public void anatole1(){	
		//anatole
		HarmonicNote note = new HarmonicNote(scale.getDegree(1).getHeight());
		Chord ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(6).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(2).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(4).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
	}
	
	public void anatole2(){	

		HarmonicNote note = new HarmonicNote(scale.getDegree(1).getHeight());
		Chord ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(6).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(4).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(5).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);		
				
	}
	
	public void anatole3(){	
				
		HarmonicNote note = new HarmonicNote(scale.getDegree(1).getHeight());
		Chord ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(6).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(3).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(5).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);		
	}
	public void anatole4(){	
		HarmonicNote note = new HarmonicNote(scale.getDegree(1).getHeight());
		Chord ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(5).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(6).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(4).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
				
		}

	
	
	public void christophe(){		
		HarmonicNote note = new HarmonicNote(scale.getDegree(1).getHeight());
		Chord ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(1).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norm7",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(4).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norm7",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(3).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norm7",scale);
		chords.add(ch);
			
		
	}
	

	public void deux_cinq_un(){
		HarmonicNote note = new HarmonicNote(scale.getDegree(2).getHeight());
		Chord ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(5).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		note = new HarmonicNote(scale.getDegree(1).getHeight());
		ch = new Chord();
		ch.generateChord(note, "norma",scale);
		chords.add(ch);
		
		
	}
	
	public ChordCollection getChords() {
		return chords;
	}

	public void setChords(ChordCollection chords) {
		this.chords = chords;
	}

	public Scale getScale() {
		return scale;
	}

	public void setScale(Scale scale) {
		this.scale = scale;
	}

}
