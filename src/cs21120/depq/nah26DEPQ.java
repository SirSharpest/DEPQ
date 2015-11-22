package cs21120.depq;

import java.awt.*;

/**
 * Created by Nathan on 03/11/15.
 *
 * This is my implementation of a BST which functions as a DEPQ
 * It is at current unbalanced and uses O(n) for most all of its functionality.
 *
 * Insertion: O(n)
 * Deletion: O(n)
 * Searching: O(n)
 *
 *I have begun to implement the functions required to properly balance this tree and hopefully
 * when I have time (holidays, when not doing assignments) I will be able to properly implement this for my own learning
 * currently this file is hosted at github.com/sirsharpest/DEPQ and any updates will be there.
 *
 * The implementation of this I am happy with because it fundamentally works and does all that it needs to do, but
 * as mentioned the time complexity being what it is, does not bode well as it will largely slow and become unsuitable
 * as the datasets entered into it grow as well.
 *
 * All of the JUnit tests have succeeded and on my machine entering in 1000 unique values and then performing actions
 * on them, takes <1second so for a small project this implementation may be enough. As I have mentioned though, not
 * having it self balancing prevents me from making this O(log n) which would be ideal.
 */
public class nah26DEPQ implements DEPQ {

    MyBST BST = new MyBST();

    /**
     * Returns the smallest element in the DEPQ but does not remove it from the DEPQ
     *
     * @return returns the smallest element in the DEPQ
     */
    public Comparable inspectLeast() {
        return BST.inspectSmallest().getValue();
    }

    /**
     * Returns the largest element in the DEPQ but does not remove it from the DEPQ
     *
     * @return returns the largest element in the DEPQ
     */
    public Comparable inspectMost() {
       return BST.inspectLargest().getValue();
    }

    /**
     * Adds an element to the DEPQ
     *
     * @param c the element to insert into the DEPQ
     */
    public void add(Comparable c) {
    	BST.insert(c);
    }

    /**
     * Removes the smallest element from the DEPQ and returns it
     *
     * @return returns the smallest element in the DEPQ
     */
    public Comparable getLeast() {
        return BST.getLeast(BST.getRoot()).getValue();
    }

    /**
     * Removes the largest element from the DEPQ and returns it
     *
     * @return returns the largest element in the DEPQ
     */
    public Comparable getMost() {
        return BST.getMost(BST.getRoot()).getValue();
    }

    /**
     * Checks if the DEPQ is empty
     *
     * @return returns true if the queue is empty
     */
    public boolean isEmpty() {
        return BST.isEmpty();
    }

    /**
     * Returns the size of the DEPQ
     *
     * @return returns the number of elements currently in the DEPQ
     */
    public int size() {
        return BST.getSize();
    }


    /**
     * This is a class that I have made
     * which will control all of the elements
     * related to storing data within a
     * binary search tree which I have constructed!
     */
    private class MyBST{

        //Root node
        //within it should be all other nodes
        private MyNode m_root = null;
        private int m_size = 0; 

        /**
         * Constructor for MyBST class
         */
        MyBST(){
        }


        /**
         * Gets the root node to be used in
         * other functions.
         * This is used for recursion, through the tree and is
         * required as a starting point
         * @return the current root node
         */
        MyNode getRoot(){
            return this.m_root;
        }

        /**
         * Changes the root to the node specified
         * @param newRoot new root node
         */
        void setRoot(MyNode newRoot){

            //this is done to ensure no errors
            //if removing all nodes from a tree
            if(newRoot != null) {
                this.m_root = newRoot;
                this.m_root.setParent(null);
                this.m_root.setIsRoot(true);
            }else{
                this.m_root = null;
            }
        }

        /**
         * First check if the root 
         * is empty, if not then insert it's children 
         * @param data data to be inserted into tree
         */
        void insert(Comparable<MyNode> data){

            if(this.m_root == null){
            	m_root = new MyNode(data);
                m_root.setIsRoot(true);
            }else{
            	
            	//create a temp node to be inserted
            	MyNode tempNode = new MyNode(data); 
            	
            	//let the node class decide where to place this new node
            	m_root.insert(m_root, tempNode);
            }
            
            //increment the size of the tree 
            //if nothing has went wrong. 
            this.m_size++; 
        }
        
        int getSize(){
        	return this.m_size;
        }

        /**
         * Previews the largest node
         * @return the largest node
         */
        MyNode inspectLargest(){
        	
        	if(this.m_root.getRight() != null){
        	
        		MyNode temp = m_root;
        		
        		while(temp.getRight() != null){
        			temp = temp.getRight();
        		}
        		//returning the largest node
        		return temp; 
        		
        	}else{
        		return m_root;
        	}
        }

