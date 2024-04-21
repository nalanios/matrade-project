// Controller for uploading images.  Still in development
package com.Team3.MaitreD.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Team3.MaitreD.services.UserService;
@RestController
@RequestMapping("/restaurant")
@CrossOrigin("*")
public class FileUploadController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/upload-photos")
	public void uploadPhoto(@RequestParam("file") MultipartFile file, 
			RedirectAttributes redirectAttributes) {
		System.out.println("haha");
		byte[] filecontent = null;
		//String image = "";
		try {
			InputStream inputStream = file.getInputStream();
			filecontent = inputStream.readAllBytes();
			//image =  Base64.encodeBase64String(filecontent);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 userService.addPicture("photo", filecontent);
		
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		
	}
}
