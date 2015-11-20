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
        //within should be all other nodes
        private MyNode m_root = null;
        private int m_size = 0; 

        /**
         * Constructor for MyBST class
         */
        MyBST(){
        }




        /**
         * Gets the root node to be used in
         * other functions
         * @return
         */
        MyNode getRoot(){
            return this.m_root;
        }

        /**
         * Changes the root to the node specified
         * @param newRoot
         */
        void setRoot(MyNode newRoot){
            this.m_root = newRoot;
            this.m_root.setParent(null);
            this.m_root.setIsRoot(true);
        }

        /**
         * First check if the root 
         * is empty, if not then insert it's children 
         * @param data
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
        
        //get the largest node 
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

        //get the smallest node
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
                       //return the node that has now been removed
                        return tmp;
                    }
                    //if this node is the root and has no children
                    else if(startNode.getLeft() == null){
                        //TODO:: Ensure that the scope of this is correct
                        //TODO:: There are more entries like this that need corrected
                        startNode = null;
                        return tmp;
                    }
                }
                //if the node is not the root and is right most
                //then we proceed to remove this node and replace
                //with the left child
                else if(startNode.isRoot() == false){
                    //check if there is a child on the left
                    if(startNode.getLeft() != null){
                        //if there are left children now
                        //replace it
                        startNode.getParent().setRight(startNode.getLeft());
                        return tmp;
                    }
                    //else if there is no right child
                    else if(startNode.getLeft() == null){
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
                        //return the node that has now been removed
                        return tmp;
                    }
                    //if this node is the root and has no children
                    else if(startNode.getRight() == null){
                        startNode = null;
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
                        return tmp;
                    }
                    //else if there is no right child
                    else if(startNode.getRight() == null){
                        startNode.getParent().setLeft(null);
                        return tmp;
                    }
                }
            }

            return null;
        }

        /**
         * Gets status of is empty or not
         * @return
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
            			rootNode.insert(rootNode.getLeft(), newNode);
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

                if(right != null) {
                    right.setParent(this);
                    }
                }
            
            void setLeft(MyNode left){


                this.m_left = left;

            	if(left!=null){
                    left.setParent(this);
                }


            	}


            /**
             * Gets the value of the node
             * @return
             */
            public Comparable getValue(){
            	return this.m_value;
            }

            public void setValue(Comparable value){
                this.m_value = value;
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

            public boolean isRoot() {
                return m_isRoot;
            }

            public void setIsRoot(boolean m_isRoot) {
                this.m_isRoot = m_isRoot;
            }
        }

    }


}
