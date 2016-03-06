package musicGeneration;

import java.util.ArrayList;
import java.util.LinkedList;

import note.RythmicNote;
import melody.MelodyGeneration;
import player.ChordPlayer;
import player.NotePlayer;
import player.ThreadedChordPlayer;
import player.ThreadedNotePlayer;
import rythm.Rythm;
import rythm.TimeSignature;
import scales.Scale;

public class PlayerAll {
	
	private MusicGeneration music;
	private ThreadedChordPlayer th=null;
	private ThreadedNotePlayer tn=null;
	private ThreadedNotePlayer td;

	private int instruChord;
	private int instruMelody;
	private int seuil;
	private int tempo;
	private Scale scale;
	public PlayerAll(Scale scale, int instruChord, int instruMelody, int seuil, int tempo){
		this.setScale(scale);
		
		
		 th = new ThreadedChordPlayer(new ChordPlayer(0, instruChord, new Rythm(new TimeSignature(4, 4, tempo))));
		 tn = new ThreadedNotePlayer(new NotePlayer(1, instruMelody, new Rythm(new TimeSignature(4, 4, tempo))));
		 this.instruChord = instruChord;
		 this.instruMelody = instruMelody;
		 this.setSeuil(seuil);
		music = new MusicGeneration(scale, new Rythm(new TimeSignature(4, 4, tempo)),seuil);

	}
	
	public void play(){
		
		music.generation();
		LinkedList<RythmicNote> mg = (LinkedList<RythmicNote>) music.getMelody().getMelody().getMelody();
		th.play(music.getChords().getChords());
		tn.Create_Process(mg);

		
	}
	
	public void stop(){
		th.stop();
		th.getChordPlayer().stopPlayer();
		tn.stop();
		tn.getChordPlayer().stopPlayer();

		
	}

	public MusicGeneration getmusic(){
		return music;
	}

	public Scale getScale() {
		return scale;
	}

	public void setScale(Scale scale) {
		this.scale = scale;
	}

	public int getInstruMelody() {
		return instruMelody;
	}
	
	public int getInstruChord() {
		return instruChord;
	}

	public void setInstruMelody(int instruMelody) {
		this.instruMelody = instruMelody;
	}
	
	public void setInstruChord(int instruChord) {
		this.instruChord = instruChord;
	}

	public int getSeuil() {
		return seuil;
	}

	public void setSeuil(int seuil) {
		this.seuil = seuil;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
}
