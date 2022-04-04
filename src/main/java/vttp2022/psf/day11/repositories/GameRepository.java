package vttp2022.psf.day11.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.psf.day11.model.Comment;
import vttp2022.psf.day11.model.Game;
import static vttp2022.psf.day11.repositories.Queries.*;

@Repository
public class GameRepository {

    @Autowired
    private JdbcTemplate template;

    public List<Comment> getCommentsByGid(Integer gid) {
        return getCommentsByGid(gid, Integer.MAX_VALUE, 0);
    }

    public List<Comment> getCommentsByGid(Integer gid, Integer limit) {
        return getCommentsByGid(gid, limit, 0);
    }

    public List<Comment> getCommentsByGid(Integer gid, Integer limit, Integer offset) {

        final List<Comment> comments = new LinkedList<>();

        final SqlRowSet result = template.queryForRowSet(
            SQL_SELECT_COMMENT_BY_GID, gid, limit, offset
        );

        // while there is a next row, it will keep adding to the comments

        while (result.next()) {
            Comment comment = Comment.create(result);
            comments.add(comment);
        }

        return comments;

        // final SqlRowSet result = template.queryForRowSet(
        //     SQL_SELECT_COMMENT_BY_GID, gid, limit, offset
        // );
        
        // ArrayList getComments = new ArrayList<>();
        // getComments.add(result);

        // return getComments;
    }

    public Optional<Game> getGameByGid(Integer query) {
        final SqlRowSet result = template.queryForRowSet(
            // select * from game where gid = <gid>
            // NEVER EVER EVER DO CONCATENATION WITH ANY SQL, EVER (like SQL_SELECT_GAME_BY_GID + gid)
            SQL_SELECT_GAME_BY_GID, query
        );

        // if there is a row, then result.next() returns true, and !result.next() returns false and it skips down
        // if there is no row, then !result.next() returns true and returns Optional.empty()

        if (!result.next()) 
            return Optional.empty();

        // Integer gid = result.getInt("gid");
        // String name = result.getNString("name");
        // Integer year = result.getInt("year");
        // Integer ranking = result.getInt("ranking");
        // Integer users_rated = result.getInt("users_rated");
        // String url = result.getNString("url");
        // String image = result.getNString("image");

        return Optional.of(Game.create(result));
    }
    
}
