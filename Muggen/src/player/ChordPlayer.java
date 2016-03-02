package player;

import java.util.LinkedList;

import RythmDecomposition.RandRythm;
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
			int i=0;
			RandRythm ry = new RandRythm();
			ry.RandomGeneration(6, 4);
			while(i<10){ //Tant que la liste n'est pas vide, on la lit.
				for(int j=0;j<chords.size();j++){
					System.out.println(chords.get(j));
					playSeq(chords.get(j), ry);
					i++;
				}
					
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	public void fill(LinkedList<Chord> chords){
		this.chords = chords;
	}

		
}
