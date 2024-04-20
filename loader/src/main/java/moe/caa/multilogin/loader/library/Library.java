package moe.caa.multilogin.loader.library;

import java.io.File;
import java.util.List;

public record Library(
        String group,
        String name,
        String version
) {
    public static Library of(String str) {
        String[] strings = str.split(":");
        return new Library(strings[0], strings[1], strings[2]);
    }

    public File getFile(File folder) {
        return new File(folder, getUrl());
    }

    public static final List<Library> NECESSARY_LIBRARIES = List.of(
            Library.of("org.jetbrains.kotlin:kotlin-stdlib:1.9.23"),
            Library.of("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.6.3"),
            Library.of("org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:1.6.3"),

            Library.of("org.incendo:cloud-core:2.0.0-beta.4"),
            Library.of("org.incendo:cloud-services:2.0.0-beta.4"),

            Library.of("com.zaxxer:HikariCP:5.1.0")
    );

    public String getUrl() {
        return group.replace(".", "/")
                + "/" + name + "/" + version + "/"
                + getFileName();
    }


    public String getFileName() {
        return name + "-" + version + ".jar";
    }

    public String getDisplayName() {
        return group + ":" + name + ":" + version;
    }

    public static final List<Library> RELOCATE_TOOL_LIBRARIES = List.of(
            Library.of("org.ow2.asm:asm:9.2"),
            Library.of("org.ow2.asm:asm-commons:9.2"),
            Library.of("me.lucko:jar-relocator:1.7")
    );

    public File getFileRelocated(File folder) {
        String url = getUrl();
        url = url.substring(0, url.length() - 4) + "-relocated.jar";
        return new File(folder, url);
    }
}

