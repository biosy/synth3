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

import rythm.Rythm;
import rythm.TimeSignature;
import chords.ChordGeneration;
import melody.MelodyGeneration;


public class Recorder {
	
	private MelodyGeneration melody;
	private ChordGeneration chords;
	private String file;
	private int tempo;
	private int instrument;
	public Recorder(MelodyGeneration melody, ChordGeneration chords, String file, int tempo, int instrument){
		this.melody = melody;
		this.chords = chords;
		this.file = file;
		this.tempo = tempo;
		this.instrument = instrument;
	}
	
	private void record() throws InvalidMidiDataException, IOException{
		
		Sequence s = new Sequence(javax.sound.midi.Sequence.PPQ,24);
		Track t = s.createTrack();
		byte[] b = {(byte)0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte)0xF7};
		SysexMessage sm = new SysexMessage();
		sm.setMessage(b, 6);
		MidiEvent me = new MidiEvent(sm,(long)0);
		t.add(me);
		
		/*tempo*/
		MetaMessage mt = new MetaMessage();
        byte[] bt = {0x02, (byte)0x00, 0x00};
		mt.setMessage(0x51 ,bt, 3);
		me = new MidiEvent(mt,(long)0);
		t.add(me);
		
		//****  set track name (meta event)  ****
				mt = new MetaMessage();
				String TrackName = new String("midifile track");
				mt.setMessage(0x03 ,TrackName.getBytes(), TrackName.length());
				me = new MidiEvent(mt,(long)0);
				t.add(me);

		//****  set omni on  ****
				ShortMessage mm = new ShortMessage();
				mm.setMessage(0xB0, 0x7D,0x00);
				me = new MidiEvent(mm,(long)0);
				t.add(me);

		//****  set poly on  ****
				mm = new ShortMessage();
				mm.setMessage(0xB0, 0x7F,0x00);
				me = new MidiEvent(mm,(long)0);
				t.add(me);

		//****  set instrument   ****
				mm = new ShortMessage();
				mm.setMessage(0xC0, instrument, 0x00);
				me = new MidiEvent(mm,(long)0);
				t.add(me);
				
				int begin =0;
				int end = 0;
				Rythm ry = new Rythm(new TimeSignature(4, 4, 120));
		for(int i=0;i<melody.getMelody().getMelody().size();i++){
			//****  note on - middle C  ****
			
			mm = new ShortMessage();
			mm.setMessage(0x90,melody.getMelody().getMelody().get(i).getHeight(),0);
			me = new MidiEvent(mm,(long)begin);
			t.add(me);

	//****  note off - middle C - 120 ticks later  ****
			end = end + (int)ry.convertTime(melody.getMelody().getMelody().get(i).getHeight());
			mm = new ShortMessage();
			mm.setMessage(0x80,melody.getMelody().getMelody().get(i).getHeight(),0);
			me = new MidiEvent(mm,(long)end);
			t.add(me);
			begin = end;
			
			mt = new MetaMessage();
	        byte[] bet = {}; // empty array
			mt.setMessage(0x2F,bet,0);
			me = new MidiEvent(mt, (long)140);
			t.add(me);

	//****  write the MIDI sequence to a MIDI file  ****
			File f = new File("midifile.mid");
			MidiSystem.write(s,1,f);
		
		}
	}
	
	

}
