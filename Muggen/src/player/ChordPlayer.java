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
	public ChordPlayer(int channel, int instrument, Rythm rythm){
		super(channel, instrument, rythm);
	}
	@Override
	public void run(){
		try{
			int i=0;
			while(i<10){ //Tant que la liste n'est pas vide, on la lit.
				for(int j=0;j<chords.size();j++){
					System.out.println(chords.get(j).getChord());
					playRythmChords(chords.get(j));
					i++;
				}
					
			}
		}catch(InterruptedException e){
			//e.printStackTrace();
		}
	}
	public void fill(LinkedList<RythmicChord> chords){
		this.chords = chords;
	}
	
	

		
}
