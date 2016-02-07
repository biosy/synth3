package rythm;

import note.RythmicNote;
/**
 * classe de données : gère le tempo
 *
 * @author AKTOR Alexis
 *
 */
public class TimeSignature {
	private int numberOfTime;//nombre de temps dans une mesure
	private int measureUnit;//unitï¿½ de temps
	private int tempo;//tempo : croche par minute
	public TimeSignature(int numberOfTime, int measureUnit, int tempo)
	{
		this.setNumberOfTime(numberOfTime);
		this.setMeasureUnit(measureUnit);
		this.setTempo(tempo);
	}

	/**
	 * @param la note que l'on veut convertir en seconde
	 * @return le temps en seconde que dure la note
	 */
	public float noteTime(int note)// on test la note pour initialiser le nombre de temps qu'elle dure avec comme unité de temps la triplecroche
	{
		float time=0;
		switch(note)
		{
		case 0:
			 time=1;
			 break;
		case 1:
			time=2;
			break;
		case 3:
			time=4;
			break;
		case 4:
			time=8;
			break;
		case 5:
			time=12;
			break;
		case 6:
			time=16;
			break;
		case 7:
			time=20;
			break;
		case 8:
			time=32;
			break;
		case 9:
			time=48;
			break;
		}
		
		switch (measureUnit)//on converti ça avec une unité de temps en (measureunit) entrée par l'utilisateur
		{
		case 1:
			time=time/32;
			break;
		case 2:
			time=time/16;
			break;
		case 4:
			time=time/8;
			break;
		case 8:
			time=time/4;
			break;
		}
		return time;
	}
	public int getNumberOfTime() {
		return numberOfTime;
	}

	public void setNumberOfTime(int numberOfTime) {
		this.numberOfTime = numberOfTime;
	}

	public int getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(int measureUnit) {
		this.measureUnit = measureUnit;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	
	

}
