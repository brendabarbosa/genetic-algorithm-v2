public class GeneticAlgorithm {

    public static void main(String[] args) {
        int generation = 0;
        Population population = new Population();
        population.initialize(Constants.POPULATIONSIZE);
        population.calculateFitness();

        System.out.println("Generation: " + generation);
        population.showPopulation();

        while (population.getFittest().getFitness() != 100.00) {
            generation++;
            population.evolve();
            population.mutate();
            population.calculateFitness();

            System.out.println("Generation: " + generation);
            population.showPopulation();
        }
        System.out.println("\nSolution found in generation " + generation);
        population.showSolution();

    }

}
