package vttp2022.psf.day11.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import vttp2022.psf.day11.model.Comment;
import vttp2022.psf.day11.model.Game;
import vttp2022.psf.day11.repositories.GameRepository;
import vttp2022.psf.day11.service.GameService;

// RESTCONTROLLER CODE
// @RestController
// @RequestMapping(path="/game")
// public class GameController {

//     @Autowired
//     private GameService gameSvc;

//     @GetMapping(path="/{gid}", produces = MediaType.APPLICATION_JSON_VALUE)
//     public ResponseEntity<String> getGameAndCommentsById(@PathVariable Integer gid) {
//         Optional<Game> opt = gameSvc.getComments(gid);
//         JsonObjectBuilder builder = Json.createObjectBuilder();
//         if (opt.isEmpty())
//             return ResponseEntity.status(404)
//                 .body(
//                     builder.add("error", "not found: %s".formatted(gid))
//                         .build().toString()
//                 );

//         Game game = opt.get();

//         builder.add("gid", game.getGameId());
//         builder.add("name", game.getName());
//         JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
//         for (Comment c: game.getComments())
//             arrBuilder.add("/comment/%s".formatted(c.getcId()));
//         builder.add("comments", arrBuilder.build());

//         return ResponseEntity.ok(builder.build().toString());
//     }

// }


// CONTROLLER CODE
@Controller
@RequestMapping(path="/game")
public class GameController {

    @Autowired
    private GameRepository gameRepo;
    
    @GetMapping("/{gid}")
    public String gameResults(@PathVariable Integer gid, Model model) {

        Game game = new Game();

        game = gameRepo.getGameByGid(gid).orElse(null); // checking whether null or not because it's Optional

        List<Comment> comment = new LinkedList<>();
        comment = gameRepo.getCommentsByGid(gid);

        model.addAttribute("comment", comment);
        model.addAttribute("game", game);

        return "Result";

    }

}
