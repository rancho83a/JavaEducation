package p02_ExtendedDatabase;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {


    private Database database;
    private static final Person[]  EXPECTED   = {
             new Person(1,"a"),
   new Person(2,"b"),
   new Person(3,"c"),
    new Person(4,"d")
    };

    @Before
    public void setup() throws OperationNotSupportedException {
        Person p1 = new Person(1,"a");
        Person p2 = new Person(2,"b");
        Person p3 = new Person(3,"c");
        Person p4 = new Person(4,"d");
        database = new Database(p1,p2,p3,p4);
    }


    @Test
    public void testConstructorShouldCreateArrayWithValidState() throws OperationNotSupportedException {

        assertEquals(4, database.getElements().length);

        for (int i = 0; i < EXPECTED.length; i++) {
            assertEquals(EXPECTED[i], database.getElements()[i]);
        }
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testCreateDatabaseWithMoreThen16elementsShouldThrowException() throws OperationNotSupportedException {
        Person[] elements = new Person[17];
        new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testCreateDatabaseWithLessThan1elementsShouldThrowException() throws OperationNotSupportedException {
       Person[] elements = new Person[0];
        new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAdd_NULL_ElementShouldThrowException() throws OperationNotSupportedException {
        Person el = null;
        database.add(el);
    }

    @Test
    public void testAddValidElementShouldThrowException() throws OperationNotSupportedException {
        Person p = new Person(5,"e");
        database.add(p);
        Person[] el = database.getElements();
        assertEquals(5, el.length);
        assertEquals(p, el[4]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveElementFromEmptyDatabase() throws OperationNotSupportedException {
        for (int i = 0; i < 4; i++) {
            database.remove();
        }
        database.remove();
    }


    @Test
    public void testRemoveLastElementFromNonEmptyDatabase() throws OperationNotSupportedException {
        database.remove();
        Person[] actual = database.getElements();
        Person[]  expected   = {
                new Person(1,"a"),
                new Person(2,"b"),
                new Person(3,"c"),
        };

        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void testGetElementsShouldReturnCorrectArray(){
        Person[] actual = database.getElements();
        assertEquals(4, actual.length);
        for (int i = 0; i < EXPECTED.length; i++) {
            assertEquals(EXPECTED[i], actual[i]);
        }
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testAddIfThereAreMultipleUsersThrowException() throws OperationNotSupportedException {
        Person p = new Person(1, "a");
        database.add(p);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testAddUsersWithNegativeIdThrowException() throws OperationNotSupportedException {
        Person p = new Person(-1, "a");
        database.add(p);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameParameterIs_NULL_throwException() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameNoUserIsPresentByThisUsernameThrowException() throws OperationNotSupportedException {
        database.findByUsername("t");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameWhenMoreThenOnePersonHasSameUsernameThrowException() throws OperationNotSupportedException {
        Person p1 = new Person(1,"a");
        Person p2 = new Person(2,"b");
        Person p3 = new Person(3,"d");
        Person p4 = new Person(4,"d");
        Database database = new Database(p1,p2,p3,p4);
        database.findByUsername("d");
    }
    @Test
    public void testFindByUsernameShouldReturnCorrectUser() throws OperationNotSupportedException {
        Person actual = database.findByUsername("a");
        Person expected = new Person(1,"a");
        assertEquals(expected,actual);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdNoUserIsPresentByThisIdThrowException() throws OperationNotSupportedException {
        database.findById(5);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIDWhenMoreThenOnePersonHasSameID_ThrowException() throws OperationNotSupportedException {
        Person p1 = new Person(1,"a");
        Person p2 = new Person(2,"b");
        Person p3 = new Person(4,"c");
        Person p4 = new Person(4,"d");
        Database database = new Database(p1,p2,p3,p4);
        database.findById(4);
    }

    @Test
    public void testFindByIdShouldReturnUserWithCorrectID() throws OperationNotSupportedException {
        Person actual = database.findById(1);
        Person expected = new Person(1,"a");
        assertEquals(expected,actual);
    }



}