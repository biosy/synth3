package player;

import java.util.LinkedList;

import javax.sound.midi.MidiChannel;

import note.RythmicNote;
import rythm.Rythm;

public class ThreadedNotePlayer{
	NotePlayer player;
	Thread thread;
	public ThreadedNotePlayer(NotePlayer player) {
		this.player = player;
		thread = new Thread(player);
	}
	
	public void play(LinkedList<RythmicNote> notes){
		player.fill(notes);
		thread.start();
	}
	
	public void stop(){
		thread.interrupt();
	}
	
	public Player getChordPlayer(){
		
		return this.player;
	}
}
