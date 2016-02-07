package main;

import java.util.LinkedList;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import chords.Chord;
import chords.ChordGeneration;
import chords.InitChordCollection;
import chords.InterMod;
import chords.LoadInter;
import chords.RythmicChord;
import player.ChordPlayer;
import player.NotePlayer;
import player.Player;
import player.ThreadedNotePlayer;
import rythm.Rythm;
import rythm.TimeSignature;
import scales.Scale;
import modes.Mode;
import note.HarmonicNote;
import note.Note;
import note.RythmicNote;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		LoadInter inter = new LoadInter("chords.txt");
		
		inter.Load("chords.txt");
		//System.out.println(inter);
		InitChordCollection cg = new InitChordCollection(new Scale(new HarmonicNote(65), new Mode(2)));
		cg.christophe();
		
		ChordPlayer p = new ChordPlayer(0, 0, new Rythm(new TimeSignature(4, 4, 200)));
		p.fill(cg.getChords().getHarmonizedChords());
		p.run();
		
		cg = new InitChordCollection(new Scale(new HarmonicNote(65), new Mode(2)));
		cg.anatole1();
		
		p.fill(cg.getChords().getHarmonizedChords());
		p.run();
		
		cg = new InitChordCollection(new Scale(new HarmonicNote(65), new Mode(2)));
		cg.deux_cinq_un();
		
		p.fill(cg.getChords().getHarmonizedChords());
		p.run();
	}

}
