package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Train;

import java.util.List;

public interface TrainServiceInterface {
    Train findById(long id);

    List<Train> findAll();

    Train save(Train train);

    void deleteById(Long id);

    void update(Long id, Train train);

    Train findByCode(String trainCode);

    List<String> findAllByCode();
}
