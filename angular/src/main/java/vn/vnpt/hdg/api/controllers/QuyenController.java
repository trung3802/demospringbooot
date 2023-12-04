package vn.vnpt.hdg.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.vnpt.hdg.api.models.Quyen;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.TreeItem;
import vn.vnpt.hdg.api.services.QuyenService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/quyen")
public class QuyenController {
	
	@Autowired
	QuyenService sQuyen;
	
	@GetMapping("/getQuyens")
	public ResponseEntity<?> getQuyens(){
		return ResponseEntity.ok(new AppResponse(sQuyen.getQuyens()));
	}
	
	@GetMapping("/getTreeQuyen")
    public ResponseEntity<?> getTreeQuyens() {
        List<TreeItem> tree = sQuyen.getTreeQuyen();
        return ResponseEntity.ok(tree);
    }
	
	@PostMapping("/addQuyen")
	public ResponseEntity<?> addQuyen(@Valid @RequestBody Quyen q) {
		return ResponseEntity.ok(sQuyen.addQuyen(q));
	}
	
	@PostMapping("/editQuyen")
	public ResponseEntity<?> editQuyen(@Valid @RequestBody Quyen q){
	    return ResponseEntity.ok(sQuyen.editQuyen(q));
	}
	
	@DeleteMapping("/deleteQuyen/{id}")
	public ResponseEntity<?> deleteQuyen(@PathVariable Integer id) {
	    return ResponseEntity.ok(sQuyen.deleteQuyen(id));
	}
}
