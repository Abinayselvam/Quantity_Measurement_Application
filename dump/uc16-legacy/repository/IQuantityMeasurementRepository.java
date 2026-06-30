package org.example.project.repository;
import org.example.project.entities.QuantityMeasurementEntity;
import java.util.List;

        public interface IQuantityMeasurementRepository {

                void save(QuantityMeasurementEntity entity);

                List<QuantityMeasurementEntity> findAll();

                QuantityMeasurementEntity findById(int id);

                void updateById(int id,QuantityMeasurementEntity entity);

                void deleteById(int id);

        }


