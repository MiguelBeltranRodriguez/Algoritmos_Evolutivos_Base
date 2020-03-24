package evolucion;

import java.util.ArrayList;

import javax.script.ScriptException;

import simulador.Simulator;

public class Individual {
	private ArrayList<Rule> rules;
	private Float fitness;
	private int id;
	
	public Individual(Individual father) {
		rules = new ArrayList<Rule>();
		for (Rule rule : father.rules) {
			rules.add(new Rule(rule));
		}
		id = SystemAG.id;
		SystemAG.id++;
		calculatedFit();
	}
	public Individual(int numRules, int sizeRules) {
		rules = new ArrayList<Rule>();
		for (int i = 0; i < numRules; i++) {
			rules.add(new Rule(sizeRules));
		}
		id = SystemAG.id;
		SystemAG.id++;
		calculatedFit();
	}

	private void calculatedFit() {
		
		Simulator sReglas = new Simulator();
		try {
			sReglas.run(this);
//			System.out.println(" Correcto " + id);
		} catch (ScriptException e) {
//			System.out.println(" Invalido script" + id);
		}catch(ClassCastException e){
//			System.out.println(" Raro "+id);
		}
		
		int numBacterias = sReglas.getBacterias().size();
		float energiaTotal = sReglas.getEnergiaTotal();
		
		
		
		int numBacteriasReal = SystemAG.sReal.getBacterias().size();
		float energiaTotalReal = SystemAG.sReal.getEnergiaTotal();
		
		int diferenciaBacterias = Math.abs(numBacteriasReal-numBacterias);
		float diferenciaEnergia = Math.abs(energiaTotalReal-energiaTotal);
		
		
		fitness = (diferenciaBacterias*80 + diferenciaEnergia*20)/(sReglas.reglasValidas+1);
//		System.out.print(" fitness "+this.fitness);
		
	}
	
	public ArrayList<Rule> getRules() {
		return rules;
	}

	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}

	public Float getFitness() {
		return fitness;
	}

	public void setFitness(Float fitness) {
		this.fitness = fitness;
	}
	public void mutated() {
		int genMutated = (int)(Math.random()*(rules.size()));
		Rule ruleMutated = rules.get(genMutated);
		ruleMutated.mutated();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

		

	
	
	
}
