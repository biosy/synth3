package chords;

import RythmDecomposition.RandRythm;

public class RythmicChord {
	
	private Chord chord;
	private RandRythm randRythm;
	
	public RythmicChord(Chord chord, RandRythm randRythm){
		this.setChord(chord);
		this.randRythm = randRythm;
	}

	
	public Chord getChord() {
		return chord;
	}

	public void setChord(Chord chord) {
		this.chord = chord;
	}
	
	public RandRythm getRythm(){
		return randRythm;
	}

}
