package com.example.demo.controller;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.demo.message.ResponseMessage;
import com.example.demo.model.FileInfo;
import com.example.demo.service.FilesStorageService;

import ch.qos.logback.core.subst.Tokenizer;

@Controller
@CrossOrigin("http://localhost:8080")
public class FilesController {
  @Autowired
  FilesStorageService storageService;
  private Environment environment;
  @PostMapping("/upload")
  public String uploadFiles(@RequestParam("files") MultipartFile[] files, Model model) {
    String message = "";

    System.out.println("uploadFiles");
    try {
      List<String> fileNames = new ArrayList<>();

      Arrays.asList(files).stream().forEach(file -> {
        storageService.save(file);
        fileNames.add(file.getOriginalFilename());
      });
      String fn = fileNames.get(0);
      StringTokenizer token_temp = new StringTokenizer(fn, ".");
  		String token_name = token_temp.nextToken();
      model.addAttribute("res","http://172.16.16.136:8080/view_img?img_name=" + fn);
      message = "Uploaded the files successfully: " + fileNames;
      return "/upload";
      //return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Fail to upload files!";
      //return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      return "/upload";
    }
  }

  @GetMapping("/files")
  public ResponseEntity<List<FileInfo>> getListFiles() {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}