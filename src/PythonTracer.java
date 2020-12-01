/*
 * @author Jeremy Samuel
 * E-mail: jeremy.samuel@stonybrook.edu
 * Stony Brook ID: 113142817
 * CSE 214
 * Recitation Section 3
 * Recitation TA: Dylan Andres
 * HW #3
 */

import java.io.*;
import java.util.*;

/**
 * PythonTracer class
 * Interacts with the given python file
 */
public class PythonTracer {

    //constant that defines the total space count of an indent
    public static final int SPACE_COUNT = 4;

    /**
     * main method
     */
    public static void main(String[] args) {

        //scanner
        Scanner scan = new Scanner(System.in);

        //allows the while loop to be switched on or off
        boolean active = true;

        //input for command (asks for a single letter)
        String input;

        while(active) {

            System.out.println("Please enter a file name (or 'quit' to quit):" +
                    " ");
            input = scan.nextLine();

            if ("quit" .equals(input)) {
                active = false;
                System.out.println("Program terminating successfully...");
            }else {
                try {
                    Complexity q = traceFile(input);
                    String f = null;
                    if (input.lastIndexOf("/") > -1) {
                        if((input.charAt(input.length() - 1) != '/'))
                            f =
                                    "\nOverall complexity of " + input.substring
                                            (input.lastIndexOf("/") + 1) +
                                            ": " + q.toString() + "\n";
                        else if(input.charAt(input.length() - 1) == '/')
                            f =
                                    "\nOverall complexity of " + input.substring
                                            (input.substring(0, input.
                                                    lastIndexOf("/")).
                                                    lastIndexOf('/') + 1,
                                                    input.length() - 1) + ": " +
                                            q.toString() + "\n";
                    }else
                        f =
                                "\nOverall complexity of " + input + ": " +
                                        q.toString();

                    System.out.println(f);


                    //System.out.println(q.toString());
                } catch (IOException e) {
                    System.out.println("Invalid file");
                }
            }

        }

    }