        /**
         * Preview the smallest node
         * @return the smallest node
         */
        MyNode inspectSmallest(){
        	
        	if(this.m_root.getLeft() != null){
            	
        		MyNode temp = m_root;
        		
        		while(temp.getLeft() != null){
        			temp = temp.getLeft();
        		}
        		//returning the largest node
        		return temp; 
        		
        	}else{
        		return m_root;
        	}
        }
        
        /**
         * Will find the largest node return it
         * and delete it
         * @return the largest node
         */
        MyNode getMost(MyNode startNode) {


            //Temp storage for the node as it will be unable to return
            //when it has been destroyed
            MyNode tmp = startNode;

            //check if this is the current largest node
            if(startNode.getRight() != null){
                //if it isn't the current right most node
                //drop another level and recurse
                return getMost(startNode.getRight());
            }
            else if(startNode.getRight() == null){

                //Check if the current node is the root
                if(startNode.isRoot()){
                    //if this node is the root then
                    //it will need to be replaced by the next left node
                    if(startNode.getLeft() != null){
                        //setting a new root node
                        setRoot(startNode.getLeft());
                        //size will need to be reduced by 1
                        this.m_size--;
                       //return the node that has now been removed
                        return tmp;
                    }
                    //if this node is the root and has no children
                    else if(startNode.getLeft() == null){
                        //size will need to be reduced by 1
                        this.m_size--;
                        setRoot(null);
                        //startNode = null;
                        return tmp;
                    }
                }
                //if the node is not the root and is right most
                //then we proceed to remove this node and replace
                //with the left child
                else if(startNode.isRoot() == false){
                    //check if there is a child on the left
                    if(startNode.getLeft() != null){
                        //size will need to be reduced by 1
                        this.m_size--;
                        //if there are left children now
                        //replace it
                        startNode.getParent().setRight(startNode.getLeft());
                        return tmp;
                    }
                    //else if there is no right child
                    else if(startNode.getLeft() == null){
                        //size will need to be reduced by 1
                        this.m_size--;
                        startNode.getParent().setRight(null);
                        return tmp;
                    }
                }
            }

            return null;
        }


        /**
         * Will find the largest node return it
         * and delete it
         * @return the smallest node
         */
        MyNode getLeast(MyNode startNode){

            //Temp storage for the node as it will be unable to return
            //when it has been destroyed
            MyNode tmp = startNode;


            //check if this is the current smallest node
            if(startNode.getLeft() != null){
                //if it isn't the current left most node
                //drop another level and recurse
                return getLeast(startNode.getLeft());
            }
            else if(startNode.getLeft() == null){

                //Check if the current node is the root
                if(startNode.isRoot()){
                    //if this node is the root then
                    //it will need to be replaced by the next right node
                    if(startNode.getRight() != null){
                        //setting a new root node
                        setRoot(startNode.getRight());
                        //size will need to be reduced by 1
                        this.m_size--;
                        //return the node that has now been removed
                        return tmp;
                    }
                    //if this node is the root and has no children
                    else if(startNode.getRight() == null){
                        //size will need to be reduced by 1
                        this.m_size--;

                        setRoot(null);
                        return tmp;
                    }
                }
                //if the node is not the root and is left most
                //then we proceed to remove this node and replace
                //with the left child
                else if(startNode.isRoot() == false){
                    //check if there is a child on the right
                    if(startNode.getRight() != null){
                        //if there are right children now
                        //replace it
                        startNode.getParent().setLeft(startNode.getRight());
                        //size will need to be reduced by 1
                        this.m_size--;
                        return tmp;
                    }
                    //else if there is no right child
                    else if(startNode.getRight() == null){
                        startNode.getParent().setLeft(null);
                        //size will need to be reduced by 1
                        this.m_size--;
                        return tmp;
                    }
                }
            }

            return null;
        }

        /**
         * Gets status of is empty or not
         * @return if tree is empty
         */
        boolean isEmpty(){
            if (m_size == 0)
                return true;
            else
                return false;
        }

        /**
         * Small class which contains information about the nodes
         * these nodes will be used in the binary search tree
         * which is also the class which this is encapsulated within
         */
        private class MyNode implements Comparable<MyNode>{

            /*
                Member variables of MyNode
                TODO: these should be set by the constructor.
             */
            private MyNode m_parent = null;
            private MyNode m_left = null;
            private MyNode m_right = null;
            private boolean m_isRoot = false;
            private int m_leftDepth = 0;
            private int m_rightDepth = 0;
            
            
            private Comparable m_value;

