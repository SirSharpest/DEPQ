package cs21120.depq;

/**
 * Created by nathan on 03/11/15.
 * TODO: Follow Bernie's BB lectures on this
 */
public class nah26DEPQ implements DEPQ {

    MyBST BST = new MyBST();

    /**
     * Returns the smallest element in the DEPQ but does not remove it from the DEPQ
     *
     * @return returns the smallest element in the DEPQ
     */
    public Comparable inspectLeast() {
        return null;
    }

    /**
     * Returns the largest element in the DEPQ but does not remove it from the DEPQ
     *
     * @return returns the largest element in the DEPQ
     */
    public Comparable inspectMost() {
        return null;
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
        return null;
    }

    /**
     * Removes the largest element from the DEPQ and returns it
     *
     * @return returns the largest element in the DEPQ
     */
    public Comparable getMost() {
        return null;
    }

    /**
     * Checks if the DEPQ is empty
     *
     * @return returns true if the queue is empty
     */
    public boolean isEmpty() {
        return false;
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
        //within should be all other nodes
        private MyNode m_root = null;
        private int m_size = 0; 
        
        /**
         * Constructor for MyBST class
         */
        MyBST(){
            
        	
        }

        /**
         * First check if the root 
         * is empty, if not then insert it's children 
         * @param data
         */
        void insert(Comparable data){

        	
            if(this.m_root == null){
            	m_root = new MyNode(data);
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
             * @param parentNode
             */
            MyNode(MyNode parentNode, Comparable value){
                this.m_value = value;
                this.m_parent = parentNode;
            }

            /**
             * This takes a root node
             * and the new node and decides where to put it
             * @param rootNode
             * @param newNode
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
            			rootNode.insert(rootNode.getRight(), newNode);
            		}
            		
            	}
                
            }

            /**
             * TODO: This function def
             * @return
             */
            MyNode getParent(){
                return this.m_parent;
            }
            /**
             * TODO: This function def
             * @return
             */
            MyNode getLeft(){
                return this.m_left;
            }
            /**
             * TODO: This function def
             * @return
             */
            MyNode getRight(){
                return this.m_right;
            }

            /*
               TODO: Set JDocs here
             */
            void setParent(MyNode parent){
            	
            	this.m_parent = parent;
            	}
            
            void setRight(MyNode right){
            	
            	this.m_right = right;
            	right.setParent(this);
            	}
            
            void setLeft(MyNode left){
            	
            	this.m_left = left;
            	left.setParent(this);
            	}


            /**
             * Returns 0 if equal
             * positive if greater than
             * negative if less than
             * @param myNode
             * @return
             */
            public int compareTo(MyNode myNode) {
                return this.m_value.compareTo(myNode.m_value);
            }
        }

    }


}
