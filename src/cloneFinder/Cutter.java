package cloneFinder;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Cutter {
    public static void main(String[] args)throws Exception{
        String path = "C:\\Users\\Admin\\Desktop\\exess.txt";
        Files.lines(Paths.get(path))
                .filter(s->{
                    String[] tmp = s.replace('\\','/').split("[/]");
                    String fileName = tmp[tmp.length-1].split(" => ")[0];
                    return !(
                            fileName.startsWith("._")||
                            fileName.startsWith(".DS_Store")
                    );
                })
                .forEach(l->System.out.println(l));
    }
}
