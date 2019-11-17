import org.apache.commons.logging.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.event.ItemListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListenerTest implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Logger.getLogger("").log(Level.WARNING,"The name of the test case failed is "+iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Logger.getLogger("").log(Level.SEVERE,"The name of the test case skipped is "+iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
