import com.utilities.Environment;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo  {

    @Test
    public void DemoCall() {
        System.out.println(Environment.value.getDBHostname());
        System.out.println(Environment.value.getDBUsername());
        System.out.println(Environment.value.getDBPort());


        //String valu2e = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");



    }
}
