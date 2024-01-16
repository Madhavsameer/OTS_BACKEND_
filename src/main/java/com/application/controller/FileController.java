package com.application.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.application.model.Data;
import com.application.repository.Info;
@CrossOrigin(maxAge=3600)
@RestController
public class FileController {
	@Autowired
	Info in;
	

@PostMapping("/file")
public void damn(String c_id,String about,MultipartFile file,MultipartFile video) throws IOException {
	
	Data d=new Data();
	
	
	d.setFile(file.getBytes());
	d.setVideo(video.getBytes());
	d.setAbout(about);
	d.setC_id(c_id);
	
	in.save(d);
}
}