package vttp2022.psf.day11.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {

    private Integer gameId;
    private String cId;
    private String user;
    private String cText;
    private Integer rating;

    public Integer getGameId() {
        return gameId;
    }
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
    public String getcId() {
        return cId;
    }
    public void setcId(String cId) {
        this.cId = cId;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getcText() {
        return cText;
    }
    public void setcText(String cText) {
        this.cText = cText;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public static Comment create(SqlRowSet rs) {
        Comment comment = new Comment();

        comment.setGameId(rs.getInt("gid"));
        comment.setRating(rs.getInt("rating"));
        comment.setUser(rs.getString("user"));
        comment.setcId(rs.getString("c_id"));
        comment.setcText(rs.getString("c_text"));

        return comment;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("commentId", cId)
            .add("user", user)
            .add("rating", rating)
            .add("comment", cText)
            .add("gameId", gameId)
            .build();
    }

    
    
}
