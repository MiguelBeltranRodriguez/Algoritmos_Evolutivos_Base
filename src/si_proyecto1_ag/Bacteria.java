/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si_proyecto1_ag;

import java.util.ArrayList;

/**
 *
 * @author Barajas-d
 */
public class Bacteria {
    
    private ArrayList<Boolean> genotipo;
    private Integer fitness;

    public Bacteria(int lengthGenotipo){
        genotipo = generateGenotipo(lengthGenotipo);
        fitness = this.get_fitness();
    }
    
    public Integer get_fitness(){
        Integer finalValue = 0;
        if(genotipo.get(0) == false){
            finalValue = finalValue + 1;
        }
        if(genotipo.get(1) == false){
            finalValue = finalValue + 1;
        }
        else{
            finalValue = finalValue + 2;
        }
        return finalValue;
    }
    
    public void mutate(){
        int lenghtGenotipo = genotipo.size();
        int genPosition = (int)(Math.random()*lenghtGenotipo);
        Boolean gen = genotipo.get(genPosition);
        
        if(gen){
            gen = false;
        }
        else{
            gen = true;
        }
        
        genotipo.set(genPosition, gen);
        fitness = this.get_fitness();
    }
    
    public ArrayList<Boolean> generateGenotipo(Integer length){
        ArrayList<Boolean> genotipoI = new ArrayList<>();
        for(int i = 0; i < length; i++){
            Integer genRandom = (int)(Math.random()*2);
            Boolean gen;
            if (genRandom >= 1){ 
                gen = true; 
            }
            else{
                gen = false;
            }
            
            genotipoI.add(gen);
        }
        return genotipoI;
    }
    
    
    //--atribute handler
    public ArrayList<Boolean> getGenotipo(){
        return genotipo;
    }
    
    public int getFitness(){
        return fitness;
    }
    
    public void setGenotipo(ArrayList<Boolean> genotipoParameter){
        genotipo = genotipoParameter;
        fitness = this.get_fitness();
    } 
    
    public void addGenotipo(Boolean gen){
        genotipo.add(gen);
        fitness = this.get_fitness();
    }
    
}
