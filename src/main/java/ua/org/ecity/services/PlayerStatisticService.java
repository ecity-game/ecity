package ua.org.ecity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.ecity.entities.PlayerStatistic;
import ua.org.ecity.repository.PlayerStatisticRepository;

import java.util.List;

@Service
public class PlayerStatisticService {

    @Autowired
    private PlayerStatisticRepository playerStatisticRepository;

    public List<PlayerStatistic> getPlayerStatisticById(Long id) {
        return playerStatisticRepository.findById(id);
    }
}
