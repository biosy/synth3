package player;

import java.util.LinkedList;

import rythm.Rythm;
import chords.Chord;
/**
 * @author Julien ABADJI
 *	Permet de lire une liste chaînée de notes, en les envoyant au Player(Chord).
 *	C'est un Thread, ce qui permet de lire les accords en même temps que les notes se lisent. 
 */
public class ChordPlayer extends Player implements Runnable{
	LinkedList<Chord> chords;
	public ChordPlayer(int channel, int instrument, Rythm rythm){
		super(channel, instrument, rythm);
	}
	@Override
	public void run(){
		try{
			while(!chords.isEmpty()){ //Tant que la liste n'est pas vide, on la lit.
				play(chords.removeFirst(),3);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	public void fill(LinkedList<Chord> chords){
		this.chords = chords;
	}
}
