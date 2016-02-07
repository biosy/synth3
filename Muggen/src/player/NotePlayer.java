package player;

import java.util.LinkedList;

import note.RythmicNote;
import rythm.Rythm;

public class NotePlayer extends Player implements Runnable{
	LinkedList<RythmicNote> notes;
	public NotePlayer(int channel, int instrument, Rythm rythm){
		super(channel, instrument, rythm);
	}

	
	public void fill(LinkedList<RythmicNote> notes){
		this.notes = notes;
	}


	@Override
	public void run() {
		try {
			while(!notes.isEmpty()){ //Tant que la LinkedList n'est pas vide, on la lit.
					play(notes.removeFirst());
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		
		}
	}
}
