/*
 * BinaryTreeCollection.java
 * Create Date: Jun 6, 2019
 * Initial-Author: Janos Aron Kiss
 */
package binarytree;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This class implements the {@link Collection} interface by using BinaryTree pattern.
 * @version $Revision$ $LastChangedDate$
 * @author $Author$
 */
public class BinaryTreeCollection<T extends Comparable> implements Collection<T> {
    
    private BinaryNode<T> root;
    private int size = 0;
    private int changes = 0;
    private int selfBalancingFrequency = 10;

    /**
     * Returns the root node or null if the collection is empty.
     */
    @Nullable
    protected BinaryNode<T> getRoot() {
        return root;
    }
    
    private void setRoot(@Nullable BinaryNode<T> node) {
        root = node;
        if ( root != null )
            root.setParent(null);
    }
    
    /**
     * Returns the number of changes since the creation of the instance.
     */
    public int getChanges() {
        return changes;
    }
    
    private void incrementChanges() {
        changes++;
        checkIfSelfBalancingIsNeeded();
    }

    /**
     * Returns the frequency when the self-balancing process will be executed.
     * @see #setSelfBalancingFrequency(int) 
     */
    public int getSelfBalancingFrequency() {
        return selfBalancingFrequency;
    }

    /**
     * Sets the frequency when the self-balancing process will be executed.
     * @param selfBalancingFrequency Give zero or less to disable self-balancing.
     */
    public void setSelfBalancingFrequency(int selfBalancingFrequency) {
        this.selfBalancingFrequency = selfBalancingFrequency;
    }
    
    private void checkIfSelfBalancingIsNeeded() {
        if ( selfBalancingFrequency > 0 && changes % selfBalancingFrequency == 0 && root != null )
            selfBalancing();
    }
    
    protected void selfBalancing() {
        BinaryNode<T> left = root.getLeft();
        BinaryNode<T> right = root.getRight();
        
        int leftDept = left != null ? left.dept() : 0;
        int rightDept = right != null ? right.dept() : 0;
        int steps = Math.abs((leftDept - rightDept) / 2);
        
        if ( steps > 0 ) {
            if ( leftDept < rightDept ) {
                for ( int i = 0; i < steps; i++ ) 
                    moveRootLeftwards();
            }
            else {
                for ( int i = 0; i < steps; i++ ) 
                    moveRootRightwards();
            }
        }
    }
    
    private void moveRootLeftwards() {
        BinaryNode<T> node = root;
        root = node.getRight();
        root.setParent(null);
        node.setRight(null);
        BinaryNode<T> smallest = root.findSmallestNode();
        smallest.setLeft(node);
        node.setParent(smallest);
    }
    
    private void moveRootRightwards() {
        BinaryNode<T> node = root;
        root = node.getLeft();
        root.setParent(null);
        node.setLeft(null);
        BinaryNode<T> greatest = root.findGreatestNode();
        greatest.setRight(node);
        node.setParent(greatest);
    }
    
