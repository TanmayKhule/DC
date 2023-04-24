import java.io.File;
public class DiskSpace {
    public static void main(String[] args) {
        File InpFile = new File("C:/");
        System.out.println("Size of disk:" + (InpFile.getFreeSpace() / (1024 * 1024 * 1024)) + "Gb");  
    }
    
}
