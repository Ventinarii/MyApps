package IntegrytyCHK;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main implements FileVisitor{
    public static void main(String[] args) throws Exception{
    new Main("C:\\Users\\Admin\\Desktop\\Games\\Media","\\\\100.100.100.100\\Filip\\Multimedia\\media").getResult().forEach(s->System.out.println(s));
        int x=2+3;
    }
    //==================================================================================================================
    public Main(String p1,String p2)throws Exception{
        P1=p1;P2=p2;
        Path
                originPath = Paths.get(p1),
                copyPath = Paths.get(p2);
        Main fileVisitor = this;
        Files.walkFileTree(originPath,fileVisitor);
        List<String> storageList = bufferList;
        bufferList = new ArrayList<>();
        Files.walkFileTree(copyPath,fileVisitor);

        int p1length=p1.length()+1,p2length=p2.length()+1;

        List<String> sortedOriginList = storageList.stream()
                .filter(s->{
                    String[] tmp = s.replace('\\','/').split("[/]");
                    String fileName = tmp[tmp.length-1].split(" => ")[0];
                    return !(fileName.startsWith("._")&&fileName.endsWith(".pdf"));
                })
                .map(s->s.substring(p1length)).sorted().collect(Collectors.toList());
        List<String> sortedCopyList = bufferList.stream().map(s->s.substring(p2length)).sorted().collect(Collectors.toList());
        bufferList=null;storageList=null;

        int locationInOrigin = 0,sizeOfOrigin = sortedOriginList.size(), locationInCopy = 0, sizeOfCopy = sortedCopyList.size(),comparizonResult;
        while(locationInOrigin<sizeOfOrigin&&locationInCopy<sizeOfCopy) {
            comparizonResult = sortedOriginList.get(locationInOrigin).compareTo(sortedCopyList.get(locationInCopy));
            if(comparizonResult==0){
                locationInOrigin++;
                locationInCopy++;
            }else if(comparizonResult<0){
                originOnlyList.add(sortedOriginList.get(locationInOrigin));
                locationInOrigin++;
            }else if(comparizonResult>0){
                copyOnlyFiles.add(sortedCopyList.get(locationInCopy));
                locationInCopy++;
            }else
                throw new Exception("dafuq: "+locationInOrigin+"/"+sizeOfOrigin+" "+locationInCopy+"/"+sizeOfCopy+"=>"+ sortedOriginList.get(locationInOrigin)+" <="+comparizonResult+"=> "+ sortedCopyList.get(locationInCopy));
        }
        while(locationInOrigin<sizeOfOrigin){ originOnlyList.add(sortedOriginList.get(locationInOrigin));locationInOrigin++; }
        while(locationInCopy<sizeOfCopy){     copyOnlyFiles .add(sortedCopyList  .get(locationInCopy));  locationInCopy++; }
    }
    public List<String> getResult(){
        List<String> list = new ArrayList<>();
        list.add("Rep: from: "+System.lineSeparator()+P1+" to: "+System.lineSeparator()+P2);
        list.add("exclusive in X@1");
        list.addAll(originOnlyList);
        list.add("exclusive in Y@2");
        list.addAll(copyOnlyFiles);
        list.add("========//======== =>Rep end");
        return list;
    }
    private String P1,P2;
    private List<String>
            bufferList = new ArrayList<>(),
            originOnlyList =new ArrayList<>(),
            copyOnlyFiles =new ArrayList<>();
    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
        bufferList.add(file.toString()+" => "+String.valueOf(attrs.size()));
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
