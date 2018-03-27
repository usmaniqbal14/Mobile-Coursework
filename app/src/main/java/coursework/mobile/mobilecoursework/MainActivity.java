package coursework.mobile.mobilecoursework;
// Usman Iqbal - S1425850

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    private Button current;
    private Button planned;

    // Setting up buttons and layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        current = (Button) findViewById(R.id.current);
        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCurrentIncidents();
            }
        });

        planned = (Button) findViewById(R.id.planned);
        planned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlannedRoadWorks();
            }
        });

    }

    // The CurrentIncidetnsActivity Class will run when the current button is clicked
    public void openCurrentIncidents() {
        Intent intent = new Intent(this, CurrentIncidentsActivity.class);
        startActivity(intent);
    }
    // The PlannedRoadworksActivity Class will run when the planned button is clicked
    public void openPlannedRoadWorks() {
        Intent intent = new Intent(this, PlannedRoadworksActivity.class);
        startActivity(intent);
    }

}