package player;

import java.util.LinkedList;

import piano.Piano;
import note.RythmicNote;
import rythm.Rythm;

public class NotePlayer extends Player implements Runnable{
	LinkedList<RythmicNote> notes;
	LinkedList<RythmicNote> notePlayed;

	public NotePlayer(int channel, int instrument, Rythm rythm){
		super(channel, instrument, rythm);
		notePlayed = new LinkedList<RythmicNote>();
	}

	
	public void fill(LinkedList<RythmicNote> notes){
		this.notes = notes;
	}


	@Override
	public void run() {
		try {
			while(!notes.isEmpty()){ //Tant que la LinkedList n'est pas vide, on la lit.
					//System.out.println(notes.getFirst().getHeight()+" dure :"+notes.getFirst().getDuration());
					notePlayed.addLast(notes.getFirst());
					play(notes.removeFirst());
			}
		}catch(InterruptedException e){
			//e.printStackTrace();
		
		}
	}
	
	public LinkedList<RythmicNote> getNotePayed(){
		return notePlayed;
	}
	
}
