import java.util.Arrays;
import java.util.Random;

public class Individual {
    private int x; // 2 genes
    private int y; // 3 genes
    private int w; // 2 genes
    private int z; // 3 genes
    private double result;
    private double fitness = 0;

    private int[] chromosome = new int[10];

    public Individual(){
        Random rn = new Random();
        for (int i = 0; i < chromosome.length; i++) {
            chromosome[i] = Math.abs(rn.nextInt() % 2);
        }
        decode();
    }

    public Individual(int[] chromosome) {
        this.chromosome = chromosome;
        decode();
    }

    public int getGene(int i){
        return chromosome[i];
    }

    /**
     * b) Função de aptidão
     */
    public void calculateFitness(){
        result = (int) (5*this.x + Math.pow(this.y,2) + this.w +  Math.pow(this.z,3));
        if(result == 0){
            fitness = 0;
            return;
        }

        if(result <= Constants.FINALVALUE){
            fitness = result*100/Constants.FINALVALUE;
        }else{
            fitness = (100 - ((result-Constants.FINALVALUE)/Constants.FINALVALUE)*100);
        }
    }

    public void mutate(){
        Random random = new Random();
        Integer randomGene = random.nextInt(chromosome.length);
        int newGene = chromosome[randomGene] == 1 ? 0 : 1;
        chromosome[randomGene] = newGene;
    }

    public int[] getChromosome() {
        return chromosome;
    }

    public String getChromosomeToString() {
        StringBuilder sb = new StringBuilder();
        for (int gene : chromosome) sb.append(gene);
        return sb.toString();
    }

    /**
     * a) Codificação de cromossomos, quantidade de genes pra cada número
     */
    private void decode(){
        String chromosomeX = String.valueOf(chromosome[0])+String.valueOf(chromosome[1]);
        String chromosomeY = String.valueOf(chromosome[2])+String.valueOf(chromosome[3])+String.valueOf(chromosome[4]);
        String chromosomeW = String.valueOf(chromosome[5])+String.valueOf(chromosome[6]);
        String chromosomeZ = String.valueOf(chromosome[7])+String.valueOf(chromosome[8])+String.valueOf(chromosome[9]);

        this.x = Integer.parseInt(chromosomeX,2);
        this.y = Integer.parseInt(chromosomeY,2);
        this.w = Integer.parseInt(chromosomeW,2);
        this.z = Integer.parseInt(chromosomeZ,2);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public double getResult() {
        return result;
    }
    public int getW() {
        return w;
    }
    public int getZ() {
        return z;
    }
    public double getFitness() {
        return fitness;
    }
}
