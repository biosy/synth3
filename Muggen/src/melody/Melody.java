package melody;

import java.util.LinkedList;

import note.RythmicNote;

public class Melody {
	
	private LinkedList<RythmicNote> melody;
	
	public Melody(){
		melody = new LinkedList<RythmicNote>();
	}

	public LinkedList<RythmicNote> getMelody() {
		return melody;
	}

	public void setMelody(LinkedList<RythmicNote> melody) {
		this.melody = melody;
	}
	
	public void add(RythmicNote note){
		melody.addFirst(note);
	}

}
