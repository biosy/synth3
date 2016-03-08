package player;

import java.util.LinkedList;

import RythmDecomposition.RandRythm;
import rythm.Rythm;
import chords.Chord;
import chords.RythmicChord;
/**
 * @author Julien ABADJI
 *	Permet de lire une liste chaînée de notes, en les envoyant au Player(Chord).
 *	C'est un Thread, ce qui permet de lire les accords en même temps que les notes se lisent. 
 */
public class ChordPlayer extends Player implements Runnable{
	LinkedList<RythmicChord> chords;
	LinkedList<RythmicChord> chordsPlayed;

	public ChordPlayer(int channel, int instrument, Rythm rythm){
		super(channel, instrument, rythm);
		chordsPlayed = new LinkedList<RythmicChord>();
	}
	@Override
	public void run(){
		try{
			while(!chords.isEmpty()){
					System.out.println(chords.getFirst().getChord());
					System.out.println(chords.getFirst().getChord().getName());

				chordsPlayed.addLast(chords.getFirst());
					playRythmChords(chords.removeFirst());
				}
					
			
		}catch(InterruptedException e){
			//e.printStackTrace();
		}
	}
	public void fill(LinkedList<RythmicChord> chords){
		this.chords = chords;
	}
	
	LinkedList<RythmicChord> getChordsPlayed(){
		return chordsPlayed;
	}


		
}
