/*
 * BinaryNodeTest.java
 * Create Date: Jun 14, 2019
 * Initial-Author: Janos Aron Kiss
 */
package binarytree;

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
public class BinaryNodeTest {
    
    public final static Integer NUMBER = 10;
    public final static Integer SMALLER_NUMBER = 5;
    public final static Integer GREATER_NUMBER = 15;    
    
    public BinaryNodeTest() {
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
     * Test of getData and setData methods, of class BinaryNode.
     */
    @Test
    public void testGetAndSetData() {
        System.out.println("getData");
        BinaryTreeCollection<Integer> list = new BinaryTreeCollection<>();  
        
        BinaryNode instance = list.createNode(null, NUMBER);
        assertEquals(NUMBER, instance.getData());
        
        instance.setData(SMALLER_NUMBER);
        assertEquals(SMALLER_NUMBER, instance.getData());
    }

    /**
     * Test of getParent and setParent methods, of class BinaryNode.
     */
    @Test
    public void testGetAndSetParent() {
        System.out.println("getParent");
        BinaryTreeCollection<Integer> list = new BinaryTreeCollection<>();
        
        BinaryNode rootInstance = list.createNode(null, NUMBER);
        assertEquals(null, rootInstance.getParent());
        
        BinaryNode childInstance = list.createNode(rootInstance, NUMBER);
        assertEquals(rootInstance, childInstance.getParent());                
        
        childInstance.setParent(null);
        assertEquals(null, childInstance.getParent());                
    }

    /**
     * Test of getLeft and setLeft methods, of class BinaryNode.
     */
    @Test
    public void testGetAndSetLeft() {
        System.out.println("getLeft");
        BinaryTreeCollection<Integer> list = new BinaryTreeCollection<>();
        
        BinaryNode rootInstance = list.createNode(null, NUMBER);
        assertEquals(null, rootInstance.getLeft());
        
        BinaryNode childInstance = list.createNode(rootInstance, SMALLER_NUMBER);
        rootInstance.setLeft(childInstance);        
        assertEquals(childInstance, rootInstance.getLeft());
        
        rootInstance.setLeft(null);
        assertEquals(null, rootInstance.getLeft());        
    }

    /**
     * Test of getRight and setRight methods, of class BinaryNode.
     */
    @Test
    public void testGetAndSetRight() {
        System.out.println("getRight");
        BinaryTreeCollection<Integer> list = new BinaryTreeCollection<>();
        
        BinaryNode rootInstance = list.createNode(null, NUMBER);
        assertEquals(null, rootInstance.getRight());
        
        BinaryNode childInstance = list.createNode(rootInstance, GREATER_NUMBER);
        rootInstance.setRight(childInstance);
        assertEquals(childInstance, rootInstance.getRight());
        
        rootInstance.setRight(null);
        assertEquals(null, rootInstance.getRight());        
    }

    /**
     * Test of isLeftChild method, of class BinaryNode.
     */
    @Test
    public void testIsLeftChild() {
        System.out.println("isLeftChild");
        BinaryTreeCollection<Integer> list = new BinaryTreeCollection<>();
        
        BinaryNode rootInstance = list.createNode(null, NUMBER);
        assertEquals(false, rootInstance.isLeftChild());

        BinaryNode childInstance = list.createNode(rootInstance, SMALLER_NUMBER);
        rootInstance.setLeft(childInstance);
        assertEquals(true, childInstance.isLeftChild());

        childInstance = list.createNode(rootInstance, GREATER_NUMBER);
        rootInstance.setRight(childInstance);
        assertEquals(false, childInstance.isLeftChild());
    }

    /**
     * Test of isDataEqualsTo method, of class BinaryNode.
     */
    @Test
    public void testIsDataEqualsTo() {
        System.out.println("isDataEqualsTo");
        BinaryTreeCollection<Integer> list = new BinaryTreeCollection<>();
        
        BinaryNode instance = list.createNode(null, NUMBER);
        assertEquals(true, instance.isDataEqualsTo(NUMBER));
        assertEquals(false, instance.isDataEqualsTo(SMALLER_NUMBER));
        assertEquals(false, instance.isDataEqualsTo(GREATER_NUMBER));
    }

    /**
     * Test of isDataLessThan method, of class BinaryNode.
     */
    @Test
    public void testIsDataLessThan() {
        System.out.println("isDataLessThan");
        BinaryTreeCollection<Integer> list = new BinaryTreeCollection<>();
        
        BinaryNode instance = list.createNode(null, NUMBER);
        assertEquals(false, instance.isDataLessThan(NUMBER));
        assertEquals(false, instance.isDataLessThan(SMALLER_NUMBER));
        assertEquals(true, instance.isDataLessThan(GREATER_NUMBER));
    }

    /**
     * Test of isDataGreaterThan method, of class BinaryNode.
     */
    @Test
    public void testIsDataGreaterThan() {
        System.out.println("isDataGreaterThan");
        BinaryTreeCollection<Integer> list = new BinaryTreeCollection<>();
        
        BinaryNode instance = list.createNode(null, NUMBER);
        assertEquals(false, instance.isDataGreaterThan(NUMBER));
        assertEquals(true, instance.isDataGreaterThan(SMALLER_NUMBER));
        assertEquals(false, instance.isDataGreaterThan(GREATER_NUMBER));
    }

    /**
     * Test of findSmallestNode method, of class BinaryNode.
     */
    @Test
    public void testFindSmallestNode() {
        System.out.println("findSmallestNode");
        BinaryTreeCollection<Integer> list = BinaryTreeCollectionTest.createTestCollection();
        assertEquals(list.find(4), list.find(8).findSmallestNode());
        assertEquals(list.find(9), list.find(10).findSmallestNode());
        assertEquals(list.find(17), list.find(17).findSmallestNode());        
    }

    /**
     * Test of findLargerParent method, of class BinaryNode.
     */
    @Test
    public void testFindLargerParent() {
        System.out.println("findLargerParent");
        BinaryTreeCollection<Integer> list = BinaryTreeCollectionTest.createTestCollection();
        assertEquals(list.find(3), list.find(1).findLargerParent());        
        assertEquals(list.find(8), list.find(6).findLargerParent());        
        assertEquals(list.find(20), list.find(15).findLargerParent());        
        assertEquals(list.find(20), list.find(19).findLargerParent());        
    }

    /**
     * Test of clear method, of class BinaryNode.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        BinaryTreeCollection<Integer> list = BinaryTreeCollectionTest.createTestCollection();
        
        BinaryNode instance = list.getRoot();
        BinaryNode left = instance.getLeft();
        BinaryNode right = instance.getRight();
        assertNotEquals(null, left);
        assertNotEquals(null, right);
        
        instance.clear();

        assertEquals(null, instance.getLeft());
        assertEquals(null, instance.getRight());
        
        assertEquals(null, left.getParent());
        assertEquals(null, left.getLeft());
        assertEquals(null, left.getRight());

        assertEquals(null, right.getParent());
        assertEquals(null, right.getLeft());
        assertEquals(null, right.getRight());
    }

}
