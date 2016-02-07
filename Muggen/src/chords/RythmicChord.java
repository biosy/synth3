package chords;

public class RythmicChord {
	
	private int duration;
	private Chord chord;
	
	public RythmicChord(Chord chord, int duration){
		this.setDuration(duration);
		this.setChord(chord);
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Chord getChord() {
		return chord;
	}

	public void setChord(Chord chord) {
		this.chord = chord;
	}
	
	

}