            /**
             * Constructor for first 
             * node
             */
            MyNode(Comparable data){
               this.m_value = data; 
            }


            /**
             * constructor
             * use this if there is already
             * a root node
             * @param parentNode node to be added under
             */
            MyNode(MyNode parentNode, Comparable value){
                this.m_value = value;
                this.m_parent = parentNode;
            }




            /**
             * This takes a root node
             * and the new node and decides where to put it
             * @param rootNode takes a node so that it can recurse down the tree
             * @param newNode node to be added
             */
            void insert(MyNode rootNode, MyNode newNode){

            	int status = newNode.compareTo(rootNode);
            	
            	//check if it is to go right in the tree
            	//or to the left
            	if(status >= 0){
            		
            		//check if right child is empty
            		if(rootNode.getRight() == null){
            			rootNode.setRight(newNode);
            		}else{
            			//if the node is already taken then
            			// evaluate it and so on
            			rootNode.insert(rootNode.getRight(), newNode);
            		}
            		
            	}else if(status < 0){
            		
            		//check if left child is empty
            		if(rootNode.getLeft() == null){
            			rootNode.setLeft(newNode);
            		}else{
            			rootNode.insert(rootNode.getLeft(), newNode);
            		}
            		
            	}
                
            }

            /**
             * Gets parent of current node
             * @return m_parent
             */
            MyNode getParent(){
                return this.m_parent;
            }
            /**
             * Gets the left child of current node
             * @return m_left
             */
            MyNode getLeft(){
                return this.m_left;
            }
            /**
             * Gets the right child of current node
             * @return m_right
             */
            MyNode getRight(){
                return this.m_right;
            }

            /**
             * Sets the parent node given to be the new one of this node
             * @param parent new parent node
             */
            void setParent(MyNode parent){
            	
            	this.m_parent = parent;
            	}

            /**
             * Sets the right child node
             * @param right new right child
             */
            void setRight(MyNode right){
            	
            	this.m_right = right;

                if(right != null) {
                    right.setParent(this);
                    }
                }

            /**
             * Sets the left child node
             * @param left new left child
             */
            void setLeft(MyNode left){
                this.m_left = left;
            	if(left!=null){
                    left.setParent(this);
                }
            }

            /**
             * This will get the height a BT on its
             * left and right
             * @return two values, the left and right depth
             */
            public Point getTreeHeight(){
                m_leftDepth = 0;
                m_rightDepth = 0;

                getLeftHeight(this);
                getRightHeight(this);

                Point result = new Point(m_leftDepth,m_rightDepth);
                return result;
            }

            /**
             * Gets the height of a BT if taking
             * its left most path
             * @param node node to check height of
             * @return the depth of the left side
             */
            private int getLeftHeight(MyNode node){

                this.m_leftDepth++;

                if(node.getLeft()!= null){

                    getLeftHeight(node.getLeft());
                }


                return m_leftDepth;
            }

            /**
             * Gets the height of a BT if taking
             * its right most path
             * @param node node to check height of
             * @return the depth of the right side
             */
            private int getRightHeight(MyNode node){

                this.m_rightDepth++;

                if(node.getRight() != null){

                    getRightHeight(node.getLeft());
                }

                return m_rightDepth;
            }

            /**
             * Currently unused and needs modified to work correctly
             * as does not take into account sub trees braching
             * inwards/outwards
             * @return if the tree is +/- 1 balanced
             */
            private boolean isBalanced(){

                Point toCheck = getTreeHeight();

                if(toCheck.x == toCheck.y ||
                        toCheck.x == toCheck.y +1 ||
                        toCheck.x == toCheck.y -1){

                    return true;

                }
                return false;

            }

            /**
             * Gets the value of the node
             * @return curren value
             */
            public Comparable getValue(){
            	return this.m_value;
            }

            /**
             * Sets a new value to the node
             * @param value new value
             */
            public void setValue(Comparable value){
                this.m_value = value;
            }
            
            /**
             * Returns 0 if equal
             * positive if greater than
             * negative if less than
             * @param myNode node to check against
             * @return evaluation of node
             */
            public int compareTo(MyNode myNode) {
                return this.m_value.compareTo(myNode.m_value);
            }

            /**
             * Checks if the node is the current root
             * @return if is root
             */
            public boolean isRoot() {
                return m_isRoot;
            }

            /**
             * Sets the node to be a the root
             * @param m_isRoot new status
             */
            public void setIsRoot(boolean m_isRoot) {
                this.m_isRoot = m_isRoot;
            }
        }

    }


}
