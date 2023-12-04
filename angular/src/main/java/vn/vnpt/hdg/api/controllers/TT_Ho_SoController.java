//package vn.vnpt.hdg.trichdo.api.controllers;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import vn.vnpt.hdg.trichdo.api.constants.AppError;
//import vn.vnpt.hdg.trichdo.api.models.TaiLieuXuLy;
//import vn.vnpt.hdg.trichdo.api.payload.request.*;
//import vn.vnpt.hdg.trichdo.api.payload.response.AppResponse;
//import vn.vnpt.hdg.trichdo.api.payload.response.LichSuXuLyResponse;
//import vn.vnpt.hdg.trichdo.api.payload.response.SummaryHoSo;
//import vn.vnpt.hdg.trichdo.api.services.CongViecHoSoService;
//import vn.vnpt.hdg.trichdo.api.services.TTHoSoService;
//import vn.vnpt.hdg.trichdo.api.services.TaiLieuXlService;
//import vn.vnpt.hdg.trichdo.api.utils.ParseUtil;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//import java.util.List;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/hoso")
//public class TT_Ho_SoController extends BaseController {
//    @Autowired
//    TT_Ho_SoService sHoSo;
//
//    @Autowired
//    TaiLieuXlService sTaiLieuXl;
//
//    @Autowired
//    AppError appError;
//
//    @Autowired
//    CongViecHoSoService sCongViecHoSo;
//
//
//    @PostMapping("/getDsHoSoNV")
//    public ResponseEntity<?> getDsHoSoNV(@RequestParam("maLoaiCV") int maLoaiCV,
//                                         @RequestBody TTHoSoListRequest data,
//                                         HttpServletRequest request) {
//        return ResponseEntity.ok(new AppResponse(sHoSo.getDsHoSoNV(getToken(request), maLoaiCV, data)));
//    }
//
//    @GetMapping("/choPhepXuLyHoSo/{maLoaiCongViecHienTai}/{maHoSo}")
//    public ResponseEntity<?> choPhepXuLyHoSo(@PathVariable("maLoaiCongViecHienTai") Integer maLoaiCongViecHienTai,
//                                             @PathVariable("maHoSo") Integer maHoSo,
//                                             HttpServletRequest request) {
//        return ResponseEntity.ok(sHoSo.choPhepXuLyHoSo(maLoaiCongViecHienTai, maHoSo, getToken(request)));
//    }
//
//    @PostMapping("/uploadTaiLieuXuLy")
//    public ResponseEntity<?> uploadTaiLieuXuLy(@RequestParam("taiLieuXuLy") MultipartFile fileTaiLieu,
//                                               @RequestParam("maHoSo") String maHoSo,
//                                               @RequestParam("maLoaiCongViec") String maLoaiCongViec, @RequestParam("nhanTg") String nhanTg, @RequestParam("maGiayTo") String maGiayTo,
//                                               @RequestParam("laSanPhamBg") String laSanPhamBg,
//                                               HttpServletRequest request) {
//        Integer iMaHoSo = ParseUtil.parseInteger(maHoSo);
//        Integer iMaLoaiCongViec = ParseUtil.parseInteger(maLoaiCongViec);
//        Integer iMaGiayTo = ParseUtil.parseInteger(maGiayTo);
//        Integer iLaSanPhamBg = ParseUtil.parseInteger(laSanPhamBg);
//        if (ObjectUtils.isEmpty(iMaLoaiCongViec) || ObjectUtils.isEmpty(iMaHoSo) || ObjectUtils.isEmpty(nhanTg) || ObjectUtils.isEmpty(iMaGiayTo) || ObjectUtils.isEmpty(iLaSanPhamBg)) {
//            return ResponseEntity.ok(new AppResponse(AppError.BAD_REQUEST, appError.getBAD_REQUEST_ERROR()));
//        }
//        TaiLieuXuLy taiLieuXuLy = sTaiLieuXl.uploadTaiLieuXuLy(fileTaiLieu, iMaHoSo, iMaLoaiCongViec, nhanTg, iMaGiayTo, iLaSanPhamBg, getToken(request));
//        if (!ObjectUtils.isEmpty(taiLieuXuLy)) {
//            return ResponseEntity.ok(new AppResponse(AppError.SUCCESS, taiLieuXuLy));
//        } else {
//            return ResponseEntity.ok(new AppResponse(AppError.INTERNAL_SERVER, appError.getINTERNAL_SERVER_ERROR()));
//        }
//    }
//
//    @PostMapping("/uploadThanhPhanHoSo")
//    public AppResponse uploadThanhPhanHoSo(@RequestParam("tphs") MultipartFile fileUpload) {
//        return sHoSo.uploadThanhPhanHoSo(fileUpload);
//    }
//}