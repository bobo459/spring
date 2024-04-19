package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.model.User;
import dw.gameshop.repository.GameRepository;
import dw.gameshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    GameRepository gameRepository;
    UserRepository userRepository;

    public GameService(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public Game saveEmployee(Game game){
        gameRepository.save(game);
        return game;
    }
    public List<Game> getAllGame() {return gameRepository.findAll();}

    public Game getGameById(long id){
        Optional<Game> game = gameRepository.findById(id);
        if (game.isEmpty()){
            throw new ResourceNotFoundException("Employee", "ID", id);
          //  return null;
        }else {
            return game.get();
        }
    }
    public Game updateGameById(long id, Game game) {
        Optional<Game> game1 = gameRepository.findById(id);
        if (game1.isPresent()) {
            game1.get().setGenre(game.getGenre());
            game1.get().setImage(game.getImage());
            game1.get().setText(game.getText());
            game1.get().setPrice(game.getPrice());
            game1.get().setTitle(game.getTitle());

            gameRepository.save(game1.get());
            return game1.get();
        } else {
            throw new ResourceNotFoundException("Employee", "ID", id);
            //return null;
        }
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
}
