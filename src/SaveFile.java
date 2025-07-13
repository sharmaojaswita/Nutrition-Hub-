import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveFile {
    static String saveFile(File photo) {
        String abspath = "src/myuploads";
        String photoName = photo.getName();
        String wholePath = abspath + "/" + photoName;
        try (FileInputStream fis = new FileInputStream(photo);
             FileOutputStream fos = new FileOutputStream(new File(wholePath))) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wholePath;
    }
}
