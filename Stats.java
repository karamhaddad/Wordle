
// Stats.java
public class Stats {

    private int gamesPlayed;
    private int gamesWon;

    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    public void incrementGamesWon() {
        gamesWon++;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }
}