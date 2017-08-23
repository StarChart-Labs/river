import org.gradle.api.tasks.TaskAction;
import com.moowork.gradle.grunt.GruntTask;
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * This class extends the moowork gradle Grunt tasks so that they can be run asynchonously.
 * We use this to run the front-end developer build (which has a blocking watch for changes)
 * simultaneously with the Java application. 
 */
public class GruntAsync extends GruntTask {

    @TaskAction
    void exec() {
        ExecutorService es = Executors.newSingleThreadExecutor()
        es.submit({
          super.exec()
        } as Callable)
    }
    
}