import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ TestFox.class, 
        TestGameObject.class, 
        TestJumpInEvent.class, 
        TestLevelSelector.class,
        TestMove.class,
        TestRabbit.class})
public class AllTests {

}