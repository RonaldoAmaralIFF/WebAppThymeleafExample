package br.edu.iff.ccc.bsi.webdev.controller.view;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "home")
public class ViewMainController {
	
	@GetMapping("/pagina2")
	public ResponseEntity<String> page2() {
		String body = "<h1>OLá Mundo</1>";
		return ResponseEntity.ok().body(body);
	}
	
	
	@GetMapping("/pagina1/{id}")
	@ResponseBody
	public String page1(@PathVariable("id") int id) {
		return "Olá Mundo -> " + id;
		
	}
	
	@GetMapping("/")
	public String getHome() {
		return "home.html";
	}
	
	@GetMapping("/example")
	public String getExample() {
		return "exemploTh.html";
	}
	
}
