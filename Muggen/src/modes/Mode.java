//Derni�re modification le 26/02/2015

package modes;

import java.util.ArrayList;

/*
 * description de type(int) du second constructeur de mode
 * type:
 *  1 --> do (lydien) gamme MAJEUR CLASSIQUE
 *  2 --> r� (phrygien)
 *  3 --> mi (dorien)
 *  4 --> fa (hypolydien)
 *  5 --> sol (hypophrygien)
 *  6 --> la (hypodorien) gamme MINEURE NATURELLE
 *  7 --> si (mixolydien)
 *  8 --> pentatonique majeur (jazzy)
 *  9 --> pentatonique mineur (bluezy)
 *  10 --> gamme blues
 *  11 --> gamme mineure m�lodique ascendante
 *  12 --> gamme mineur harmonique
 */

/**
 * classe pour instancier un mode sp�cifique
 * @author JOHAN
 *
 */
public class Mode { //ici est d�finit un objet mode, qui est essentiellement une arrayList d'intervals (int)
					//qui associ�e � une fondamentale renvoit une arrayList de notes, et au final une belle scale toute propre.
			
	private ArrayList<Integer> intervals;
	private int type;
	/**
	 * si on poss�de d�j� une liste d'intervalles on peu cr�er un objet mode selon celle-ci en lui envoyant en param�tre la arraylist de integer.
	 * @param intervals arraylist de integer qui contient les intervalles souhait�s pour le mode perssonalis�
	 *  
	 */
	public Mode(ArrayList<Integer> intervals){
		this.intervals = intervals;
	}
	

	/**
	 * @param type ce integer permet de d�terminer lequel des 12 modes sera cr��
	 */
	public Mode(int type){ //constructeut de mode selon ce que l'on souhaite.
		
		ArrayList<Integer> newIntervals =  new ArrayList<Integer>();
		this.type = type;
		if(type > 0 && type < 8){//ici sont trait�s les modes diatonics. les types sont d�cris en haut de la classe.
			
			int a = 7 - type;
			int b = (17 - type) % 7;
			
			for(int i = 0; i < 7; i ++){
				if(i == a || i == b){
					newIntervals.add(1);
				}
				else{
					newIntervals.add(2);
				}
			}
			setMode(newIntervals);
		}
		
		else if(type == 8 || type == 9){ //attention le "mode" pentatonique ne contient que 6 intervals (en r�alit� 5 mais who cares?) 
			/* remarque || type 8 est le mode majeur de la penta et type 9 est le mode mineur */
			
			int a = 3 - (2*(type%8));
			int b = 4 + ((9-type)%9);
			for(int i = 0; i < 6; i ++){
				if(i == a || i == b){
					newIntervals.add(3);
				}
				else{
					newIntervals.add(2);
				}
			}
			setMode(newIntervals);
		}
		
		else if(type == 10){ //gamme blues.
			newIntervals.add(3);
			newIntervals.add(2);
			newIntervals.add(1);
			newIntervals.add(1);
			newIntervals.add(3);
			newIntervals.add(2);
			
			setMode(newIntervals);
		}
		
		else if(type == 11){ //gamme mineure m�lodique ascendante
			newIntervals.add(2);
			newIntervals.add(1);
			newIntervals.add(2);
			newIntervals.add(2);
			newIntervals.add(2);
			newIntervals.add(2);
			newIntervals.add(1);
			
			setMode(newIntervals);
		}
		
		else if(type == 12){//gamme mineure harmonique
			newIntervals.add(2);
			newIntervals.add(1);
			newIntervals.add(2);
			newIntervals.add(2);
			newIntervals.add(1);
			newIntervals.add(3);
			newIntervals.add(1);
			
			setMode(newIntervals);
		}
		
		else if(type < 1){
			System.out.println("ATTENTION || La valeur de l'attribut (int) du constructeur 'Mode' doit �tre sup�rieur strictement � 0.");
		}
	}

	/**
	 * @return on retourne uniquement les intervalles du mode. Utilis� pour instanci� la scale.
	 */
	public ArrayList<Integer> getIntervals(){ //en quelquesort semblable � un getmode, mais on ne r�ucp pas l'objet, juste la partie int�ressante.
		return intervals;
	}
	
	/**
	 * @param intervals arraylist d'integer pour set les intervalles du mode
	 */
	public void setMode(ArrayList<Integer> intervals){
		this.intervals = intervals;
	}
	/**
	 * @return permet de connaitre le type du mode si celui ci a �t� g�n�r� selon un typ (integer)
	 */
	public int getType(){
		return type;
	}
}