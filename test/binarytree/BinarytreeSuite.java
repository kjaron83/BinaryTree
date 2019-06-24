/*
 * BinarytreeSuite.java
 * Create Date: Jun 14, 2019
 * Initial-Author: Janos Aron Kiss
 */
package binarytree;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Janos Aron Kiss
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({binarytree.BinaryTreeCollectionTest.class, binarytree.BinaryNodeTest.class, binarytree.BinaryIteratorTest.class})
public class BinarytreeSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
