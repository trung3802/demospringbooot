package vn.vnpt.hdg.api.dao.base;

import vn.vnpt.hdg.api.dao.NguoiDungDao;
import vn.vnpt.hdg.api.dao.impl.NguoiDungDaoImpl;

public class DaoFactory {
	public static NguoiDungDao getNguoiDungDao() {
		return new NguoiDungDaoImpl();
	}
}
