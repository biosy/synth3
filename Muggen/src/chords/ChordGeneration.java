package chords;

import java.util.Random;

import note.HarmonicNote;
import scales.Scale;

public class ChordGeneration {
	
	private InitChordCollection ch;
	private Scale scale;
	public ChordGeneration(Scale scale) {
		ch = new InitChordCollection(scale);
		this.scale = scale;
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
		
	}
	
	public InitChordCollection getCollection(){
		return ch;
	}

}
