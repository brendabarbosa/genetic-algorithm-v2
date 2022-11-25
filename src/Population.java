import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Population {

    List<Individual> individuals = new ArrayList<>();
    private Individual fittest;
    List<Individual> wheelSelector = new ArrayList<>();

    public void initialize(int size) {
        for (int i = 0; i < size; i++) {
            individuals.add(new Individual());
        }
    }

    public void calculateFitness(){
        for (Individual individual: this.individuals) {
            individual.calculateFitness();
        }
        this.individuals.sort((value1, value2) -> Double.compare(value2.getFitness(), value1.getFitness()));
        this.fittest = this.individuals.get(0);
    }

    public Individual getFittest() {
        return fittest;
    }

    public void showPopulation(){
        for (Individual individual: this.individuals) {
            System.out.print("W = "+individual.getW());
            System.out.print("\t\tX = "+individual.getX());
            System.out.print("\t\tY = "+individual.getY());
            System.out.print("\t\tZ = "+individual.getZ());
            System.out.print("\t\tResult = "+individual.getResult());
            System.out.print("\t\tChromosome = "+fittest.getChromosomeToString());
            System.out.println("\t\tFitness = "+individual.getFitness());
        }
        System.out.println();
    }


    public void showSolution(){
        System.out.print("W = "+fittest.getW());
        System.out.print("\t\tX = "+fittest.getX());
        System.out.print("\t\tY = "+fittest.getY());
        System.out.print("\t\tZ = "+fittest.getZ());
        System.out.print("\t\tResult = "+fittest.getResult());
        System.out.print("\t\tChromosome = "+fittest.getChromosomeToString());
        System.out.println("\t\tFitness = "+fittest.getFitness());
    }

    public void evolve(){
        List<Individual> newIndividuals = new ArrayList<>();
        generateWheel();

        for (int i=0; i < Constants.POPULATIONSIZE; i++){
            Individual parent1 = selectParent();
            Individual parent2 = selectParent();
            while (parent1.equals(parent2)){
                parent2 = selectParent();
            }
            Individual newIndividual = crossover(parent1, parent2);
            newIndividuals.add(newIndividual);
        }

        this.individuals = newIndividuals;
        this.fittest = null;
    }
    public void mutate(){
        for (Individual individual: this.individuals) {
            if(Math.random() >= Constants.MUTATERATE){
                individual.mutate();
            }
        }
    }

    private void generateWheel(){
        wheelSelector.clear();

        //selecionando apenas os 4 primeiros
        for(int contParentA = 0; contParentA < Constants.POPULATIONCUTOFF; contParentA++ ){
            for(int contParentB = contParentA; contParentB < Constants.POPULATIONCUTOFF; contParentB++){
                wheelSelector.add(individuals.get(contParentA));
            }
        }

//        for (Individual individual: this.individuals) {
//            int value = (int) Math.floor(individual.getFitness() * 10);
//            for (int i = 0; i < value; i++){
//                wheelSelector.add(individual);
//            }
//        }
    }

    private Individual crossover(Individual parent1, Individual parent2){
        Random random = new Random();
        int crossOverPoint = 5;
        int[] newChromosome = new int[parent1.getChromosome().length];
        for (int i = 0; i < parent1.getChromosome().length; i++) {
            if (i > crossOverPoint) {
                newChromosome[i] = parent1.getGene(i);
            } else {
                newChromosome[i] = parent2.getGene(i);
            }
        }
        return new Individual(newChromosome);
    }

    private Individual selectParent(){
        Random random = new Random();
        Integer randomIndex = random.nextInt(1, wheelSelector.size());
        return this.wheelSelector.get(randomIndex);
    }

}