    /**
     * takes inputted python file and goes through it line by line, and then
     * gets
     * the total complexity of the code
     * @param filename
     * the location of the file
     * @return
     * returns the complexity of the python code
     * @throws IOException
     * throws exception of filename, file location, or the file itself is
     * invalid
     */
    public static Complexity traceFile(String filename) throws IOException {
        BlockStack stack = new BlockStack();
        String lastBlockName = "";

        if (filename != null) {

            //reads file
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader inStream = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(inStream);

            CodeBlock oldTop;
            Complexity oldTopComplexity;
            String line, prevName = null;
            int prevIndent = 0;
            String loopVariable = null;
            String[] split;

            //keeps running the loop while there are lines available to read
            while((line = reader.readLine()) != null){

                //skips comments in the given python project
                if(!line.contains("#") && !line.isEmpty()) {

                    //keeps track of the name of the old block
                    if (!stack.isEmpty()) {
                        lastBlockName = stack.peek().getName();
                    }

                    //keeps track of the indents of the current line
                    int indents = line.indexOf(line.trim()) / SPACE_COUNT;

                    //updates code blocks
                    while (indents < stack.size()) {
                        if (indents == 0 && stack.size() == 1) {
                            String g = "Leaving block " + lastBlockName;
                            System.out.println(g);
                            reader.close();
                            return stack.peek().getHighestSubComplexity();
                        } else {
                            oldTop = stack.pop();
                            //keeps track of the name of the old block
                            if (!stack.isEmpty()) {
                                lastBlockName = stack.peek().getName();
                            }
                            oldTopComplexity =
                                    Complexity.multiplyComplexity(oldTop.
                                            getBlockComplexity(), oldTop.
                                            getHighestSubComplexity());
                            if (Complexity.compareComplexity(oldTopComplexity,
                                    stack.peek().getHighestSubComplexity())) {

                                String u =
                                        "Leaving block " + oldTop.getName() +
                                                " updating block " +
                                                stack.peek().getName() + "\n";
                                if(stack.peek().getName().length() < oldTop.
                                        getName().length())
                                    stack.peek().setHighestSubComplexity
                                            (Complexity.multiplyComplexity
                                            (oldTopComplexity, stack.peek().
                                            getHighestSubComplexity()));
                                else
                                    stack.peek().setHighestSubComplexity
                                            (oldTopComplexity);

                                u = u  + printBlockInfo(stack.peek());

                                System.out.println(u);

                            }
                        }
                    }
                    
                    String keyword = "";
                    boolean containsKeyword = false;
                    CodeBlock logN = new CodeBlock(), n = new CodeBlock(),
                            o1 = new CodeBlock();
                    
                    //searches for keyword in line
                    for(int i = 0; i < CodeBlock.BLOCK_TYPES.length; i++){
                        if(line.contains(" " + CodeBlock.BLOCK_TYPES[i] + " ")
                                || line.contains("def")){
                            keyword = CodeBlock.BLOCK_TYPES[i];
                            containsKeyword = true;
                            break;
                        }
                        containsKeyword = false;
                    }


                    //adds proper block to stack if keyword is found
                    if(containsKeyword) {
                        if (keyword.equals("for")) {
                            if (line.contains("in log_N")) {
                                logN.setBlockComplexity(new Complexity
                                        (0, 1));
                                stack.push(logN);
                            } else {
                                n.setBlockComplexity(new Complexity
                                        (1,0));
                                stack.push(n);
                            }
                        } else if (keyword.equals("while")) {
                            split = line.split(" ");
                            loopVariable = split[split.length - 3];
                            o1.setLoopVariable(loopVariable);
                            stack.push(o1);
                        } else {
                            stack.push(o1);
                        }

                        //naming the codeBlock
                        String x;
                        if(indents == 0)
                            x = "1";
                        else if(indents > prevIndent) {
                            x = prevName + ".1";
                        }else if(indents == prevIndent) {
                            int tempInt = Integer.parseInt(prevName.
                                    substring(prevName.length() - 1));
                            x = prevName.substring(0, prevName.length() - 1) +
                                    "." + (tempInt + 1);
                        }else {
                            int tempInt =
                                    Integer.parseInt(prevName.substring
                                            (prevName.length() - 3,
                                                    prevName.length() - 2));
                            x =
                                    prevName.substring(0,
                                            prevName.length() - 3) +
                                            (tempInt + 1);
                        }

                        stack.peek().setName(x);
                        prevName = x;

                        System.out.println("Entering block " + stack.peek().
                                getName() + " '" + keyword + "'");
                        System.out.println(printBlockInfo(stack.peek()));


                        prevIndent = indents;

                        //if loopVariable of a block is found, the complexity
                        // of the block is updated
                    }else if (!stack.isEmpty() && stack.peek().getLoopVariable()
                            != null){
                        if(stack.peek().getLoopVariable().
                                equals(loopVariable)) {

                            String u =
                                    "Found update statement, updating block " +
                                            stack.peek().getName() + "\n";

                            if (line.contains(loopVariable + " /=") ||
                                    line.contains(loopVariable + " *=")) {
                                stack.peek().getBlockComplexity().
                                        setLogPower(1);
                                u = u + printBlockInfo(stack.peek());

                                System.out.println(u);
                            } else if (line.contains(loopVariable + " +=") ||
                                    line.contains(loopVariable + " -=")) {
                                stack.peek().getBlockComplexity().setNPower(1);
                                u = u + printBlockInfo(stack.peek());

                                System.out.println(u);
                            }


                        }
                    }
                }
            }

        }else{
            //throws if the filename is null
            throw new IOException("Null filename");
        }

        String g = "Leaving block "  + lastBlockName;
        System.out.println(g);

        try {
            return stack.pop().getHighestSubComplexity();
        }catch (EmptyStackException e){
            //only throws exception if the file given has only comments
            throw new IOException();
        }

    }

    /**
     * gets a string containing the info of a given block of code
     * @param block
     * CodeBlock info to be printed
     * @return
     * returns string that shows the CodeBlock name, blockComplexity, and
     * highestSubComplexity
     */
    public static String printBlockInfo(CodeBlock block){
        return "\t" + String.format("%-20s%-29s%-25s",
                "BLOCK " + block.getName() + ":", "block complexity = " +
                        block.getBlockComplexity().toString(), "highest " +
                        "sub-complexity = " + block.getHighestSubComplexity().
                        toString() + "\n" );
    }

}