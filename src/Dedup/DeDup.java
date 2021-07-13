package Dedup;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeDup {
public static void main(String[] args){
    class FilePack implements Comparable<FilePack>{
        public FilePack(String fileName){
            this.fileName=fileName.substring(fileName.lastIndexOf('\\')+1);
            this.fullName=fileName;
        }
        public final String fullName, fileName;
        @Override
        public int compareTo(FilePack o) {
            return fileName.compareTo(o.fileName);
        }

        @Override
        public String toString() {
            return fullName;
        }
    }


    try {
        Map<String,FilePack> pathsL1 = new HashMap();
        Map<String,FilePack> pathsL2 = new TreeMap();
        Arrays
                .asList(
                        Paths.get("C:\\Users\\Admin\\Desktop\\Tools\\Programing\\MyApps\\src\\Dedup\\Data\\Basia.txt"))
                .forEach(path->{
            try {
                List<FilePack> repeat = new ArrayList<>();
                Files   .lines(path)
                        .map(line->new FilePack(line))
                        .forEach(file->{
                            FilePack tmp = pathsL1.get(file.fileName);
                            if(tmp!=null){
                                repeat.add(file);
                                repeat.add(tmp);
                            }
                            pathsL1.put(file.fileName,file);
                        });

                List<FilePack> repeatStill = new ArrayList<>();
                repeat  .stream()
                        .forEach(file->{
                            FilePack tmp = pathsL2.get(file.fileName);
                            if(tmp!=null){
                                repeatStill.add(file);
                                repeatStill.add(tmp);
                            }
                            pathsL2.put(file.fileName,file);
                        });
                repeatStill.stream()
                        .sorted()
                        .forEach(repeating->System.out.println(repeating));
            } catch (Exception ex) {ex.printStackTrace();}
        });
    }catch (Exception ex){ex.printStackTrace();}
}
}
