package com.company.util;

import java.io.File;
import java.io.IOException;

public final class ArchiverCommand {
    private final File source;
    private final boolean pack;

    public ArchiverCommand(File source, boolean pack) {
        this.source = source;
        this.pack = pack;
    }

    public void execute() throws IOException {
        if (pack) {
            ArchiverHelper.writeToArchive(source);
        } else {
            ArchiverHelper.writeToFile(source);
        }
    }
}
