
// Controller for Restaurant related pages
package com.Team3.MaitreD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Team3.MaitreD.models.Restaurant;
import com.Team3.MaitreD.models.RestaurantDTO;
import com.Team3.MaitreD.services.RestaurantService;
import com.Team3.MaitreD.services.StorageService;

import java.io.IOException;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin("*")
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;
	@Autowired
	StorageService storageService;  // Assuming a service to handle file storage

	@PostMapping("/upload-photos")
	public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		String imageUri = storageService.saveFile(file);  // Assume storageService handles file storage and returns URI
		redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
		return ResponseEntity.ok().body("File uploaded successfully: " + imageUri);
	}

	@GetMapping("/get-image/{username}")
	public String getImage(@PathVariable String username) {
		username = username.replace(""", "");
        Restaurant restaurant = restaurantService.getCurrentRestaurant(username);
        byte[] photo = restaurant.getPhoto();
        return Base64.encodeBase64String(photo);
    }
}
