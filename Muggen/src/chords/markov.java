package chords;

public class markov {
	private int[] mat_transition;
	private int nbrSommet;
	
	public markov(int nbrSommet){
		this.setNbrSommet(nbrSommet);
		setMat_transition(new int[nbrSommet]);
	}

	public int[] getMat_transition() {
		return mat_transition;
	}

	public void setMat_transition(int[] mat_transition) {
		this.mat_transition = mat_transition;
	}

	public int getNbrSommet() {
		return nbrSommet;
	}

	public void setNbrSommet(int nbrSommet) {
		this.nbrSommet = nbrSommet;
	}
	
	

}
