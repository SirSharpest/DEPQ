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
        return 0;
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
        private MyNode m_root;

        /**
         * Constructor for MyBST class
         */
        MyBST(){
            m_root = new MyNode();
        }

        void insert(Comparable data){

            m_root.insert(data);
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
             * constructor
             * use this if there is already
             * a root node
             */
            MyNode(){
                m_root = null;
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

            //TODO: This fun
            //As Tom pointed out
            void insert(Comparable data){

                //newNode = new MyNode(data);

                //find where it goes, then put there
                //root - go right node - insert
                //roots-right-child-node

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
            void setParent(MyNode parent){this.m_parent = parent;}
            void setRight(MyNode right){this.m_right = right;}
            void setLeft(MyNode left){this.m_left = left;}


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
