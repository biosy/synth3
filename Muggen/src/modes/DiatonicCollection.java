//Dernière modification le 22/02/2015

package modes;

import java.util.ArrayList;

/**
 * @author JOHAN
 * La diatonicCollection permet d'intancier tous les intervalls de mode d'un seul coup
 */
public class DiatonicCollection{ //Diatonic collection instancie 7 modes d'un seul tenant dans une arrayList de modes.
	
	private ArrayList<Mode> diatonicCollection = new ArrayList<Mode>();
	private int a = 2;
	private int b = 6;
	
	/* 
	 *  ici on instancie 7 MODES diatonics et pour les recup c'est moche
	 * pour récup UN MODE specific faites un tour dans SpecificMode qui fait essentiellement la meme chose mais renvoit UN SEUL MODE
	 * DE VOTRE CHOIX ;)
	 */
	
	/**
	 * 
	 */
	public DiatonicCollection(){ //la arrayList<mode> de la collection est remplis de mode ainsi: do,ré,mi,fa,sol,la,si. (dans cet ordre).
		ArrayList<Integer> intervals = new ArrayList<Integer>(); 
		
		for(int i = 0; i < 7; i++){ //les 7 modes
			for(int j = 0; j < 7; j ++){
				if(j == a || j == b){
					intervals.add(1);
				}
				else{
				intervals.add(2);
				}
			}
				ArrayList<Integer> saveIntervals = (ArrayList<Integer>) intervals.clone();//la arrayList de sauvegarde evite le vidage systématique des modes par la procédure intervals.clear()
				Mode newMode = new Mode(saveIntervals);
				diatonicCollection.add(newMode);
				intervals.clear();//sans "saveIntervals" cette procédure deleterai tous les arraylist d'intervals dans les modes de la arrayList des modes Diatonics.
				a--;
				b--;
				if(a == 0){
					a = 6;
				}
		}	
	}
	
	/**
	 * @return retourne l'objet diatonicCollection sous forme de Arraylist de mode
	 */
	public ArrayList<Mode> getDiatonicModes(){
		return diatonicCollection;
	}

}
