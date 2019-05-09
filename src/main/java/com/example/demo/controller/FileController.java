package com.example.demo.controller;


import com.example.demo.dao.impl.FileDaoImpl;
import com.example.demo.dao.impl.PictureDaoImpl;
import com.example.demo.dao.impl.TeacherDaoImpl;
import com.example.demo.dao.impl.VideoDaoImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;
import java.util.List;

@RestController
public class FileController {

    private final ResourceLoader resourceLoader;
    @Autowired
    private FileDaoImpl fileDao;
    @Autowired
    private PictureDaoImpl pictureDao;
    @Autowired
    private VideoDaoImpl videoDao;
    @Autowired
    private TeacherDaoImpl teacherDao;

    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
//    /**
//     * 保存文件，直接以multipartFile形式
//     * @param multipartFile
//     * @param path 文件保存绝对路径
//     * @return 返回文件名
//     * @throws IOException
//     */
//    public static String saveImg(MultipartFile multipartFile,String path) throws IOException {
//        File file = new File(path);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
//        String fileName = Calendar.getInstance().getTimeInMillis() + ".png";
//        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
//        byte[] bs = new byte[1024];
//        int len;
//        while ((len = fileInputStream.read(bs)) != -1) {
//            bos.write(bs, 0, len);
//        }
//        bos.flush();
//        bos.close();
//        return fileName;
//    }

    @RequestMapping(value = "/file_upload", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") List <MultipartFile> files){

        if(files.isEmpty()){
            return "fail";
        }
        String path = "E:/coding-java/storage";
        for(MultipartFile file : files){
            String fileName = file.getOriginalFilename();
//            String uid = file.get
            int size = (int) file.getSize();
            System.out.println(fileName + "->" + size);
            fileName = size + "_" + fileName;
            System.out.println(fileName);

            if(file.isEmpty()){
                return "fail";
            }else{
                File dest = new File(path + "/" + fileName);
                if(!dest.getParentFile().exists()){
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e){
                    e.printStackTrace();
                    return "fail";
                }
            }
        }
        return "success";
    }

    @RequestMapping("/showPicture")
    public ResponseEntity showPicture(String pictureName){
        try {
            String uidName = pictureDao.getUidName(pictureName);
            String path = "E:/coding-java/storage/";
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + uidName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/showTeacherPicture")
    public ResponseEntity showTeacherPicture(String pictureName){
        try {
//            System.out.println(pictureName);
            String uidName = teacherDao.getUidName(pictureName);
//            System.out.println("teacherUidName:"+uidName);
            String path = "E:/coding-java/storage/";
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + uidName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/showFile")
    public ResponseEntity showFile(String fileName){
        try {
            String uidName = fileDao.getUidName(fileName);
            String path = "E:/coding-java/storage/";
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + uidName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/showVideo")
    public ResponseEntity showVideo(String videoName){
        try {
            String uidName = videoDao.getUidName(videoName);
            String path = "E:/coding-java/storage/";
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + uidName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/download")
    public String downLoad(String filename,HttpServletResponse response){
        System.out.println(filename);
        if(filename.indexOf(".jpg") != -1 || filename.indexOf(".png") != -1){
            filename = pictureDao.getUidName(filename);
        }else if(filename.indexOf("mp4") != -1) {
            filename = videoDao.getUidName(filename);
        } else{
            filename = fileDao.getUidName(filename);
        }
//        String filename="2.png";
        String filePath = "E:/coding-java/storage" ;
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
