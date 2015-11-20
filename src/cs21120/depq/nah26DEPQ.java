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
         * @return
         */
        MyNode getMost(MyNode startNode) {

            /**
             *TODO Get the right most node
             * TODO if there is no right node &&  current node is root the replace from the left
             * TODO also check that left is a thing too
             */

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

                        //startNode.replaceRoot(startNode.getLeft());
                        //TODO Not copying properly

                        startNode.setValue(startNode.getLeft().getValue());
                        startNode.setRight(startNode.getLeft().getRight());
                        startNode.setLeft(startNode.getLeft());
                        //set node to be root
                        startNode.setIsRoot(true);
                        startNode.setParent(null);
                        //return the old root node
                        return tmp;
                    }
                    //if this node is the root and has no children
                    else if(startNode.getLeft() == null){
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
         * @return
         */
        MyNode getLeast(MyNode startNode){

            if(startNode.getLeft() != null){

                return (this.getLeast(startNode.getLeft()));
            }

            //stores the value of the node to be returned
            //as replacing it with it's child will
            //cause return issues
            MyNode tmp = startNode;

            //if both nodes are null then we can kill the parents link
            //to its left child
            if(startNode.getRight() == null && startNode.getLeft() == null){
                startNode.getParent().setLeft(null);
            }
            //if there is a right child  let it
            //replace it's parent when it's removed
            else if(startNode.getRight() != null){
                startNode.getParent().setLeft(startNode.getRight());
            }

            return tmp;
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
             * This is a method that
             * will replace this node with another one
             */
            private void replaceRoot(MyNode replacement){

                //Set all the values correctly
                //that suit what a root looks like
                this.setIsRoot(true);
                this.setParent(null);
                this.setRight(replacement.getRight());
                this.setLeft(replacement.getLeft());
                this.setValue(replacement.getValue());

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
