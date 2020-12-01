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
 * CodeBlock class
 * Class that holds a block of code, and the different complexities held
 * within it
 */
public class CodeBlock {

    //Block types that the program should detect
    public static final String[] BLOCK_TYPES = {"def", "for", "while", "if",
            "elif", "else"};
    public static final int DEF = 0, FOR = 1, WHILE = 2, IF = 3, ELIF = 4,
            ELSE = 5;

    private Complexity blockComplexity = new Complexity(),
            highestSubComplexity = new Complexity();
    private String name = "", loopVariable;

    /**
     * Default constructor for CodeBlock
     */
    public CodeBlock(){
        this.loopVariable = null;
    }

    /**
     * getter method for blockComplexity
     * @return
     * returns blockComplexity
     */
    public Complexity getBlockComplexity() {
        return blockComplexity;
    }

    /**
     * getter method for highestSubComplexity
     * @return
     * returns highestSubComplexity
     */
    public Complexity getHighestSubComplexity() {
        return highestSubComplexity;
    }

    /**
     * getter method for name
     * @return
     * returns name
     */
    public String getName() {
        return name;
    }

    /**
     * getter method for loopVariable
     * @return
     * returns loopVariable
     */
    public String getLoopVariable() {
        return loopVariable;
    }

    /**
     * setter method for blockComplexity
     * @param blockComplexity
     * the complexity of the block
     */
    public void setBlockComplexity(Complexity blockComplexity) {
        this.blockComplexity = blockComplexity;
    }

    /**
     * setter method for highestSubComplexity
     * @param highestSubComplexity
     * the highest sub complexity of the block
     */
    public void setHighestSubComplexity(Complexity highestSubComplexity) {
        this.highestSubComplexity = highestSubComplexity;
    }

    /**
     * setter method for name
     * @param name
     * the name of the CodeBlock
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter method for loopVariable
     * @param loopVariable
     * the loopVariable that is found
     */
    public void setLoopVariable(String loopVariable) {
        this.loopVariable = loopVariable;
    }

}
