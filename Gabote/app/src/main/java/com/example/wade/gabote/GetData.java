package com.example.wade.gabote;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.MainThread;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.wade.gabote.GameScores;

public class GetData {
    protected GetData() {
        //Constructor
    }

    protected static class WeekDataTable implements ResultListener {
        //Column Variables
        private int[] gsis_id;
        private int[] gamekey;
        private String[] start_time;
        //private int[] week;
        private int week;
        private String[] day_of_week;
        private int[] year;
        private String[] season_type;
        private char[] finished;
        private String[] home_team;
        private int[] home_score;
        private int[] home_score_q1;
        private int[] home_score_q2;
        private int[] home_score_q3;
        private int[] home_score_q4;
        private int[] home_score_q5;
        private int[] home_turnovers;
        private String[] away_team;
        private int[] away_score;
        private int[] away_score_q1;
        private int[] away_score_q2;
        private int[] away_score_q3;
        private int[] away_score_q4;
        private int[] away_score_q5;
        private int[] away_turnovers;
        private String[] time_inserted;
        private String[] time_updated;
        private int gameCount;
        private Activity activity;

        public WeekDataTable(int week,Activity activity) {
            this.activity = activity;
            gsis_id = new int[16];
            gamekey = new int[16];
            start_time = new String[16];
            //week
            day_of_week = new String[16];
            year = new int[16];
            season_type = new String[16];
            finished = new char[16];
            home_team = new String[16];
            home_score = new int[16];
            home_score_q1 = new int[16];
            home_score_q2 = new int[16];
            home_score_q3 = new int[16];
            home_score_q4 = new int[16];
            home_score_q5 = new int[16];
            home_turnovers = new int[16];
            away_team = new String[16];
            away_score = new int[16];
            away_score_q1 = new int[16];
            away_score_q2 = new int[16];
            away_score_q3 = new int[16];
            away_score_q4 = new int[16];
            away_score_q5 = new int[16];
            away_turnovers = new int[16];
            time_inserted = new String[16];
            time_updated = new String[16];
            gameCount=0;

            GetNetworkConn task = new GetNetworkConn();
            task.setOnResultsListener(this);
            task.execute("SELECT * FROM game WHERE week=" + week + " AND season_year='2015' AND season_type='Regular' ORDER BY start_time");
        }

        public int[] getGsis_id(){return gsis_id;}
        public int[] getGameKey(){return gamekey;}
        public String[] getStart_time(){return start_time;}
        public String[] getDay_of_week(){return day_of_week;}
        public int[] getYear(){return year;}
        public String[] getSeason_type(){return season_type;}
        public char[] getFinished(){return finished;}
        public String[] getHome_team(){return home_team;}
        public int[] getHome_score(){return home_score;}
        public int[] getHome_score_q1(){return home_score_q1;}
        public int[] getHome_score_q2(){return home_score_q2;}
        public int[] getHome_score_q3(){return home_score_q3;}
        public int[] getHome_score_q4(){return home_score_q4;}
        public int[] getHome_score_q5(){return home_score_q5;}
        public int[] getHome_turnovers(){return home_turnovers;}
        public String[] getAway_team(){return away_team;}
        public int[] getAway_score(){return away_score;}
        public int[] getAway_score_q1(){return away_score_q1;}
        public int[] getAway_score_q2(){return away_score_q2;}
        public int[] getAway_score_q3(){return away_score_q3;}
        public int[] getAway_score_q4(){return away_score_q4;}
        public int[] getAway_score_q5(){return away_score_q5;}
        public int[] getAway_turnovers(){return away_turnovers;}
        public String[] getTime_inserted(){return time_inserted;}
        public String[] getTime_updated(){return time_updated;}
        public int getGame_count(){return gameCount;}

        @Override
        public void onResultSuccess(ArrayList<String[]> result) {
            for(int i=0; i<result.size();i++)
            {
                gsis_id[i] =        Integer.parseInt(result.get(i)[0]);
                gamekey[i] =        Integer.parseInt(result.get(i)[1]);
                start_time[i] =     (result.get(i)[2]);
                //week
                day_of_week[i] =    (result.get(i)[4]);
                year[i] =           Integer.parseInt(result.get(i)[5]);
                season_type[i] =    (result.get(i)[6]);
                finished[i] =       (result.get(i)[7].charAt(0));
                home_team[i] =      (result.get(i)[8]);
                home_score[i] =     Integer.parseInt(result.get(i)[9]);
                home_score_q1[i] =  Integer.parseInt(result.get(i)[10]);
                home_score_q2[i] =  Integer.parseInt(result.get(i)[11]);
                home_score_q3[i] =  Integer.parseInt(result.get(i)[12]);
                home_score_q4[i] =  Integer.parseInt(result.get(i)[13]);
                home_score_q5[i] =  Integer.parseInt(result.get(i)[14]);
                home_turnovers[i] = Integer.parseInt(result.get(i)[15]);
                away_team[i] =      (result.get(i)[16]);
                away_score[i] =     Integer.parseInt(result.get(i)[17]);
                away_score_q1[i] =  Integer.parseInt(result.get(i)[18]);
                away_score_q2[i] =  Integer.parseInt(result.get(i)[19]);
                away_score_q3[i] =  Integer.parseInt(result.get(i)[20]);
                away_score_q4[i] =  Integer.parseInt(result.get(i)[21]);
                away_score_q5[i] =  Integer.parseInt(result.get(i)[22]);
                away_turnovers[i] = Integer.parseInt(result.get(i)[23]);
                time_inserted[i] =  (result.get(i)[24]);
                time_updated[i] =   (result.get(i)[25]);
            }
            gameCount = result.size();
            updateGameScoreView();
        }
        private void updateGameScoreView()
        {
            ListView lv = (ListView)activity.findViewById(R.id.listView);
            ArrayList<String> gameList = new ArrayList<String>();

            String temp;
            for(int i=0; i<this.getGame_count();i++)
            {
                temp = "";
                temp+= this.getHome_team()[i] + ": " + this.getHome_score()[i];
                temp+="    ";
                temp+= this.getAway_team()[i] + ": " + this.getAway_score()[i];
                gameList.add(i,temp);
            }
            ArrayAdapter<String> la = new ArrayAdapter<String>(activity,R.layout.rowdef,gameList);

            lv.setAdapter(la);
        }
    }
}
