/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si_proyecto1_ag;

import java.util.ArrayList;
import sun.security.util.Length;

/**
 *
 * @author Barajas-d
 */
public class Sistema {

    private ArrayList<Bacteria> population;
    private int lengthGenotipo;
    
    public Sistema(int sizePopulation, int genotipolenght) {
        population = new ArrayList<>();
        lengthGenotipo = genotipolenght;
        for (int i = 0; i < sizePopulation; i++) {
            Bacteria newBacteria = new Bacteria(lengthGenotipo);
            population.add(newBacteria);
        }
    }
    
    public void printSystem(){
        int sizePopulation = population.size();
        for (int i = 0; i < sizePopulation; i++) {
            System.out.println("GENOTIPO" + i +": " + population.get(i).getGenotipo() + "FITNESS: " + population.get(i).getFitness());
        }
    }
    
    public void newPopulation(){
        int lenghtPopulation = this.population.size();
        for (int i = 0; i < lenghtPopulation; i++) {
            Bacteria mutated = new Bacteria(lengthGenotipo);
            mutated.setGenotipo(this.population.get(i).getGenotipo());
            mutated.mutate();
            population.add(mutated);
        }
    }
    
    public void selection(){
        this.newPopulation();
        int sizePopulation = population.size();
        int i, j;
        Bacteria aux;
        for (i = 0; i < sizePopulation - 1; i++) {
            for (j = 0; j < population.size() - i - 1; j++) {
                if (population.get(j+1).getFitness() < population.get(j).getFitness()) {
                    aux = population.get(j+1);
                    population.add(j+1, population.get(j));
                    population.remove(j+2);
                    population.add(j, aux);
                    population.remove(j+1);
                }
            }
        }
        
        while(population.size() != (sizePopulation/2)){
            population.remove(0);
        }
        
    }
}
