
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// TODO Get this to work. Doesn't test all tests right now, but fix in future. 
//		Could not get this to work in lab either, prof told us he didn't know how to 
//		fix it
@RunWith(Suite.class)
@SuiteClasses({ TestFox.class, 
        TestGameObject.class, 
        TestLevelSelector.class,
        TestMove.class,
        TestRabbit.class,
        TestUndoRedo.class})
public class AllTests {

}
