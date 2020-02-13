import com.utilities.Environment;
import com.utilities.RedisData;
import org.testng.annotations.Test;

public class Demo {

    @Test
    public void DemoCall() {
        System.out.println(Environment.value.getDBHostname());
        System.out.println(Environment.value.getDBUsername());
        System.out.println(Environment.value.getDBPort());

        RedisData redis = new RedisData();

        System.out.println(redis.getString("wgdoc:21259"));
        System.out.println(redis.getHash("wg:21258").get("code"));
        System.out.println(redis.getHash("wg:21258", "code"));
        //String valu2e = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");


    }
}
