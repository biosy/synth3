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
	private Scale scale;
	private ThreadedChordPlayer th;
	private ThreadedNotePlayer tn;
	private ThreadedNotePlayer td;

	private int instruChord;
	private int instruMelody;
	private int seuil;
	private int tempo;
	public PlayerAll(Scale scale, int instruChord, int instruMelody, int seuil, int tempo){
		 th = new ThreadedChordPlayer(new ChordPlayer(0, instruChord, new Rythm(new TimeSignature(4, 4, tempo))));
		 tn = new ThreadedNotePlayer(new NotePlayer(1, instruMelody, new Rythm(new TimeSignature(4, 4, tempo))));
		 td = new ThreadedNotePlayer(new NotePlayer(2, 0, new Rythm(new TimeSignature(4, 4, tempo))));

		 this.seuil = seuil;
		music = new MusicGeneration(scale, new Rythm(new TimeSignature(4, 4, tempo)),10);
	}
	
	public void play(){
		
		music.generation();
		LinkedList<RythmicNote> mg = (LinkedList<RythmicNote>) music.getMelody().getMelody().getMelody().clone();
		th.play(music.getChords().getCollection().getChords().getHarmonizedChords());
		tn.Create_Process(mg);
		//td.Create_Process(music.getMelody().getMelody().getMelody());

		
	}

}
