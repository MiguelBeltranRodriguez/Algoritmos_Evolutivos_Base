package evolucion;

import java.util.ArrayList;
import java.util.Collections;

import simulador.Simulator;



public class SystemAG {
	private ArrayList<Individual> population;
	private int numPopulation;
	private int numRules;
	public static int id = 0;
	public static Simulator sReal;
	
    public static void main(String[] args){
        
    	SystemAG s = new SystemAG(20, 4, 14);
    	System.out.println("Inicial: ");
    	s.print();
    	s.run(400);
    	System.out.println("Final: ");
    	s.print();
    }
	
	
	
	
	
	private void print() {
		for (Individual individual : population) {
			System.out.println(" individuo: "+individual.getId() + " Fitt: "+individual.getFitness());
			for (Rule rule : individual.getRules()) {
				for (Integer numero : rule.getGenes()) {
					System.out.print(" "+numero );
				}
				System.out.println();
			}
		}
	}





	public SystemAG(int numPopulation, int numRules, int sizeRules) {
		this.numPopulation = numPopulation;
		this.numRules = numRules;
		sReal = new Simulator();
		sReal.run();
		population = new ArrayList<Individual>();
		for (int i = 0; i < numPopulation; i++) {
			population.add(new Individual(numRules, sizeRules));
		}
	}	
	public void run(int numberTicks) {
		for (int i = 0; i < numberTicks; i++) {
			selection();
			completePopulation();
		}
		
	}
	private void completePopulation() {
		ArrayList<Individual> childs = new ArrayList<Individual>();
		for (int i = population.size(); i < numPopulation; i++) {
			int prop = (int)(Math.random()*population.size());
			Individual individual = population.get(prop);
			int propChild = (int)(Math.random()*101);
			if(propChild<20) {
				childs.add(childMutated(individual));
			}else if(propChild<=100){
				childs.add(childCombinated(individual));
			}
		}
		population.addAll(childs);
			
	}
	private Individual childMutated(Individual father) {
		Individual individualChild = new Individual(father);
		individualChild.mutated();
		
		return individualChild;
	}
	private Individual childCombinated(Individual father) {
		int propChild = (int)(Math.random()*(population.size()));
		
		Individual mother = population.get(propChild);
		int propRules = (int)(Math.random()*(numRules));
		
		Individual individualChild = new Individual(father);
		individualChild.getRules().set(propRules, new Rule(mother.getRules().get(propRules)));
		
		return individualChild;
	}
	private void selection() {
		Collections.shuffle(population);
		int i = 0;
		while(i<(numPopulation/2)) {
			Individual first = population.get(i);
			Individual second = population.get(population.size()-i-1);
			if(first.getFitness()<=second.getFitness()) {
				population.remove(second);
			}
			else {
				population.remove(first);
			}
			i++;
		}
	}
	public ArrayList<Individual> getPopulation() {
		return population;
	}

	public void setPopulation(ArrayList<Individual> population) {
		this.population = population;
	}
	
	public static int generarRandom() {
		int g = (int)(Math.random()*(101));
		if(g<3) {
			return 2;
		}else if(g<48) {
			return 1;
		}else {
			return 0;
		}
	}
}
