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
	
	public void Create_Process(){
		thread.start();
	}
	
	public void Create_Process(LinkedList<RythmicNote> notes){
		player.fill(notes);
		thread.start();

	}
	public void stop(){
		thread.interrupt();
		thread = new Thread(player);

	}
	
	public NotePlayer getChordPlayer(){
		
		return this.player;
	}
}
