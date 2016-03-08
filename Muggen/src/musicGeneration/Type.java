package musicGeneration;

import java.util.ArrayList;

import modes.Mode;
import scales.Scale;


public class Type {
	private ArrayList<Integer> instruChord;
	private ArrayList<Integer> instruMelody;
	private int seuil;
	private Mode mode;
	private int tempo;
	private String name;
	public Type(ArrayList<Integer> instrumentMelody, ArrayList<Integer> instrumentsChords, int seuil, Mode mode, int tempo, String name){
		this.instruChord = instrumentsChords;
		this.instruMelody = instrumentMelody;
		this.seuil =seuil;
		this.mode =mode;
		this.name=name;
		this.tempo = tempo;
	}

	public int getSeuil() {
		return seuil;
	}

	

	public ArrayList<Integer> getInstruMelody() {
		return instruMelody;
	}


	public ArrayList<Integer> getInstruChords() {
		return instruChord;
	}

	public int getTempo() {
		return tempo;
	}

	public Mode getMode() {
		return mode;
	}

	@Override
	public String toString() {
		return "Type [instruChord=" + instruChord + ", instruMelody="
				+ instruMelody + ", seuil=" + seuil + ", mode=" + mode
				+ ", tempo=" + tempo + ", name=" + name + "]";
	}


	public String getName(){
		return name;
	}

	
}
