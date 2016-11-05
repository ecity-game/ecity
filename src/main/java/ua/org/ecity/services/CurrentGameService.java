package ua.org.ecity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.ecity.entities.CurrentGame;
import ua.org.ecity.repository.CurrentGameRepository;

import java.util.List;

@Service
public class CurrentGameService {

    @Autowired
    private CurrentGameRepository currentGameRepository;

    public List<CurrentGame> getCurrentGameById (Long id) {
        return currentGameRepository.findById(id);
    }

    public List<CurrentGame> getCurrentGameByName(String name) {
        return currentGameRepository.findByName(name);
    }

    public List<CurrentGame> getCurrentGameByState(String state) {
        return currentGameRepository.findByState(state);
    }

}
