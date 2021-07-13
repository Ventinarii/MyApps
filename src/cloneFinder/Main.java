package cloneFinder;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main implements FileVisitor{
    public static void main(String[] args) throws Exception{
        class FilePack implements Comparable<FilePack>{
            public FilePack(String fileName){
                this.fullName=fileName;
                this.fileName=fileName.substring(fileName.lastIndexOf('\\')+1);
            }
            public final String fullName, fileName;
            @Override
            public int compareTo(FilePack o) {
                return fileName.compareTo(o.fileName);
            }
        }
        ArrayList<FilePack> allFiles=new ArrayList<>();
        //==============================================================================================================init
        ArrayList<Main> allGatherers = new ArrayList<>(
                Arrays.asList(
                        new Main("\\\\100.100.100.100\\Basia")      ,
                        new Main("\\\\100.100.100.100\\Dokumenty")  ,
                        new Main("\\\\100.100.100.100\\Filip")      ,
                        new Main("\\\\100.100.100.100\\Multimedia") ,
                        new Main("\\\\100.100.100.100\\Piotr")      ,
                        new Main("\\\\100.100.100.100\\Piotr PRACA"),
                        new Main("\\\\100.100.100.100\\Public")     ,
                        new Main("\\\\100.100.100.100\\Web")
                )
        );
        //==============================================================================================================input
        allGatherers.stream().parallel().forEach(m->{
                    m.gatherAllFiles();
                    int length=m.path.toString().length()+1;
                    allFiles.addAll(
                            m.fileList.stream().map(f->new FilePack(f)).collect(Collectors.toList())
                    );
                }
        );
        //==============================================================================================================gather all files in filePacks
        List<FilePack> files = allFiles.stream().sorted().collect(Collectors.toList());
        ArrayList<FilePack> duplicates=new ArrayList<>();
        FilePack lastPack=files.get(0);
        boolean first=true;
        int amountOfFiles=files.size();

        for(int x=1;x<amountOfFiles;x++)
            if(files.get(x).fileName.equals(lastPack.fileName)) {
                if(first) {
                    duplicates.add(lastPack);
                    first=false;
                }
                duplicates.add(files.get(x));
            }else {
                lastPack = files.get(x);
                first=true;
            }
        //==============================================================================================================get all copies
        duplicates.forEach(d->System.out.println(d.fullName));
        System.out.println("total :"+duplicates.size()+" duplicates found");
        int x=3+3;
    }
    //==================================================================================================================
    public Main(String input)throws Exception {
        path = Paths.get(input);//get paths
    }
    public void gatherAllFiles(){
        Main fileVisitor = this;
        try {
            Files.walkFileTree(path, fileVisitor);//fill list 1 with X
        }catch (Exception ex){ex.printStackTrace();}
    }
    private final Path path;
    public final List<String> fileList = new ArrayList<>();
    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
        fileList.add(file.toString());
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

