package Python;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateLabels  implements FileVisitor {
    public static void main(String[] args){
        try {
            List<Path> toCollect = Arrays.asList(
                    Paths.get("C:\\Users\\Admin\\Desktop\\MKR\\TensorFlow\\Teach")
            );
            Stream<Path> streams = toCollect.parallelStream();
            streams.forEach(p -> new GenerateLabels(p));

            Tags = Tags
                    .stream()
                    .map(e->e.replace("<name>","").replace("</name>","").trim())
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList());

            Tags.forEach(e->{
                System.out.println(
                        ("item {\n" +
                            "    id: "+i+"\n" +
                            "    name: ~"+e+"~\n" +
                            "    display_name: ~"+e+"~\n" +
                            "}").replace('~','\''));
                i++;
            });

        }catch (Exception ex){ex.printStackTrace();}
    }

    public GenerateLabels(Path path){
        GenerateLabels fileVisitor = this;
        try {
            Files.walkFileTree(path, fileVisitor);
        }catch (Exception ex){ex.printStackTrace();}
    }

    public static List<String> Tags = new ArrayList<>();
    public static int i = 1;

    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
        if(file.toString().endsWith(".xml"))
            Files
                    .lines(Paths.get(file.toString()))
                    .filter(e->e.endsWith("</name>"))
                    .forEach(e->Tags.add(e));
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
