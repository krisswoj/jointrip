//package pl.jointrip.controllers.logged.documentsApproval;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.file.Files;
//
//@Controller
//public class FileUploadControllerOld {
//
//    private static final String PIZZA_IMAGES = "pizzaImages";
//    private static final String TOMCAT_HOME_PROPERTY = "catalina.home";
//    private static final String TOMCAT_HOME_PATH = System.getProperty(TOMCAT_HOME_PROPERTY);
//    private static final String PIZZA_IMAGES_PATH = TOMCAT_HOME_PATH + File.separator + PIZZA_IMAGES;
//
//    private static final File PIZZA_IMAGES_DIR = new File(PIZZA_IMAGES_PATH);
//    private static final String PIZZA_IMAGES_DIR_ABSOLUTE_PATH = PIZZA_IMAGES_DIR.getAbsolutePath() + File.separator;
//
//    private static final String FAILED_UPLOAD_MESSAGE = "You failed to upload [%s] because the file because %s";
//    private static final String SUCCESS_UPLOAD_MESSAGE = "You successfully uploaded file = [%s]";
//
//
//    @GetMapping(value = "/uploadFile")
//    public ModelAndView uploadFileTest(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("message", "none");
//        modelAndView.setViewName("upload-form");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//    public ModelAndView uploadFileHandler(@RequestParam("name") String name,
//                                          @RequestParam("file") MultipartFile file) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("upload-form");
//
//        if (file.isEmpty()) {
//            modelAndView.addObject("message", String.format(FAILED_UPLOAD_MESSAGE, name, "file is empty"));
//        } else {
//            createPizzaImagesDirIfNeeded();
//            modelAndView.addObject("message", createImage(name, file));
//        }
//
//        return modelAndView;
//    }
//
//    private void createPizzaImagesDirIfNeeded() {
//        if (!PIZZA_IMAGES_DIR.exists()) {
//            PIZZA_IMAGES_DIR.mkdirs();
//        }
//    }
//
//    private String createImage(String name, MultipartFile file) {
//        try {
//            File image = new File(PIZZA_IMAGES_DIR_ABSOLUTE_PATH + name);
//            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image));
//            stream.write(file.getBytes());
//            stream.close();
//
//            return String.format(SUCCESS_UPLOAD_MESSAGE, name);
//        } catch (Exception e) {
//            return String.format(FAILED_UPLOAD_MESSAGE, name, e.getMessage());
//        }
//    }
//
//    @RequestMapping(value = "/image/{imageName}")
//    @ResponseBody
//    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
//        createPizzaImagesDirIfNeeded();
//
//        File serverFile = new File(PIZZA_IMAGES_DIR_ABSOLUTE_PATH + imageName + ".jpg");
//
//        return Files.readAllBytes(serverFile.toPath());
//    }
//
//}