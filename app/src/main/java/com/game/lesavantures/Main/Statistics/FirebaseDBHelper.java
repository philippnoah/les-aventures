package com.game.lesavantures.Main.Statistics;

import android.os.Bundle;
import android.util.Log;

import com.game.lesavantures.Main.GameManager;
import com.game.lesavantures.Main.Level.LevelManager;
import com.game.lesavantures.Main.Level.Level;
import com.game.lesavantures.Main.User.ScoreboardUser;
import com.game.lesavantures.Main.User.User;
import com.game.lesavantures.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FirebaseDBHelper implements DatabaseHelper {

    /**
     * Firebase database reference variable. This is used to connect to the database to read and write data.
     */
    private FirebaseFirestore db;

    /**
     * Used for reference in all firebase-related methods.
     */
    String collection = "statistics";
    private static String TAG = "FIREBASE_DB_HELPER";

    /**
     * Initialize the Firebase instance
     */
    public FirebaseDBHelper() {
        this.db = FirebaseFirestore.getInstance();
    }

    /**
     * Save the current user statistics to the db
     * @param user
     */
    @Override
    public void saveUserStatisticsForUser(User user) {

        String doc = user.getId();
        Map<String, Object> data = new HashMap<>();
        data.put("DISPLAY_NAME", user.getDisplayName());
        data.putAll(user.getUserStatistics().toMap());

        db.collection(collection).document(doc).update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "onSuccess: SUCCESS");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding document", e);
            }
        });
    }

    /**
     * Initialize the user object in the db when they sign up for the first time.
     *
     * @param user
     */
    @Override
    public void initUserStatisticsForUser(User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("DISPLAY_NAME", user.getDisplayName());
        db.collection(collection).document(user.getId()).set(data);
    }

    @Override
    public UserStatistics getUserStatisticsByUserId(String userId) {
        return null;
    }

    /**
     * Retrieve the top n statistics from the firebase db
     * @param n
     */
    @Override
    public void fetchTopNStatistics(int n) {
        final String orderField = Level.LEVEL_1.toString();
        final Query query = db.collection(collection).orderBy(orderField, Query.Direction.DESCENDING).limit(n);
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                HashMap<String, ScoreboardUser> scoreboard = createScoreboardFromDocuments(queryDocumentSnapshots);
                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                GameManager.statisticsManager.setScoreboard(scoreboard);
            }
        });
    }

    private HashMap<String, ScoreboardUser> createScoreboardFromDocuments(QuerySnapshot snapshot) {
        HashMap<String, ScoreboardUser> scoreboard = new HashMap<>();
        for (DocumentSnapshot document : snapshot) {
            // doc = user object [displayName, [LevelScores]]
            String displayName = "" + document.getData().get("DISPLAY_NAME");
            UserStatistics userStatistics = new UserStatistics();
            for (Level level : Level.values()) {
                if (document.getData().containsKey(level.toString())) {
                    int score = Math.toIntExact((long) document.getData().get(level.toString()));
                    LevelStatistics levelStatistics = LevelManager.buildLevelStatistics(level, score);
                    userStatistics.setLevelStatistics(levelStatistics);
                }
            }
            ScoreboardUser scoreboardUser = new ScoreboardUser(displayName, userStatistics);
            scoreboard.put(scoreboardUser.getDisplayName(), scoreboardUser);
        }
        return scoreboard;
    }
}
