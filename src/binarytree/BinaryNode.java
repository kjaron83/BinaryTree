/*
 * BinaryNode.java
 * Create Date: Jun 11, 2019
 * Initial-Author: Janos Aron Kiss
 */

package binarytree;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This class represents a node in the binary tree.
 * @version $Revision$ $LastChangedDate$
 * @author $Author$
 */
public class BinaryNode<T extends Comparable> {

    private final BinaryTreeCollection collection;
    private BinaryNode parent;
    private BinaryNode left;
    private BinaryNode right;	
    private T data;

    protected BinaryNode(@Nonnull BinaryTreeCollection collection, @Nullable BinaryNode parent, @Nonnull T data) {
        this.collection = collection;
        this.parent = parent;
        this.data = data;
    }

    @Nonnull
    public BinaryTreeCollection getCollection() {
        return collection;
    }

    @Nullable
    public BinaryNode getParent() {
        return parent;
    }

    protected void setParent(@Nullable BinaryNode parent) {
        this.parent = parent;
    }

    @Nonnull
    public T getData() {
        return data;
    }

    protected void setData(@Nonnull T data) {
        this.data = data;
    }

    @Nullable
    public BinaryNode getLeft() {
        return left;
    }

    protected void setLeft(@Nullable BinaryNode left) {
        this.left = left;
    }

    @Nullable
    public BinaryNode getRight() {
        return right;
    }

    protected void setRight(@Nullable BinaryNode right) {
        this.right = right;
    }
    
    /**
     * Returns whether this node is the left child of its parent.
     */
    public boolean isLeftChild() {
        return parent != null && parent.left == this;
    }
    
    /**
     * Returns whether the data of this node equal to the specified object.
     */
    public boolean isDataEqualsTo(@Nonnull Object o) {
        return data.equals(o);
    }

    /**
     * Returns whether the data of this node less than the specified object.
     */
    public boolean isDataLessThan(@Nonnull Object o) {
        return data.compareTo(o) < 0;
    }

    /**
     * Returns whether the data of this node greater than the specified object.
     */
    public boolean isDataGreaterThan(@Nonnull Object o) {
        return data.compareTo(o) > 0;
    }
    
    /**
     * Returns the node that contains the smallest data in this subtree.
     */
    @Nonnull
    protected BinaryNode<T> findSmallestNode() {
        BinaryNode<T> current = this;        
        while ( current.left != null )
            current = current.left;
        return current;
    }    

    /**
     * Returns the node that contains the greatest data in this subtree.
     */
    @Nonnull
    protected BinaryNode<T> findGreatestNode() {
        BinaryNode<T> current = this;        
        while ( current.right != null )
            current = current.right;
        return current;
    }    

    /**
     * Returns the first node of the parent chain that contains greater data than this node.
     */
    @Nullable
    protected BinaryNode<T> findGreaterParent() {
        BinaryNode<T> current = parent;
        while ( current != null &&  current.getData().compareTo(data) < 0 ) {
            current = current.parent;
        }
        return current;
    }        
    
    /**
     * Eliminates the connections between this node and its parent, and children. Calls clear() on children nodes as well.
     */
    protected void clear() {
        if ( left != null )
            left.clear();
        if ( right != null )
            right.clear();

        left = null;
        right = null;
        parent = null;
    }   
    
    /**
     * Returns how deep is the subtree of this node. Returns 1 if this node has no children.
     */
    public int dept() {
        int leftDept = this.left != null ? this.left.dept() : 0;
        int rightDept = this.right != null ? this.right.dept() : 0;
        
        return ( leftDept > rightDept ? leftDept : rightDept ) + 1;
    }
    
}
