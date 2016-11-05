package ua.org.ecity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.ecity.entities.GameStatistic;
import ua.org.ecity.repository.GameStatisticRepository;

import java.util.List;

@Service
public class GameStatisticService {

    @Autowired
    private GameStatisticRepository gameStatisticRepository;

    public List<GameStatistic> getGameStatisticById (Long id) {
        return gameStatisticRepository.findById(id);
    }
}
