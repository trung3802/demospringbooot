package vn.vnpt.hdg.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.Quyen;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.TreeItem;
import vn.vnpt.hdg.api.repository.QuyenRepository;
import vn.vnpt.hdg.api.services.QuyenService;

@Service
public class QuyenServiceImpl implements QuyenService {
	
	@Autowired
	QuyenRepository rQuyen;

	@Override
	public List<Quyen> getQuyens() {
		List<Quyen> cds = rQuyen.findAll();
		return cds;
	}
	
	@Override
	public AppResponse addQuyen(Quyen q) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(q.getMa())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu mã không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(q.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(q.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    Quyen existingQuyenByMa = rQuyen.findByMa(q.getMa());
	        if (existingQuyenByMa != null) {
	            return new AppResponse("Mã đã có trong hệ thống", false);
	        }
	        Quyen existingQuyenByTen = rQuyen.findByTen(q.getTen());
	        if (existingQuyenByTen != null) {
	            return new AppResponse("Tên đã có trong hệ thống", false);
	        }
		    // Insert data
		    rQuyen.save(q);
		    
		    return new AppResponse("Thêm quyền thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
    public List<TreeItem> getTreeQuyen() {
        List<Quyen> Quyens = rQuyen.findAll();
        return buildTree(Quyens, 0);
    }
	
    private List<TreeItem> buildTree(List<Quyen> allQuyens, Integer parentId) {
        List<TreeItem> treeItems = new ArrayList<>();
        for (Quyen quyen : allQuyens) {
            if (quyen.getChaID() == parentId) {
                List<TreeItem> children = buildTree(allQuyens, quyen.getId());
                TreeItem treeItem = new TreeItem(quyen.getId(), quyen.getTen(), false, false, children);
                treeItems.add(treeItem);
            }
        }
        return treeItems;
    }
	
	@Override
	public AppResponse editQuyen(Quyen q) {
	    try {        
	        Optional<Quyen> existingQuyenOptional = rQuyen.findById(q.getId());
	        if (!existingQuyenOptional.isPresent()) {
	            return new AppResponse("Quyền không tồn tại trong hệ thống", false);
	        }        

	        Quyen existingQuyen = existingQuyenOptional.get();
	        
	        if (!ObjectUtils.isEmpty(q.getMa())) {
	            existingQuyen.setMa(q.getMa());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(q.getTen())) {
	            existingQuyen.setTen(q.getTen());
	        }
	        else {
	        	return new AppResponse("Tên không được để trống");
	        }
	        
	        if (q.getChaID() != null) {
	            existingQuyen.setChaID(q.getChaID());
	        }
	        if (q.getLoai() != null) {
	            existingQuyen.setLoai(q.getLoai());
	        }
	        if (q.getController() != null) {
	            existingQuyen.setController(q.getController());
	        }
	        if (q.getAction() != null) {
	            existingQuyen.setAction(q.getAction());
	        }
	        if (q.getFunctionGroup() != null) {
	            existingQuyen.setFunctionGroup(q.getFunctionGroup());
	        }
	        if (q.getThuTu() != null) {
	            existingQuyen.setThuTu(q.getThuTu());
	        }
	        if (q.getTrangThai() != null) {
	            existingQuyen.setTrangThai(q.getTrangThai());
	        }
	        
	        rQuyen.save(existingQuyen);
	        
	        return new AppResponse("Chỉnh sửa quyền thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteQuyen(Integer id) {
        try {
            Quyen existingQuyen = rQuyen.findById(id).orElse(null);
            if (existingQuyen == null) {
                return new AppResponse("Quyền không tồn tại trong hệ thống", false);
            }
            
            rQuyen.delete(existingQuyen);
            
            return new AppResponse("Xóa Quyền thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
