import com.moatcrew.restbdd.jbehave.SpringJbehaveStory;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by tensh on 31/07/2016.
 */

@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class DemoTestExecutor extends SpringJbehaveStory {
}
