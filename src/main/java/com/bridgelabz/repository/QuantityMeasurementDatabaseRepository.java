package com.bridgelabz.repository;

import com.bridgelabz.exception.DatabaseException;
import com.bridgelabz.model.QuantityMeasurementEntity;
import com.bridgelabz.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    @Override
    public void save(QuantityMeasurementEntity entity) {
        String sql = "INSERT INTO quantity_measurement (value, unit, operation) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, entity.getValue());
            stmt.setString(2, entity.getUnit());
            stmt.setString(3, entity.getOperation());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Error saving data", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAll() {
        List<QuantityMeasurementEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM quantity_measurement";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

                entity.setValue(rs.getDouble("value"));
                entity.setUnit(rs.getString("unit"));
                entity.setOperation(rs.getString("operation"));

                list.add(entity);
            }

        } catch (Exception e) {
            throw new DatabaseException("Error fetching data", e);
        }

        return list;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM quantity_measurement";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Error deleting data", e);
        }
    }

    @Override
    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM quantity_measurement";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            throw new DatabaseException("Error counting data", e);
        }

        return 0;
    }
}