package player;

import java.util.LinkedList;

import chords.Chord;
import chords.RythmicChord;

/**
 * @author Julien ABADJI
 *	Permet de contrôler le Thread ChordPlayer.
 */
public class ThreadedChordPlayer{
	ChordPlayer player;
	Thread thread;
	public ThreadedChordPlayer(ChordPlayer player) {
		this.player = player;
		thread = new Thread(player);
	}
	
	public void play(LinkedList<RythmicChord> notes){
		player.fill(notes);
		thread.start();
		
	}
	public void stop(){
		thread.interrupt();
		thread = new Thread(player);
	}
	public Player getChordPlayer(){
		
		return this.player;
	}
}
