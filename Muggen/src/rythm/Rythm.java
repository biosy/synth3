package rythm;
/**
 * classe de donn�e : converti un nombre de temps en secondes
 *
 * @author AKTOR Alexis
 *
 */
public class Rythm 
{
	private TimeSignature ts;//signature de la partition
	public Rythm(TimeSignature ts)
	{
		this.ts=ts;
	}
	
	/**
	 * @param nombre de temps � convertir
	 * @return float, le temps en seconde
	 */
	public float convertTime(int length)//méthode pour convertir un nombre de temps en seconde
	{
		float croche=60000/ts.getTempo();//durée d'une croche en seconde
		float millisecond = 0;//durée d'un temps
		switch(length)
		{
		case 0 ://1 : durée d'une triple croche
				millisecond= (croche/4);
		break;
		case 1 ://2 : durée d'une double croche
				millisecond= (croche/2);
		break;
		case 2 ://4 : durée d'une note d'un triolet
				millisecond= ((croche*2)/3);
		break;
		case 3 ://8 : durée d'une croche
				millisecond= (croche);
		break;
		case 4 ://16 : durée d'une noire
				millisecond= (croche*2);
		break;
		case 5 ://16 : durée d'une noire pointée
				millisecond= (croche*3);
		break;
		case 6 ://16 : durée d'une blanche
				millisecond= (croche*4);
		break;
		case 7 ://16 : durée d'une blanche pointée
				millisecond= (croche*5);
		break;
		case 8 ://16 : durée d'une ronde
				millisecond= (croche*8);
		break;
		case 9 ://16 : durée d'une ronde pointée
				millisecond= (croche*12);
		break;
		}
		return  (millisecond);
	}
}