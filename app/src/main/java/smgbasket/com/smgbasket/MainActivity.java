package smgbasket.com.smgbasket;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int POINTS_FOR_3_POINT_SHOT = 3;
    public static final int POINTS_FOR_2_POINT_SHOT = 2;
    public static final int POINTS_FOR_FREE_THROW = 1;

    private int scoreTeamA = 0;
    private int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Tweet your comment", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);

    }

    /**
     * Adds 3 points to Team A's score and displays the updated score
     */
    public void threePointsForTeamA(View view) {
        scoreTeamA += POINTS_FOR_3_POINT_SHOT;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Adds 2 points to Team A's score and displays the updated score
     */
    public void twoPointsForTeamA(View view) {
        scoreTeamA += POINTS_FOR_2_POINT_SHOT;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Adds 1 point to Team A's score and displays the updated score
     */
    public void freeThrowForTeamA(View view) {
        scoreTeamA += POINTS_FOR_FREE_THROW;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Adds 3 points to Team B's score and displays the updated score
     */
    public void threePointsForTeamB(View view) {
        scoreTeamB += POINTS_FOR_3_POINT_SHOT;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Adds 2 points to Team B's score and displays the updated score
     */
    public void twoPointsForTeamB(View view) {
        scoreTeamB += POINTS_FOR_2_POINT_SHOT;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Adds 1 point to Team B's score and displays the updated score
     */
    public void freeThrowForTeamB(View view) {
        scoreTeamB += POINTS_FOR_FREE_THROW;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Resets and displays score for both teams
     */
    public void resetScores(View view) {
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        dialog.setMessage("Warning Information: Are you sure you want reset the score?");
        dialog.setPositiveButton("Yes, I want", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                scoreTeamA = 0;
                scoreTeamB = 0;
                displayForTeamA(scoreTeamA);
                displayForTeamB(scoreTeamB);
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

    }

    /**
     * Send/Share the score*/
    public void sendScores(View view){

        EditText TeamA= (EditText) findViewById(R.id.teamA);
        EditText TeamB= (EditText)findViewById(R.id.teamB);

        String teamA = TeamA.getText().toString();
        String teamB = TeamB.getText().toString();

        if (teamA.isEmpty() || teamB.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill up the teams name, thanks",Toast.LENGTH_LONG).show();
        }else{
            /*Intent intent= new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setPackage("com.whatapp");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>The score of the Team " + teamA + " is: " + scoreTeamA + "</p>" + " The score of the Team " + teamB + " is: " + scoreTeamB + "</p>"));
            intent.setType("text/plan");
            startActivity(Intent.createChooser(intent, "Share with"));*/
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
//            shareIntent.putExtra(Intent.EXTRA_TEXT, "I've just found a great place where take a great photos! Want discover more? Check out this awesome spot: https://www.photospotland.com/spots/" );
            shareIntent.putExtra(Intent.EXTRA_TEXT,"The score of the Team " + teamA + " is: " + scoreTeamA+" " +
                    "          The score of the Team " + teamB + " is: " + scoreTeamB + "");
            shareIntent.setType("text/plain");
            startActivity(shareIntent);
        }

    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
