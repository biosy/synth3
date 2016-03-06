package chords;

import java.util.LinkedList;
import java.util.Random;

import RythmDecomposition.RandRythm;
import note.HarmonicNote;
import scales.Scale;

public class ChordGeneration {
	
	private InitChordCollection ch;
	private Scale scale;
	private LinkedList<RythmicChord> chords;
	public ChordGeneration(Scale scale) {
		ch = new InitChordCollection(scale);
		this.scale = scale;
		chords = new LinkedList<RythmicChord>();
	}
	
	
	public void aleatoryStyle(){
		
		
		Random rand = new Random();
		int random = rand.nextInt(6);
		switch(random){
		case 0 :
			ch.anatole1();
			break;
		case 1 :
			ch.anatole2();
			break;
		case 2 :
			ch.anatole3();
			break;
		case 3 :
			ch.anatole4();
			break;
			
		case 4 :
			ch.deux_cinq_un();
			break;
		case 5 :
			ch.christophe();
			break;
		
			
			
		}
		RandRythm randRythm = new RandRythm();
		randRythm.RandomGeneration(6, 4);
		RythmicChord rythmChords;
		for(int i=0;i<ch.getChords().getHarmonizedChords().size();i++){
			rythmChords = new RythmicChord(ch.getChords().getHarmonizedChords().get(i), randRythm);
			chords.add(rythmChords);
			randRythm.RandomGeneration(6, 4);
		}
		
	}
	
	public InitChordCollection getCollection(){
		return ch;
	}
	public LinkedList<RythmicChord> getChords(){
		return chords;
	}
	
}
