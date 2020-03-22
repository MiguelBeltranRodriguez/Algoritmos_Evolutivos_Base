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
public class Reglas {
    
    private ArrayList<Integer> genotipo;
    private Integer fitness;
    
    public Reglas(int lengthGenotipo){
        genotipo = new ArrayList<>();
        generateGenotipo(lengthGenotipo, genotipo);
        fitness = this.get_fitness();
    }
    
    public void process(){
        for (int i = 0; i < genotipo.size(); i++) {
            if(genotipo.get(i)==2){
                if(i >= 6 && i <= 8){
                    genotipo.set(6, 2);
                    genotipo.set(7, 2);
                    genotipo.set(8, 2);
                }
                else if(i >= 10 && i <= 12){
                    genotipo.set(10, 2);
                    genotipo.set(11, 2);
                    genotipo.set(12, 2);
                }
                else if(i >= 18 && i <= 20){
                    genotipo.set(18, 2);
                    genotipo.set(19, 2);
                    genotipo.set(20, 2);
                }
                else if(i >= 26 && i <= 28){
                    genotipo.set(26, 2);
                    genotipo.set(27, 2);
                    genotipo.set(28, 2);
                }
                else if(i >= 38 && i <= 40){
                    genotipo.set(38, 2);
                    genotipo.set(39, 2);
                    genotipo.set(40, 2);  
                }
                else if(i >= 42 && i <= 44){
                    genotipo.set(42, 2);
                    genotipo.set(43, 2);
                    genotipo.set(44, 2);   
                }
                else if(i >= 50 && i <= 52){
                    genotipo.set(50, 2);
                    genotipo.set(51, 2);
                    genotipo.set(52, 2);
                }
                else if(i >= 58 && i <= 60){
                    genotipo.set(58, 2);
                    genotipo.set(59, 2);
                    genotipo.set(60, 2);     
                }
            }
        }
    }
    
    public Integer get_fitness(){
        Integer finalValue = 0;
        if(genotipo.get(0) == 1){
            finalValue = finalValue + 1;
        }
        if(genotipo.get(1) == 0){
            finalValue = finalValue + 1;
        }
        else{
            finalValue = finalValue + 2;
        }
        return finalValue;
    }
    
    public void mutate(){
        int lenghtGenotipo = genotipo.size();
        
        do{
            int genPosition = (int)(Math.random()*lenghtGenotipo);
            int randomMutate;
            //5,6,7,8,10,11,12,13,18,19,20,21,26,27,28,29,37,38,39,40,42,43,44,45,50,51,52,53,58,59,60,61
            if(
                    (genPosition >=5 && genPosition <= 8) ||
                    (genPosition >= 10 && genPosition <= 13) ||
                    (genPosition >= 18 && genPosition <= 21) ||
                    (genPosition >= 26 && genPosition <= 29) ||
                    (genPosition >= 37 && genPosition <= 40) ||
                    (genPosition >= 42 && genPosition <= 45) ||
                    (genPosition >= 50 && genPosition <= 53) ||
                    (genPosition >= 58 && genPosition <= 61)
                ){
                randomMutate = (int)(Math.random()*2);
            }
            else{
                randomMutate = (int)(Math.random()*1);
            }
            
      
            Integer gen = genotipo.get(genPosition);
            
            
            if(gen == 0){
                if(randomMutate == 0){
                    gen = 1;
                }
                else{
                    gen = 2;
                }
            }
            else if (gen == 1){
                if(randomMutate == 0){
                    gen = 0;
                }
                else{
                    gen = 2;
                }
            }
            else{
                if(randomMutate == 0){
                    gen = 1;
                }
                else{
                    gen = 0;
                }
            }
            genotipo.set(genPosition, gen);
            fitness = this.get_fitness();
        }while(!(this.isValid()));
    }
    
    public void generateGenotipo(Integer length, ArrayList<Integer> genotipoI){
        genotipoI.clear();
        for(int i = 0; i < length; i++){
            Integer genRandom = (int)(Math.random()*2);
            Integer gen;
            if (genRandom >= 1){ 
                gen = 1; 
            }
            else{
                gen = 0;
            }

            genotipoI.add(gen);
        }
        if(!this.isValid()){
            generateGenotipo(length, genotipoI);
        }
    }
    
    //context requires
    public Boolean isValid(){
        Integer ruleSize = (SI_Proyecto1_AG.SIZE_GENOTYPE/SI_Proyecto1_AG.NUMBER_RULES);
        Boolean valid = true;
            //number of rules
            for (int i = 0; i < SI_Proyecto1_AG.NUMBER_RULES; i++) {
                if(genotipo.get(0+(ruleSize*i)) == 1 && genotipo.get(1+(ruleSize*i)) == 1){ //null value
                    valid = false;
                }
                
                if(genotipo.get(2+(ruleSize*i)) == 1 && genotipo.get(3+(ruleSize*i)) == 1){ //null value
                    valid = false;
                }
                
                if(genotipo.get(6+(ruleSize*i)) == 1 && genotipo.get(7+(ruleSize*i)) == 1){ //null value
                    valid = false;
                }
                
                if(genotipo.get(10+(ruleSize*i)) == 1 && genotipo.get(11+(ruleSize*i)) == 1){ //null value
                    valid = false;
                }
            }
        return valid;
    }
    //--atribute handler
    public ArrayList<Integer> getGenotipo(){
        return genotipo;
    }
    
    public int getFitness(){
        return fitness;
    }
    
    public void setGenotipo(ArrayList<Integer> genotipoParameter){
        genotipo = genotipoParameter;
        fitness = this.get_fitness();
    } 
    
    public void addGenotipo(Integer gen){
        genotipo.add(gen);
        fitness = this.get_fitness();
    }
    
}
