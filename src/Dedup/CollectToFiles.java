package Dedup;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectToFiles implements FileVisitor {
    public static void main(String[] args){
        try {
            List<String> list = new ArrayList<>();
            List<Path> toCollect = Arrays.asList(
                    Paths.get("\\\\192.168.1.100\\Basia"),
                    Paths.get("\\\\192.168.1.100\\Dokumenty"),
                    Paths.get("\\\\192.168.1.100\\Filip"),
                    Paths.get("\\\\192.168.1.100\\Multimedia"),
                    Paths.get("\\\\192.168.1.100\\Piotr")
            );
            Stream<Path> streams = toCollect.parallelStream();
            streams.forEach(p ->
            {
                try {
                    String[] tmp = p.toString().replace('\\','/').split("[/]");
                    String folderName= tmp[tmp.length-1];

                    List<String> paths = new CollectToFiles(p).paths;

                    Files.deleteIfExists(Paths.get("C:\\Users\\Admin\\Desktop\\Tools\\Programing\\MyApps\\src\\Dedup\\Data\\"+folderName+".txt"));
                    Files.createFile(Paths.get("C:\\Users\\Admin\\Desktop\\Tools\\Programing\\MyApps\\src\\Dedup\\Data\\"+folderName+".txt"));
                    Files.write(
                            Paths.get("C:\\Users\\Admin\\Desktop\\Tools\\Programing\\MyApps\\src\\Dedup\\Data\\"+folderName+".txt"),
                            paths
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception ex){ex.printStackTrace();}
    }

    public List<String> paths = new ArrayList<>();
    public CollectToFiles(Path path){
        CollectToFiles fileVisitor = this;
        try {
            Files.walkFileTree(path, fileVisitor);
        }catch (Exception ex){ex.printStackTrace();}
    }
    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
        if(dir.toString().endsWith("@Recently-Snapshot"))
            return FileVisitResult.SKIP_SUBTREE;
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
        paths.add(file.toString());
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
