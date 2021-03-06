package musicGeneration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.sound.midi.InvalidMidiDataException;

import chords.ChordGeneration;
import note.RythmicNote;
import main.Recorder;
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
		music.generation();
		this.tempo = tempo;
		this.scale = scale;

	}
	
	public ThreadedChordPlayer getThreadedChordPlayer(){
		return th;
	}
	
	public ThreadedNotePlayer getThreadedNotePlayer(){
		return tn;
	}
	public void play(){
		
		
		reload();
		 th = new ThreadedChordPlayer(new ChordPlayer(0, instruChord, new Rythm(new TimeSignature(4, 4, tempo))));
		 tn = new ThreadedNotePlayer(new NotePlayer(1, instruMelody, new Rythm(new TimeSignature(4, 4, tempo))));
		th.play(music.getChords().getChords());
		tn.Create_Process(music.getMelody().getMelody().getMelody());

		
	}
	
	public void stop(){
		th.stop();
		th.getChordPlayer().stopPlayer();
		tn.stop();
		tn.getChordPlayer().stopPlayer();

		
	}
	
	public void reload(){
		music.getMelody().getMelody().getMelody().clear();
		music.getChords().getChords().clear();
		music = new MusicGeneration(scale, new Rythm(new TimeSignature(4, 4, tempo)),seuil);

		music.generation();

		
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
	
	public void save(String file) throws InvalidMidiDataException, IOException{
		Recorder recorder = new Recorder(tn.getChordPlayer().getNotePayed(), music.getChords(), file, tempo, instruMelody, instruChord);
		recorder.record(file);
	}
}
