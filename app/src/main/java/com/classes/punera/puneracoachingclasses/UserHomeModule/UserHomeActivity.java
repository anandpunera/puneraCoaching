package com.classes.punera.puneracoachingclasses.UserHomeModule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.classes.punera.puneracoachingclasses.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by punera on 10/12/2018.
 */

public class UserHomeActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home_layout);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("About Us");
        listDataHeader.add("Topics");
        listDataHeader.add("Faculty");
        listDataHeader.add("Coming Soon..");
        listDataHeader.add("Contact Us");

        // Adding child data
        List<String> aboutUs = new ArrayList<String>();
        aboutUs.add("We are Premier Coaching Center for General Aptitiude and General Knowledge. The course helps" +
                " prepare students for various competetive exams like IBPS, SSC, NDA, CDSE, etc.");

        List<String> topics = new ArrayList<String>();
        topics.add("The Quantitative Aptitude");
        topics.add("Verbal Ability");
        topics.add("Mathematics for logic building");
        topics.add("General Sciences");
        topics.add("Logical Reasoning");
        topics.add("Data Interpretation");


        List<String> faculty = new ArrayList<String>();
        faculty.add("Sunil Punera");
        faculty.add("Anand Punera");

        List<String> comingsoon = new ArrayList<String>();
        comingsoon.add("Basics of Computer Science");
        comingsoon.add("Computer Programming using Java");
        comingsoon.add("class XI, XII Mathematics");
        comingsoon.add("class XI, XII Physics");

        List<String> contactUs = new ArrayList<String>();
        contactUs.add("Phone No. +917415157982");
        contactUs.add("email: anand.c.punera@gmail.com");

        listDataChild.put(listDataHeader.get(0), aboutUs); // Header, Child data
        listDataChild.put(listDataHeader.get(1), topics);
        listDataChild.put(listDataHeader.get(2), faculty);
        listDataChild.put(listDataHeader.get(3), comingsoon);
        listDataChild.put(listDataHeader.get(4), contactUs);

    }
}
