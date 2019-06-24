/*
 * BinaryIterator.java
 * Create Date: Jun 11, 2019
 * Initial-Author: Janos Aron Kiss
 */

package binarytree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This class implements the {@link Iterator} interface for using {@link BinaryTreeCollection}.
 * @version $Revision$ $LastChangedDate$
 * @author $Author$
 */
public class BinaryIterator<T extends Comparable> implements Iterator<T> {
    
    private final BinaryTreeCollection<T> list;
    private BinaryNode<T> current;
    private BinaryNode<T> next;
    private int changes;

    protected BinaryIterator(@Nonnull BinaryTreeCollection<T> list) {
        this.list = list;
        this.next = this.list.getRoot();
        if ( this.next != null )
            this.next = this.next.findSmallestNode();
        this.changes = this.list.getChanges();
    }        

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public T next() {
        if ( changes != list.getChanges() )
            throw new ConcurrentModificationException();            
        
        current = next;
        next = findNext(next);        
        return current.getData();
    }
    
    @Nullable
    private BinaryNode<T> findNext(@Nonnull BinaryNode<T> current) {        
        BinaryNode<T> right = current.getRight();
        if ( right != null )
            return right.findSmallestNode();
        
        return current.findLargerParent();
    }

    @Override
    public void remove() {
        list.removeNode(current);
        current = null;
        changes = list.getChanges();
    }
    
}
