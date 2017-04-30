package dk.sdu.mmmi.cbse.asteroidsnetbeans1;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.io.IOException;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.Paths.get;
import static java.nio.file.Files.copy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbTestCase;
import org.openide.util.Lookup;

public class ApplicationTest extends NbTestCase {
    private static final String ADD_PLAYER_UPDATES_FILE = "C:\\Users\\Christian\\Documents\\GitHub\\Github-chrpe15-KOM\\TestLab\\application\\src\\test\\resources\\player\\updates.xml";
    private static final String REM_PLAYER_UPDATES_FILE = "C:\\Users\\Christian\\Documents\\GitHub\\Github-chrpe15-KOM\\TestLab\\application\\src\\test\\resources\\remplayer\\updates.xml";
    private static final String UPDATES_FILE = "C:\\Users\\Christian\\Documents\\Github\\Github-chrpe15-KOM\\UpdateCenter\\netbeans_site\\updates.xml";
    public static Test suite() {
        return NbModuleSuite.createConfiguration(ApplicationTest.class).
                gui(false).
                failOnMessage(Level.WARNING). // works at least in RELEASE71
                failOnException(Level.INFO).
                enableClasspathModules(false). 
                clusters(".*").
                suite(); // RELEASE71+, else use NbModuleSuite.create(NbModuleSuite.createConfiguration(...))
    }

    public ApplicationTest(String n) {
        super(n);
    }

    public void testApplication() throws InterruptedException, IOException{
        List<IEntityProcessingService> processors = new CopyOnWriteArrayList<>();
        List<IGamePluginService> plugins = new CopyOnWriteArrayList<>();
        waitForUpdate(processors, plugins);
        
        assertEquals("No plugins", 0, plugins.size());
        assertEquals("No processors", 0, processors.size());
        
        copy(get(ADD_PLAYER_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins);
        
        assertEquals("One plugin", 1, plugins.size());
        assertEquals("One processor", 1, processors.size());
        
        copy(get(REM_PLAYER_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins);
        
        assertEquals("No plugins", 0, plugins.size());
        assertEquals("No processors", 0, processors.size());
    }
    private void waitForUpdate(List<IEntityProcessingService> processors,List<IGamePluginService> plugins) throws InterruptedException{
        Thread.sleep(10000);
        processors.clear();
        processors.addAll(Lookup.getDefault().lookupAll(IEntityProcessingService.class));
        
        plugins.clear();
        plugins.addAll(Lookup.getDefault().lookupAll(IGamePluginService.class));
    }

}
