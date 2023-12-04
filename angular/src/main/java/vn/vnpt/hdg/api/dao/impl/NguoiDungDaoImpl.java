package vn.vnpt.hdg.api.dao.impl;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;

import vn.vnpt.hdg.api.constants.DBProcedure;
import vn.vnpt.hdg.api.dao.NguoiDungDao;
import vn.vnpt.hdg.api.dao.base.BaseCursorDAO;
import vn.vnpt.hdg.api.models.NguoiDung;
import vn.vnpt.hdg.api.utils.Utils;

public class NguoiDungDaoImpl extends BaseCursorDAO implements NguoiDungDao {

	@Override
	public NguoiDung getNguoiDungTheoToken(String token) {
		try {
            doProcedure(DBProcedure.TT_NGUOI_DUNG_TOKEN.name(),
                    null,
                    token);

            String json = jsonResult.toString();
            List<NguoiDung> data = Utils.getObjectMapper().readValue(json, new TypeReference<List<NguoiDung>>() {
            });

            if (!CollectionUtils.isEmpty(data)) {
                return data.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
	}

}
