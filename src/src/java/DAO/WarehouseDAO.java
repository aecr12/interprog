package DAO;

import Model.Warehouse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aykut
 * @author Metin
 */
public class WarehouseDAO implements IDAO<Warehouse> {

	@Override
	public Warehouse find(int id) {
		Warehouse warehouse = new Warehouse();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM warehouse WHERE id=?");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				warehouse.setId(rs.getInt("id"));
				warehouse.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			Logger.getLogger(WarehouseDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return warehouse;
	}

	@Override
	public List<Warehouse> findAll() {
		List<Warehouse> warehouses = new ArrayList<>();
		Warehouse warehouse;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM warehouse");
			rs = ps.executeQuery();

			while (rs.next()) {
				warehouse = new Warehouse(
								rs.getInt("id"),
								rs.getString("name")
				);
				warehouses.add(warehouse);
			}
		} catch (SQLException e) {
			Logger.getLogger(WarehouseDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return warehouses;
	}

	@Override
	public int create(Warehouse w) {
		int id = -1;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("INSERT INTO warehouse (name) VALUES (?) RETURNING id");
			ps.setString(1, w.getName());

			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			Logger.getLogger(WarehouseDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return id;
	}

	@Override
	public void update(Warehouse w) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("UPDATE warehouse SET name=? where id=?");
			ps.setString(1, w.getName());
			ps.setInt(2, w.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(WarehouseDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("DELETE FROM warehouse WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(WarehouseDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public Warehouse findByName(String name) {
		Warehouse warehouse = new Warehouse();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM warehouse WHERE name =?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				warehouse.setId(rs.getInt("id"));
				warehouse.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			Logger.getLogger(WarehouseDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return warehouse;
	}

	@Override
	public List<Warehouse> findAll(int p, int ps) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}
}
