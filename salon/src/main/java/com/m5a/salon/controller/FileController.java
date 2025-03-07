/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m5a.salon.controller;

import com.m5a.salon.genericService.FileService;
import com.m5a.salon.model.entity.FileMessage;
import com.m5a.salon.model.entity.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Bryan
 */
@Controller
@CrossOrigin("*")
@RequestMapping("/file")
public class FileController {
//Inyectamos el servicio

    @Autowired
    FileService fileService;

//    @PostMapping("/upload")
//    public ResponseEntity<FileMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) {
//        String message = "";
//        try {
//            List<String> fileNames = new ArrayList<>();
//
//            Arrays.asList(files).stream().forEach(file -> {
//                fileService.save(file);
//                fileNames.add(file.getOriginalFilename());
//            });
//
//            message = "Se subieron los archivos correctamente " + fileNames;
//            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
//        } catch (Exception e) {
//            message = "Fallo al subir los archivos";
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
//        }
//    }
    @PostMapping("/upload")
    public ResponseEntity<List<FileModel>> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        String message = "";
        List<String> fileNames = new ArrayList<>();

        List<FileModel> archivos = new ArrayList<>();

        Arrays.asList(files).stream().forEach(file -> {
            fileService.save(file);
            fileNames.add(file.getOriginalFilename());

            FileModel fileModel = new FileModel();
            fileModel.setName(file.getOriginalFilename());
            archivos.add(fileModel);
        });

        message = "Se subieron los archivos correctamente " + fileNames;

        return new ResponseEntity<>(archivos, HttpStatus.OK);
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileModel>> getFiles() {
        List<FileModel> fileInfos = fileService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile",
                    path.getFileName().toString()).build().toString();
            return new FileModel(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<FileModel> getFilesById(@PathVariable String filename) {
        FileModel fileModel = new FileModel();
        List<FileModel> fileInfos = fileService.loadAll().map(path -> {
            String fileName = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile",
                    path.getFileName().toString()).build().toString();
            if (fileName.equals(filename)) {
                fileModel.setName(fileName);
                fileModel.setUrl(url);
            }
            return fileModel;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(fileModel, HttpStatus.OK);
    }

    @GetMapping("/filesImg/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/delete/{filename:.+}")
    public ResponseEntity<FileMessage> deleteFile(@PathVariable String filename) {
        String message = "";
        try {
            message = fileService.deleteFile(filename);
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }
}
