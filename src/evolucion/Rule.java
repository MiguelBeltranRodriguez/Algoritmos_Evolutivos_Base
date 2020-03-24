package evolucion;

import java.util.ArrayList;

public class Rule {
	private ArrayList<Integer> genes;
	
	public Rule(int sizeRules) {
		genes = new ArrayList<Integer>();
		for (int i = 0; i < sizeRules; i++) {
			int genRandom = SystemAG.generarRandom();
			genes.add(genRandom);
		}
	}

	public Rule(Rule rule) {
		genes = new ArrayList<Integer>();
		for (Integer integer : rule.genes) {
			genes.add(new Integer(integer.intValue()));
		}
	}

	public ArrayList<Integer> getGenes() {
		return genes;
	}

	public void setGenes(ArrayList<Integer> genes) {
		this.genes = genes;
	}

	public void mutated() {
		int genMutated = (int)(Math.random()*(genes.size()));
		int genRandom = SystemAG.generarRandom();
		genes.set(genMutated, genRandom);
	}
	
}
