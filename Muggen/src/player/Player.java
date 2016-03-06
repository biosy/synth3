package player;

import java.util.concurrent.TimeUnit;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import RythmDecomposition.RandRythm;
import chords.Chord;
import chords.RythmicChord;
import rythm.Rythm;
import note.HarmonicNote;
import note.RythmicNote;


public class Player{
	
	private int channel; //Channel to play. Different for chords and melody. 
	private int instrument; //Instrument played by the player. See internet for the available instruments. 
	private Synthesizer synth; //these two attrubutes manage the midi interactions. 
	private MidiChannel midiChannel;
	private Rythm rythm;

	public Player(int channel, int instrument, Rythm rythm){
		this.channel = channel;
		try {
			synth = MidiSystem.getSynthesizer(); //MIDI stuff
            synth.open();
        } catch (MidiUnavailableException ex) {
           ex.printStackTrace();
        }
        midiChannel = synth.getChannels()[channel]; //MORE MIDI STUFF.
        midiChannel.programChange(instrument);
        this.rythm = rythm;
       /* try {
			//play(new RythmicNote(60, 2)); //Jingle
			//play(new RythmicNote(67, 4));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}
	
	
	public void play(RythmicNote note) throws InterruptedException {
		midiChannel.noteOn(note.getHeight(), 100);
		Thread.sleep((long)rythm.convertTime(note.getDuration()));
		midiChannel.noteOff(note.getHeight());

		
	}
	
	public void play(Chord chord)throws InterruptedException {
		long timer = (long)rythm.convertTime(chord.toRythmicNote().get(0).getDuration());
		for(RythmicNote note : chord.toRythmicNote()){
			midiChannel.noteOn(note.getHeight(), 60);
		}
		Thread.sleep(timer);
		midiChannel.allNotesOff();
	}
	
	
	
	public void play(Chord chord,int nbr)throws InterruptedException {
		long timer = (long)rythm.convertTime(8);
		for(int i=0;i<nbr;i++){
			for(HarmonicNote note : chord.getNotes()){
				midiChannel.noteOn(note.getHeight(), 60);
			}
			Thread.sleep(timer);
			midiChannel.allNotesOff();
		}
	}
	
	public void playSeq(Chord chord, RandRythm ry)throws InterruptedException {
		for(int i=0;i<ry.getRythmes().size();i++){
			for(HarmonicNote note : chord.getNotes()){
				midiChannel.noteOn(note.getHeight(), 60);
				
			}
			Thread.sleep((long)rythm.convertTime(ry.getRythmes().get(i)));
			midiChannel.allNotesOff();
		}

		for(int i=0;i<ry.getRythmes().size();i++){
			for(HarmonicNote note : chord.getNotes()){
				midiChannel.noteOn(note.getHeight(), 60);
				
			}
			Thread.sleep((long)rythm.convertTime(ry.getRythmes().get(i)));
			midiChannel.allNotesOff();
		}
	}
	
	public void playRythmChords(RythmicChord rc) throws InterruptedException{
		for(int i=0;i<rc.getRythm().getRythmes().size();i++){
			for(HarmonicNote note : rc.getChord().getNotes()){
				midiChannel.noteOn(note.getHeight(), 60);
				
			}
			Thread.sleep((long)rythm.convertTime(rc.getRythm().getRythmes().get(i)));
			midiChannel.allNotesOff();
		}
		for(int i=0;i<rc.getRythm().getRythmes().size();i++){
			for(HarmonicNote note : rc.getChord().getNotes()){
				midiChannel.noteOn(note.getHeight(), 60);
				
			}
			Thread.sleep((long)rythm.convertTime(rc.getRythm().getRythmes().get(i)));
			midiChannel.allNotesOff();
		}
		
	}
		
	public void stopPlayer(){
		
		this.midiChannel.allNotesOff();
	}


	public int getInstrument() {
		return instrument;
	}


	public void setInstrument(int instrument) {
		this.instrument = instrument;
	}
}
