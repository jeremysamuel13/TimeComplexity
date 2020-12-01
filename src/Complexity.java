/*
 * @author Jeremy Samuel
 * E-mail: jeremy.samuel@stonybrook.edu
 * Stony Brook ID: 113142817
 * CSE 214
 * Recitation Section 3
 * Recitation TA: Dylan Andres
 * HW #3
 */

/**
 * Complexity class
 * Holds exponents of n and log n to define complexity of a CodeBlock
 */
public class Complexity {
    private int nPower, logPower;

    /**
     * Default constructor
     * Sets complexity to O(1) at construction
     */
    public Complexity() {
        nPower = 0;
        logPower = 0;

    }

    /**
     * Constructor with parameters
     * @param nPower
     * set power of n (ex. when nPower = 2, complexity = O(n^2))
     * @param logPower
     * set power of logN (ex. when logPower = 2, complexity = O(log^2(n))
     */
    public Complexity(int nPower, int logPower) {
        this.nPower = nPower;
        this.logPower = logPower;
    }

    /**
     * Getter method for logPower
     * @return
     * returns logPower
     */
    public int getLogPower() {
        return logPower;
    }

    /**
     * Getter method for nPower
     * @return
     * returns nPower
     */
    public int getNPower() {
        return nPower;
    }

    /**
     * Setter method for logPower
     * @param logPower
     * sets logPower
     */
    public void setLogPower(int logPower) {
        this.logPower = logPower;
    }

    /**
     * Setter method nPower
     * @param nPower
     * sets nPower
     */
    public void setNPower(int nPower) {
        this.nPower = nPower;
    }

    /**
     * Makes string representation of the complexity
     * @return
     * Returns string representation of the complexity
     */
    public String toString() {
        //starts string with the beginning of big O notation
        String x = "O(";

        //adds n^nPower to string if applicable
        if (nPower > 0) {
            if(getNPower() == 1)
                x = x + "n";
            else
                x = x + "n^" + getNPower();
        }

        //adds log(n)^logPower to the string if applicable
        if (logPower > 0) {
            //case for no nPower element in string
            if (x.equals("O(")) {
                if(getLogPower() == 1)
                    x = x + "log(n)";
                else
                    x = x + "log(n)^" + getLogPower();

            }
            //case with nPower element in string
            else {
                if(getLogPower() == 1)
                    x = x + " * log(n)";
                else
                    x = x + " * log(n)^" + getLogPower();
            }
        }

        //if no nPower or logPower, then it is just O(1)
        if (nPower == 0 && logPower == 0)
            x = "O(1";


        //adds closing parenthesis to big O notation
        x = x + ")";

        return x;
    }

    /**
     * Compares complexity of two given complexities
     * @param comp1
     * complexity 1
     * @param comp2
     * complexity 2
     * @return
     * returns true if comp1 > comp2 in terms of complexity, false if otherwise
     */
    public static boolean compareComplexity(Complexity comp1, Complexity comp2){
        return (comp1.getNPower() > comp2.getNPower() || (comp1.getNPower() ==
                comp2.getNPower() && (comp1.getLogPower() >
                comp2.getLogPower())));
    }

    /**
     * multiplies the two given complexities. used when calculating 
     * complexities of nested loops
     * @param comp1
     * complexity 1
     * @param comp2
     * complexity 2
     * @return
     * returns the two multiplied complexity of the two given complexities
     */
    public static Complexity multiplyComplexity(Complexity comp1,
                                                Complexity comp2){
        return new Complexity(comp1.getNPower() + comp2.getNPower(),
                comp1.getLogPower() + comp2.getLogPower());
    }

}
