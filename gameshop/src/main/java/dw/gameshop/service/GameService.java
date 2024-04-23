package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.model.User;
import dw.gameshop.repository.GameRepository;
import dw.gameshop.repository.ReviewRepository;
import dw.gameshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    GameRepository gameRepository;  //의존성주입을 잊지말구 하기
    UserRepository userRepository;
@Autowired
    public GameService(GameRepository gameRepository, UserRepository userRepository, ReviewRepository reviewRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public Game saveEmployee(Game game){
        gameRepository.save(game);
        return game;
    }
    public List<Game> getAllGame() {return gameRepository.findAll();}  //엑티브를 블리언으로 해서 접근가능,불가능으로 설정할수 있다.

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

    //제일 비싼 게임의 정보
    public Game getGameWithMaxPrice() {
        //3가지 방법중 어떤것을 사용해도 되는 파트이다.
        //1)service 모듈에서 자바로 구현하는 방법
        List<Game> games =  gameRepository.findAll(); //일단 전체게임정보를 모두 불러와서 넣어 놓는다.



/*        //1. 람다식이 아닌 일반 자바코드 사용 예
        if (games.size() <= ){  //예외를 넣어줄려면
            throw new ResourceNotFoundException("Max Price"," ", " ");
        }
        (밑에는 예외처리가 안들어감)
        Game max = games.get(0);
        for (int i = 0; i<games.size()-1; i++){ //끝까지 계산하면 안된다.그래서 밑에 +1이 들어가면 -1를 위에서 해줘야한다.
            if (max.getPrice() < games.get(i+1).getPrice()){
                max = games.get(i+1);
            }
        }//앞뒤를 계속비교해서 이동시킨다.
        return max;*/

   /*     //2. 람다식 사용 예
        return games.stream().sorted(Comparator.comparingInt(Game::getPrice)  //((Game g)-> g.getPrice()) 을 (Game::getPrice) 식으로도 사용가능
                .reversed())
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Max Price"," ", " ")); //예외
        //sort : 보이드형,리턴이 없이 결과값이 없는것
        //sorted : 메소드의 흐림이 계속해서 이어진다. 항상 stream
        //게임을 스트림화(연달아 정렬) 한다. 트라이캐치문과 비슷하다. */

        //3. JPQL 사용 예
        return gameRepository.getGameWithMaxPrice();

    }

    //제일 비싼 게임 TOP3 =>정렬이 되어야 한다.
    public List<Game> getGameWithMaxPriceTop3(){
        List<Game> games =  gameRepository.findAll();
        //sort사용 =>람다식 사용법으로 알려주겠다.

   /*     //람다식이 아닌 일반 자바코드 사용 예
        games.sort(Comparator.comparingInt((Game g) -> g.getPrice()).reversed()); //comparingInt 특이한 사용법 ()안에 익명함수를 사용한다. 받아오는 녀석이 개인
                                              //(game -> game.getPrice()))네츄럴 오더라 오름차순이다. => 익명함수형태
                                              //Comparator.comparingInt(game -> game.getPrice())에 붙은 reversed 이다.
                                              //getPrice이 에러난 이유는 참조가 없다고 에러난다. Game 속의 변수명 g 이다.라고 알려줘야한다.
        List<Game> newGames = new ArrayList<>();
        newGames.add(games.get(0));
        newGames.add(games.get(1));
        newGames.add(games.get(2));
        return newGames;      */

      /*  //람다식 사용 예
        return games.stream()
                .sorted(Comparator.comparingInt(Game::getPrice).reversed())
                .limit(3)
                .collect(Collectors.toList()); //3개를 현재 스트림상태인데, 리스트를 만들기 위해서는.collect(Collectors.toList())을 사용한다. Collectors.toList()대신에 set으로 사용할수도 있다.
*/
        //JPQL 사용 예
        return gameRepository.getGameWithMaxPriceTop3()
                .stream().limit(3).collect(Collectors.toList());
    }
}
