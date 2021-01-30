package gp_springboot_demo_fileUpload.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${user.fileUploadPath}")
    String fileUploadPath;
    /**
     * parameter name "upload" should be the same as:
     * <label>photo:</label><input type="file" name="upload"><br/>
     * @param upload
     * @param username
     * @return
     */
    @RequestMapping("/upload")
    public String upload(MultipartFile upload, String username) throws IOException {
        System.out.println("user name: " + username + " " + upload.getOriginalFilename());
        upload.transferTo(new File(fileUploadPath, upload.getOriginalFilename()));
        return "success";
    }

    @RequestMapping("/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response){
        File file = new File("C:\\Users\\JiaXie\\CrashPlan\\desktop\\upload\\ejbear.ear");

        //set response header and client file name
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition","attachment;filename=" + file.getName());
        InputStream stream = null;
        ServletOutputStream outputStream = null;
        try{
            //copy file and send file to response's outstream
            stream = new FileInputStream(file);
            outputStream = response.getOutputStream();

            //copy input stream to outputStream
            byte[] b = new byte[1024];
            int length = 0;
            while((length = stream.read(b)) > 0){
                outputStream.write(b, 0, length);
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try{
                outputStream.flush();
                outputStream.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
