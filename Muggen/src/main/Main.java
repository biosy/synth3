package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;
import javax.sound.midi.Track;


import modes.Mode;
import musicGeneration.PlayerType;
import musicGeneration.SaveType;
import musicGeneration.Type;
import jm.music.data.*;
import jm.JMC;
import jm.audio.*;
import jm.util.*;
 
public class Main implements JMC {
	
	public static void main(String[] args) throws InvalidMidiDataException, IOException, InterruptedException {
		//PlayerType pl = new PlayerType("salut");
	
		//pl.play();
		
		ArrayList<Integer> instru = new ArrayList<Integer>();
		instru.add(1);
		SaveType sv = new SaveType(new Type(instru, instru, 5, new Mode(1), 120, "walabilay"), "testos.txt");
	}
	
	
}
