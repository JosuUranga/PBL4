package modelos;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;
import objetos.LocalizacionVO;

public class LocalizacionDAO extends DBManager{
	Statement statement, tempStatement;
	ResultSet resultSet, tempResultSet;
	CallableStatement callStatement;
	static LocalizacionDAO instance;
	LocalizacionVO localizacion;
	List<LocalizacionVO>listLoca;
	public final static String insertLocalizacion="{CALL insert_Localizacion(?,?,?)}";
	public final static String getLocalizacionMuestra="{CALL get_localizacion_where_exists_muestra()}";
	public final static String getAllLocalizaciones="{CALL get_localizaciones()}";
	
	protected LocalizacionDAO(String u, String p, String db, String ip) {
		super(u, p, db, ip);
	}
	public static LocalizacionDAO getInstance(String u,String p,String db, String ip) {
		if (instance == null) {
			instance = new LocalizacionDAO(u, p, db, ip);
		}
		return instance;
	}
	public LocalizacionVO getLocalizacionUser(String email) throws SQLException{
		resultSet=executeQuery("SELECT Localizaciones.nombre,Localizaciones.habitantes,Localizaciones.AREAkm2,Localizaciones.localizacionID "
				+ "FROM Localizaciones WHERE email='"+email+"'");
		resultSet.next();
		localizacion=new LocalizacionVO(resultSet.getInt(4),resultSet.getString(1),resultSet.getInt(2),resultSet.getFloat(3));
		conClose();
		return localizacion;
	}
	public List<LocalizacionVO> getLocalizacionesMuestra() throws SQLException{
		callStatement=executeCall(getLocalizacionMuestra);
		resultSet=callStatement.executeQuery();
		listLoca=new ArrayList<>();
		while(resultSet.next()) {
			localizacion=new LocalizacionVO(resultSet.getInt(4),resultSet.getString(1),resultSet.getInt(2),resultSet.getFloat(3));
			listLoca.add(localizacion);
		}
		conClose();
		return listLoca;
	}
	public List<LocalizacionVO> getAllLocalizaciones() throws SQLException{
		callStatement=executeCall(getAllLocalizaciones);
		resultSet=callStatement.executeQuery();
		listLoca=new ArrayList<>();
		while(resultSet.next()) {
			localizacion=new LocalizacionVO(resultSet.getInt(4),resultSet.getString(1),resultSet.getInt(2),resultSet.getFloat(3));
			listLoca.add(localizacion);
		}
		conClose();
		return listLoca;
	}
	public void addLocalizacion(String nombre,int habitantes,float AREAkm2) throws SQLException{
		execute("INSERT INTO Localizaciones (nombre, habitantes, areakm2) VALUES ('"+nombre+"', "+habitantes+", "+AREAkm2+");");
	}

}
