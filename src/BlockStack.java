/*
 * @author Jeremy Samuel
 * E-mail: jeremy.samuel@stonybrook.edu
 * Stony Brook ID: 113142817
 * CSE 214
 * Recitation Section 3
 * Recitation TA: Dylan Andres
 * HW #3
 */

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * BlockStack class
 * holds the stack of CodeBlocks
 * Used for keeping track of the different CodeBlocks within the structure of
 * the entire code
 */
public class BlockStack{

    private final Stack<CodeBlock> stack = new Stack<>();
    private int size = 0;

    /**
     * default constructor
     */
    public BlockStack(){

    }

    /**
     * adds new CodeBlock to the top of the stack
     * @param block
     * the CodeBlock you want to be pushed to the stack
     */
    public void push(CodeBlock block){
        stack.push(block);
        size++;
    }

    /**
     * removes the CodeBlock at the top of the stack
     * @return
     * returns the removed CodeBlock
     */
    public CodeBlock pop(){
        if(!isEmpty()) {
            size--;
            return stack.pop();
        }else{
            throw new EmptyStackException();
        }
    }

    /**
     * gets the CodeBlock at the top of the stack
     * @return
     * returns the CodeBlock at the top of the stack
     */
    public CodeBlock peek(){
        return stack.peek();
    }

    /**
     * gets the size of the sack by referencing variable that keeps track of it
     * @return
     * returns size of stack
     */
    public int size(){
        return size;
    }

    /**
     * checks if stack is empty or not
     * @return
     * returns true if stack is empty, false if otherwise
     */
    public boolean isEmpty(){
        return size == 0;
    }

}
