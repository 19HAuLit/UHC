package fr.loris.gottagras.uhc.utils;

import java.io.File;
import java.nio.file.Files;

public class file {
    public void fileDelete(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (!Files.isSymbolicLink(f.toPath())) {
                    fileDelete(f);
                }
            }
        }
        boolean success = file.delete();
    }
}
