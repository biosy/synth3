package main;

import java.util.ArrayList;

import RythmDecomposition.RythmDecomposition;
import RythmDecomposition.RythmGeneration;
import chords.Chord;
import chords.ChordGeneration;
import chords.LoadInter;
import player.ChordPlayer;
import player.NotePlayer;
import player.ThreadedChordPlayer;
import player.ThreadedNotePlayer;
import rythm.Rythm;
import rythm.TimeSignature;
import scales.Scale;
import melody.MelodyGeneration;
import modes.Mode;
import musicGeneration.PlayerAll;
import note.HarmonicNote;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		/*LoadInter inter = new LoadInter("chords.txt");
		
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
		
		/*Markov mar = new Markov(10);
		mar.addLink(0, 0, 50);
		mar.addLink(0, 1, 50);
		mar.addLink(1, 0, 50);
		mar.addLink(1, 2, 50);
		mar.addLink(2, 2, 50);
		mar.addLink(2, 3, 50);
		*/
	
		/*	ChordGeneration cg = new ChordGeneration(new Scale(new HarmonicNote(60), new Mode(5)));
			cg.aleatoryStyle();
			Chord chord = new Chord();
			chord = cg.getCollection().getChords().getHarmonizedChords().get(0);

			MelodyGeneration melo = new MelodyGeneration(new Scale(new HarmonicNote(60), new Mode(5)), chord);

			ThreadedChordPlayer th = new ThreadedChordPlayer(new ChordPlayer(0, 0, new Rythm(new TimeSignature(4, 4, 120))));
			ThreadedNotePlayer tn = new ThreadedNotePlayer(new NotePlayer(1, 80, new Rythm(new TimeSignature(4, 4, 120))));
			RythmGeneration ry = new RythmGeneration();
			
			
			//for(int size=0;size<10;size++){
				for(int i=0;i<cg.getCollection().getChords().getHarmonizedChords().size();i++){
					ry.generateDecomposition(4);
					chord = cg.getCollection().getChords().getHarmonizedChords().get(i);	
					System.out.println(chord);

					melo.setChord(chord);
					melo.reinit();

					melo.generate(ry);
					System.out.println(melo.getMelody());

					//System.out.println("0");
				

				}

	//}
			th.play(cg.getCollection().getChords().getHarmonizedChords());
			tn.Create_Process(melo.getMelody().getMelody());
*/
		PlayerAll p  = new PlayerAll(new Scale(new HarmonicNote(60), new Mode(5)), 0, 72,4);
		p.play();
	

		
		
	}
	
	
	

}
