/*
 * BinaryIteratorTest.java
 * Create Date: Jun 14, 2019
 * Initial-Author: Janos Aron Kiss
 */
package binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Janos Aron Kiss
 */
public class BinaryIteratorTest {
    
    public BinaryIteratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hasNext method, of class BinaryIterator.
     */
    @Test
    public void testHasNext() {
        System.out.println("hasNext");
        BinaryTreeCollection<Integer> list = BinaryTreeCollectionTest.createTestCollection();
        int size = list.size();

        Iterator<Integer> iterator = list.iterator();

        for ( int i = 0; i < size; i++ ) {
            iterator.next();
            assertEquals(i < size - 1, iterator.hasNext());
        }
    }

    /**
     * Test of next method, of class BinaryIterator.
     */
    @Test
    public void testNext() {
        System.out.println("next");
        BinaryTreeCollection<Integer> list = BinaryTreeCollectionTest.createTestCollection();
        int size = list.size();

        Iterator<Integer> iterator = list.iterator();
        ArrayList<Integer> sorted = new ArrayList<>(BinaryTreeCollectionTest.NUMBERS);
        Collections.sort(sorted);
        
        for ( int i = 0; i < size; i++ )
            assertEquals(sorted.get(i), iterator.next());
    }

    /**
     * Test of remove method, of class BinaryIterator.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        BinaryTreeCollection<Integer> list = BinaryTreeCollectionTest.createTestCollection();
        int size = list.size();
        
        Iterator<Integer> iterator = list.iterator();
        int number = BinaryTreeCollectionTest.NUMBERS.get(
                new Random().nextInt(BinaryTreeCollectionTest.NUMBERS.size())
        );

        int current;
        for ( int i = 0; i < size; i++ ) {
            current = iterator.next();
            if ( current == number ) {
                assertEquals(true, list.contains(number));
                iterator.remove();
                assertEquals(false, list.contains(number));
                assertEquals(size - 1, list.size());
                break;
            }
        }
    }
    
}
