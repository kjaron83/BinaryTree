/*
 * BinaryTreeCollectionTest.java
 * Create Date: Jun 14, 2019
 * Initial-Author: Janos Aron Kiss
 */
package binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
public class BinaryTreeCollectionTest {
    
    public final static Integer NUMBER = Integer.MAX_VALUE;
    public final static List<Integer> NUMBERS = Arrays.asList(3, 8, 1, 4, 6, 2, 10, 9, 20, 25, 15, 16, 18, 19, 17);
    
    public BinaryTreeCollectionTest() {
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
    
    public static BinaryTreeCollection<Integer> createTestCollection() {
        BinaryTreeCollection<Integer> instance = new BinaryTreeCollection<>();
        instance.setSelfBalancingFrequency(0);
        instance.addAll(NUMBERS);
        return instance;
    }

    /**
     * Test of getRoot method, of class BinaryTreeCollection.
     */
    @Test
    public void testGetRoot() {
        System.out.println("getRoot");        
        BinaryTreeCollection<Integer> instance = createTestCollection();
        assertEquals(Integer.valueOf(3), instance.getRoot().getData());
        instance.remove(3);
        assertEquals(Integer.valueOf(4), instance.getRoot().getData());        
    }

    /**
     * Test of getChanges method, of class BinaryTreeCollection.
     */
    @Test
    public void testGetChanges() {
        System.out.println("getChanges");
        BinaryTreeCollection<Integer> instance = createTestCollection();
        
        int changes = NUMBERS.size();
        assertEquals(changes, instance.getChanges());
        
        instance.remove(NUMBERS.get(NUMBERS.size() - 1));
        changes++;
        assertEquals(changes, instance.getChanges());        
        
        instance.remove(NUMBERS.get(0));
        changes += 2;
        assertEquals(changes, instance.getChanges());        
        
        instance.add(NUMBERS.get(1));
        assertEquals(changes, instance.getChanges());        
    }

    /**
     * Test of getSelfBalancingFrequency and setSelfBalancingFrequency methods, of class BinaryTreeCollection.
     */
    @Test
    public void testGetAndSetSelfBalancingFrequency() {
        System.out.println("getSelfBalancingFrequency");
        BinaryTreeCollection<Integer> instance = createTestCollection();

        int number1 = 10;
        int number2 = 100;
        instance.setSelfBalancingFrequency(number1);
        assertEquals(number1, instance.getSelfBalancingFrequency());
        
        instance.setSelfBalancingFrequency(number2);
        assertEquals(number2, instance.getSelfBalancingFrequency());
    }    
    
    /**
     * Test of selfBalancing method, of class BinaryTreeCollection.
     */
    @Test
    public void testSelfBalancing() {
        System.out.println("selfBalancing");
        BinaryTreeCollection<Integer> instance = createTestCollection();
        Object[] items = instance.toArray();
        instance.selfBalancing();

        assertEquals(NUMBERS.size(), instance.size());
        assertArrayEquals(items, instance.toArray());
        
        BinaryNode root = instance.getRoot();
        BinaryNode left = root.getLeft();
        BinaryNode right = root.getRight();
        
        int leftDept = left != null ? left.dept() : 0;
        int rightDept = right != null ? right.dept() : 0;
        int steps = Math.abs((leftDept - rightDept) / 2);
        assertEquals(0, steps);        
    }    
    
    /**
     * Test of size method, of class BinaryTreeCollection.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        BinaryTreeCollection<Integer> instance = createTestCollection();

        assertEquals(NUMBERS.size(), instance.size());
        
        instance.add(NUMBER);
        assertEquals(NUMBERS.size() + 1, instance.size());
        
        instance.add(NUMBER);
        assertEquals(NUMBERS.size() + 1, instance.size());        
    }

    /**
     * Test of isEmpty method, of class BinaryTreeCollection.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        BinaryTreeCollection<Integer> instance = new BinaryTreeCollection<>();        
        assertEquals(true, instance.isEmpty());
        
        instance.add(NUMBER);
        assertEquals(false, instance.isEmpty());        
        
        instance.remove(NUMBER);
        assertEquals(true, instance.isEmpty());        
    }

    /**
     * Test of contains method, of class BinaryTreeCollection.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        BinaryTreeCollection<Integer> instance = new BinaryTreeCollection<>();        
        assertEquals(false, instance.contains(NUMBER));
        
        instance.add(NUMBER);
        assertEquals(true, instance.contains(NUMBER));
        
        instance.remove(NUMBER);
        assertEquals(false, instance.contains(NUMBER));
    }

    /**
     * Test of iterator method, of class BinaryTreeCollection.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        BinaryTreeCollection<Integer> instance = createTestCollection();
        assertEquals(true, instance.iterator() instanceof BinaryIterator);
    }

    /**
     * Test of toArray method, of class BinaryTreeCollection.
     */
    @Test
    public void testToArray_0args() {
        System.out.println("toArray");
        BinaryTreeCollection<Integer> instance = createTestCollection();
        
        ArrayList<Integer> sorted = new ArrayList<>(NUMBERS);
        Collections.sort(sorted);
        Object[] numbers = sorted.toArray(); 
        Object[] items = instance.toArray();

        assertArrayEquals(numbers, items);
    }

    /**
     * Test of toArray method, of class BinaryTreeCollection.
     */
    @Test
    public void testToArray_GenericType() {
        System.out.println("toArray");
        BinaryTreeCollection<Integer> instance = createTestCollection();
        
        ArrayList<Integer> sorted = new ArrayList<>(NUMBERS);
        Collections.sort(sorted);
        Object[] numbers = sorted.toArray(); 
        Integer[] items = instance.toArray(new Integer[0]);

        assertArrayEquals(numbers, items);
    }

    /**
     * Test of add method, of class BinaryTreeCollection.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        BinaryTreeCollection<Integer> instance = new BinaryTreeCollection<>();                        
        assertEquals(true, instance.add(NUMBER));
        assertEquals(false, instance.add(NUMBER));
    }

    /**
     * Test of createNode method, of class BinaryTreeCollection.
     */
    @Test
    public void testCreateNode() {
        System.out.println("createNode");
        BinaryTreeCollection instance = new BinaryTreeCollection();
        assertEquals(true, instance.createNode(null, NUMBER) instanceof BinaryNode);
    }

    /**
     * Test of remove method, of class BinaryTreeCollection.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        BinaryTreeCollection<Integer> instance = createTestCollection();
        assertEquals(true, instance.remove(NUMBERS.get(0)));
        assertEquals(false, instance.remove(NUMBERS.get(0)));
    }

    /**
     * Test of removeNode method, of class BinaryTreeCollection.
     */
    @Test
    public void testRemoveNode() {
        System.out.println("removeNode");
        BinaryTreeCollection<Integer> instance = createTestCollection();
        BinaryNode<Integer> node = instance.getRoot();
        Integer data = node.getData();
        assertEquals(true, instance.removeNode(node));
        assertEquals(false, instance.contains(data));
        assertEquals(NUMBERS.size() - 1, instance.size());
    }

    /**
     * Test of containsAll method, of class BinaryTreeCollection.
     */
    @Test
    public void testContainsAll() {
        System.out.println("containsAll");
        BinaryTreeCollection<Integer> instance = createTestCollection();
        assertEquals(true, instance.containsAll(NUMBERS));
        instance.remove(NUMBERS.get(0));
        assertEquals(false, instance.containsAll(NUMBERS));
        assertEquals(true, instance.containsAll(NUMBERS.subList(1, 2)));
    }

    /**
     * Test of addAll method, of class BinaryTreeCollection.
     */
    @Test
    public void testAddAll() {
        System.out.println("addAll");
        BinaryTreeCollection<Integer> instance = new BinaryTreeCollection<>();        
        assertEquals(true, instance.addAll(NUMBERS));
        assertEquals(NUMBERS.size(), instance.size());
        assertEquals(false, instance.addAll(NUMBERS));
        assertEquals(NUMBERS.size(), instance.size());
    }

    /**
     * Test of removeAll method, of class BinaryTreeCollection.
     */
    @Test
    public void testRemoveAll() {
        System.out.println("removeAll");
        BinaryTreeCollection<Integer> instance = createTestCollection();
        assertEquals(true, instance.removeAll(NUMBERS));
        assertEquals(0, instance.size());
        assertEquals(false, instance.removeAll(NUMBERS));
        assertEquals(0, instance.size());
    }

    /**
     * Test of retainAll method, of class BinaryTreeCollection.
     */
    @Test
    public void testRetainAll() {
        System.out.println("retainAll");
        BinaryTreeCollection<Integer> instance = createTestCollection();
        
        int count = NUMBERS.size() / 2;
        
        ArrayList<Integer> sorted = new ArrayList<>(NUMBERS.subList(0, count));
        Collections.sort(sorted);
        Object[] numbers = sorted.toArray(); 
        
        assertEquals(true, instance.retainAll(sorted));
        assertEquals(count, instance.size());
        assertEquals(false, instance.retainAll(sorted));
        assertEquals(count, instance.size());
        
        Integer[] items = instance.toArray(new Integer[0]);
        
        assertArrayEquals(numbers, items);        
    }

    /**
     * Test of clear method, of class BinaryTreeCollection.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        BinaryTreeCollection<Integer> instance = createTestCollection();
        assertEquals(NUMBERS.size(), instance.size());
        instance.clear();
        assertEquals(0, instance.size());
        assertEquals(null, instance.getRoot());
    }

    /**
     * Test of find method, of class BinaryTreeCollection.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        BinaryTreeCollection<Integer> instance = createTestCollection();
        assertEquals(instance.getRoot(), instance.find(3));        
        assertEquals(instance.getRoot().getLeft(), instance.find(1));        
        assertEquals(instance.getRoot().getRight(), instance.find(8));        
        assertEquals(instance.getRoot().getRight().getLeft(), instance.find(4));        
        assertEquals(instance.getRoot().getRight().getRight(), instance.find(10));        
    }
    
}
