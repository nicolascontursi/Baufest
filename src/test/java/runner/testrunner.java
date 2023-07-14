package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = "src/test/java/features", //donde buscar los escenarios de prueba
        glue = {"seleniumgluecode"}, //donde buscar las implementaciones de los escenarios de prueba - metodos
        plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:resources/reportes/reporte.html"}

)

public class testrunner {


}
