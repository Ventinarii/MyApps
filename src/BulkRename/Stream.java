package BulkRename;
import Shared.LaazyContainers;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stream implements FileVisitor{
    public static void main(String[] args) throws Exception{
        Files.walkFileTree(Paths.get("C:\\Users\\Admin\\Desktop\\New folder"),new Stream());
        pck.stream().forEach(pck->{
            File file = new File(pck.file.toString());
            //y2mate.com - 2_hours_epic_music_mix_the_power_of_epic_music_full_mix_vol_2_DeXoACwOT1o_320kbps
            int x=3;
        });


    }
    private class Pck{
        public Pck(Object file, BasicFileAttributes attrs){
            this.file=file;
            this.attrs=attrs;
        }
        public final Object file;
        public final BasicFileAttributes attrs;
    }
    public static List<Pck> pck = new ArrayList<>();
    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
        pck.add(new Pck(file,attrs));
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