    @Override
    public int size() {
        return size;
    }        

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(@Nullable Object o) {
        if ( o == null )
            return false;
        
        BinaryNode<T> currentNode = root;
        while( currentNode != null ) {
            if ( currentNode.isDataEqualsTo(o) )
                return true;
            else if ( currentNode.isDataGreaterThan(o) )
                currentNode = currentNode.getLeft();
            else
                currentNode = currentNode.getRight();
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new BinaryIterator<>(this);
    }

    @Override
    public Object[] toArray() {
        return toArray((T[]) new Comparable[size]);
    }

    @Override
    public <T> T[] toArray(@Nonnull T[] a) {        
        if ( a == null )
            throw new NullPointerException();

        if ( a.length < size )
            a = (T[]) Arrays.copyOf(a, size);
        
        int i = 0;
        for ( T data : (Collection<T>) this ) {
            a[i] = data;
            i++;
        }
            
        while ( i < size ) {
            a[i] = null;
            i++;
        }
        
        return a;        
    }

    @Override
    public boolean add(@Nonnull T element) {
        if ( element == null )
            throw new NullPointerException();
        
        if ( root == null ) {
            setRoot(createNode(null, element));
            return true;
        }
        
        BinaryNode currentNode = root;
        BinaryNode parentNode;
        
        while ( true ) {
            parentNode = currentNode;
            if ( currentNode.isDataEqualsTo(element) )
                return false;            
            else if ( currentNode.isDataGreaterThan(element) ) {
                currentNode = currentNode.getLeft();
                if ( currentNode == null ) {
                    parentNode.setLeft(createNode(parentNode, element));
                    return true;
                }
            }
            else {
                currentNode = currentNode.getRight();
                if ( currentNode == null ) {
                    parentNode.setRight(createNode(parentNode, element));
                    return true;
                }
            }
        }
    }
    
    /**
     * Creates a new {@link  BinaryNode}. 
     * @param parent The parent of the new node.
     * @param element The data of the new node.
     * @return The new node.
     */
    @Nonnull
    protected BinaryNode<T> createNode(@Nullable BinaryNode<T> parent, @Nonnull T element) {
        size++;
        incrementChanges();
        return new BinaryNode<>(this, parent, element);
    }
    
    @Override
    public boolean remove(@Nonnull Object o) {
        BinaryNode currentNode = find(o);
        if ( currentNode == null )
            return false;
        
        return removeNode(currentNode);
    }
    
    /**
     * Removes a node from the collection.
     * @return Whether the collection was changed.
     */
    protected boolean removeNode(@Nonnull BinaryNode currentNode) {
        return removeNode(currentNode, true);
    }

    private boolean removeNode(@Nonnull BinaryNode currentNode, boolean decrementSize) {
        if ( currentNode == null )
            throw new NullPointerException();
        
        if ( currentNode.getCollection() != this )
            throw new RuntimeException("The specified node is not related to this collection!");
         
        BinaryNode parentNode = currentNode.getParent();
        BinaryNode leftNode = currentNode.getLeft();
        BinaryNode rightNode = currentNode.getRight();
                
        //if i am here that means we have found the node
        //Case 1: if node to be deleted has no children
        if ( leftNode == null && rightNode == null ) {
            if ( parentNode == null )
                setRoot(null);
            else if ( currentNode.isLeftChild() ) 
                parentNode.setLeft(null);
            else
                parentNode.setRight(null);
        }        
        //Case 2 : if node to be deleted has only one child
        else if ( leftNode != null && rightNode == null ) {
            if ( parentNode == null )
                setRoot(leftNode);
            else if( currentNode.isLeftChild() )
                parentNode.setLeft(leftNode);
            else 
                parentNode.setRight(leftNode);

            leftNode.setParent(parentNode);
        }
        else if( leftNode == null && rightNode != null ) {
            if ( parentNode == null )
                setRoot(rightNode);
            else if ( currentNode.isLeftChild() )
                parentNode.setLeft(rightNode);
            else
                parentNode.setRight(rightNode);
            
            rightNode.setParent(parentNode);
        }
        else if ( leftNode != null && rightNode != null ) {
            //now we have found the minimum element in the right sub tree
            BinaryNode successor = rightNode.findSmallestNode();
            removeNode(successor, false);
            currentNode.setData(successor.getData());
        }		
        
        if ( decrementSize )
            size--;
        incrementChanges();
        
        return true;		        
    }

    @Override
    public boolean containsAll(@Nonnull Collection<?> c) {
        if ( c == null )
            throw new NullPointerException();

        for ( Object o : c ) {
            if ( !contains(o) )
                return false;
        }
        
        return true;
    }

    @Override
    public boolean addAll(@Nonnull Collection<? extends T> c) {
        if ( c == null )
            throw new NullPointerException();

        boolean changed = false;
        for ( T e : c )
            changed = add(e) || changed;

        return changed;
    }

    @Override
    public boolean removeAll(@Nonnull Collection<?> c) {
        if ( c == null )
            throw new NullPointerException();

        boolean changed = false;
        for ( Object o : c )
            changed = remove(o) || changed;

        return changed;
    }

    @Override
    public boolean retainAll(@Nonnull Collection<?> c) {
        if ( c == null )
            throw new NullPointerException();

        boolean changed = false;

        Iterator<T> iterator = iterator();
        T data;
        while ( iterator.hasNext() ) {
            data = iterator.next();
            if ( !c.contains(data) ) {
                iterator.remove();
                changed = true;
            }
        }
        
        return changed;
    }

    @Override
    public void clear() {
        if ( root != null ) {
            root.clear();
            setRoot(null);
            size = 0;
            incrementChanges();
        }
    }      
    
    /**
     * Returns a {@link BinaryNode} that contains the specified data or null if the collection does not contain the value.
     */
    @Nullable
    protected BinaryNode<T> find(@Nonnull Object data) {
        if ( root == null )
            return null;
        
        BinaryNode currentNode = root;

        while ( !currentNode.isDataEqualsTo(data) ){
            if ( currentNode.isDataGreaterThan(data) )
                currentNode = currentNode.getLeft();
            else
                currentNode = currentNode.getRight();
            
            if ( currentNode == null )
                return null;
        }      
        
        return currentNode;
    }    
            
}

