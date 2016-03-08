package main;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;
import javax.sound.midi.Track;

import com.sun.prism.impl.Disposer.Record;

import musicGeneration.PlayerType;
import jm.music.data.*;
import jm.JMC;
import jm.audio.*;
import jm.util.*;
 
public class Main implements JMC {
	
	public static void main(String[] args) throws InvalidMidiDataException, IOException, InterruptedException {
		PlayerType pl = new PlayerType("salut");
		pl.randomize();
		pl.play();
		//pl.getPlayerAll().stop();
		pl.getPlayerAll().setInstruMelody(0);
		pl.getPlayerAll().stop();
		pl.play();
		pl.getPlayerAll().save("wesh.midi");
		//pl.play();
	}
	
	
}
