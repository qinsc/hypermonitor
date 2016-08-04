package hyper.monitor.test.dao;

/**
 * Created by qinscx on 2016/8/2.
 */
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class AbstractTestCase {
    protected static ApplicationContext context;

    @BeforeClass
    public static void beforeClass() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml", "datasource.xml");
    }
}
