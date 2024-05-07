package tests;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestWatcherExtension implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        deleteTraces(context);
    }

    private void deleteTraces(ExtensionContext context){
        Path tracePath = Paths.get("traces" + context.getDisplayName().replace(" ", "") + ".zip");
        try{
            Files.deleteIfExists(tracePath);
        } catch (IOException e){
            System.out.println("Failed to delete the trace file with name " + tracePath.getFileName());
        }
    }
}
