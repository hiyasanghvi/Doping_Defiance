package com.example.doping_defiance;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class kym extends AppCompatActivity {

    // List of medicines prohibited in competition
    private final List<String> prohibitedInCompetition = Arrays.asList(
            "A 1 5MG TABLET 10S",
            "DECA",
            "EPOGEN",
            "ANADROL",
            "HGH (HUMAN GROWTH HORMONE)",
            "Adrafinil",
            "Amfepramone",
            "Amfetamine",
            "Amfetaminil",
            "Amiphenazole",
            "Benfluorex",
            "Benzylpiperazine",
            "Bromantan",
            "Clobenzorex",
            "Cocaine",
            "Cropropamide",
            "Crotetamide",
            "Fencamine",
            "Fenetylline",
            "Fenfluramine",
            "Fenproporex",
            "Fonturacetam [4-phenylpiracetam (carphedon)]",
            "Furfenorex",
            "Lisdexamfetamine",
            "Mefenorex",
            "Mephentermine",
            "Mesocarb",
            "Metamfetamine(d-)",
            "p-methylamfetamine",
            "Modafinil",
            "Norfenfluramine",
            "Phendimetrazine",
            "Phentermine",
            "Prenylamine",
            "Prolintane"
    );


    // List of medicines prohibited out of competition
    private final List<String> prohibitedOutOfCompetition = Arrays.asList(
            "A 2 V 10MG TABLET 10S",
            "DECA",
            "CLENBUTEROL",
            "STANOZOLOL"
    );

    // List of medicines prohibited in both in and out of competition
    private final List<String> prohibitedInAndOutOfCompetition = Arrays.asList(
            "DECA",
            "TESTOSTERONE GEL",
            "INSULIN",
            "1-Androstenediol (5ɑ-androst-1-ene-3ß, 17ß-diol)",
            "1-Androstenedione (5ɑ-androst-1-ene-3, 17-dione)",
            "1-Androsterone (3ɑ-hydroxy-5a-androst-1-ene-17-one)",
            "1-Epiandrosterone (3ß-hydroxy-5ɑ-androst-1-ene-17-one)",
            "1-Testosterone (17ß-hydroxy-5ɑ-androst-1-en-3-one)",
            "4-Androstenediol (androst-4-ene-3ß, 17ß-diol)",
            "4-Hydroxytestosterone (4,17ß-dihydroxyandrost-4-en-3-one)",
            "5-Androstenedione (androst-5-ene-3,17-dione)",
            "7ɑ-Hydroxy-DHEA",
            "7ß-Hydroxy-DHEA",
            "7-Keto-DHEA",
            "11ß-Methyl-19-nortestosterone",
            "17ɑ-Methylepithiostanol (epistane)",
            "19-Norandrostenediol (estr-4-ene-3,17-diol)",
            "19-Norandrostenedione (estr-4-ene-3,17-dione)",
            "Androst-4-ene-3,11,17-trione (11-ketoandrostenedione, adrenosterone)",
            "Androstanolone (5ɑ-dihydrotestosterone, 17ß-hydroxy-5ɑ-androstan-3-one)",
            "Androstenediol (androst-5-ene-3ß,17ß-diol)",
            "Androstenedione (androst-4-ene-3,17-dione)",
            "Bolasterone",
            "Boldenone",
            "Boldione (androsta-1,4-diene-3,17-dione)",
            "Calusterone",
            "Clostebol",
            "Danazol ([1,2]oxazolo[4’,5’:2,3]pregna-4-en-20-yn-17ɑ-ol)",
            "Dehydrochlormethyltestosterone (4-chloro-17ß-hydroxy-17ɑ-methylandrosta-1,4-dien-3-one)",
            "Desoxymethyltestosterone (17ɑ-methyl-5ɑ-androst-2-en-17ß-ol and 17ɑ-methyl-5ɑ-androst-3-en-17ß-ol)",
            "Dimethandrolone (7ɑ,11ß-Dimethyl-19-nortestosterone)",
            "Drostanolone",
            "Epiandrosterone (3ß-hydroxy-5ɑ-androstan-17-one)",
            "Epi-dihydrotestosterone (17ß-hydroxy-5ß-androstan-3-one)",
            "Epitestosterone",
            "Ethylestrenol (19-norpregna-4-en-17ɑ-ol)",
            "Fluoxymesterone",
            "Formebolone",
            "Furazabol (17ɑ-methyl [1,2,5]oxadiazolo[3’,4’:2,3]-5ɑ-androstan-17ß-ol)",
            "Gestrinone",
            "Mesterolone",
            "Metandienone (17ß-hydroxy-17ɑ-methylandrosta-1,4-dien-3-one)",
            "Metenolone",
            "Methandriol",
            "Methasterone (17ß-hydroxy-2ɑ,17ɑ-dimethyl-5ɑ-androstan-3-one)",
            "Methyl-1-testosterone (17ß-hydroxy-17ɑ-methyl-5ɑ-androst-1-en-3-one)",
            "Methylclostebol",
            "Methyldienolone (17ß-hydroxy-17ɑ-methylestra-4,9-dien-3-one)",
            "Methylnortestosterone (17ß-hydroxy-17ɑ-methylestr-4-en-3-one)",
            "Methyltestosterone",
            "Metribolone (methyltrienolone, 17ß-hydroxy-17ɑ-methylestra-4,9,11-trien-3-one)",
            "Mibolerone",
            "Nandrolone (19-nortestosterone)",
            "Norboletone",
            "Norclostebol (4-chloro-17ß-ol-estr-4-en-3-one)",
            "Norethandrolone",
            "Oxabolone",
            "Oxandrolone",
            "Oxymesterone",
            "Oxymetholone",
            "Prasterone (dehydroepiandrosterone, DHEA, 3ß-hydroxyandrost-5-en-17-one)",
            "Prostanozol (17ß-[(tetrahydropyran-2-yl)oxy]-1’H-pyrazolo[3,4:2,3]-5ɑ-androstane)",
            "Quinbolone",
            "Stanozolol",
            "Stenbolone",
            "Testosterone",
            "Tetrahydrogestrinone (17-hydroxy-18a-homo-19-nor-17ɑ-pregna-4,9,11-trien-3-one)",
            "Tibolone",
            "Trenbolone (17ß-hydroxyestr-4,9,11-trien-3-one)",
            "Trestolone (7ɑ-Methyl-19-nortestosterone, MENT)",
            "Erythropoietin receptor agonists, e.g. darbepoetins (dEPO); erythropoietins (EPO); EPO-based constructs [e.g. EPO-Fc, methoxy polyethylene glycol-epoetin beta (CERA)]; EPO-mimetic agents and their constructs (e.g. CNTO-530, peginesatide)",
            "Hypoxia-inducible factor (HIF) activating agents, e.g. cobalt; daprodustat (GSK1278863); IOX2; molidustat (BAY 85-3934); roxadustat (FG-4592); vadadustat (AKB-6548); xenon",
            "GATA inhibitors, e.g. K-11706",
            "Transforming growth factor beta (TGF-ß) signalling inhibitors, e.g. luspatercept; sotatercept",
            "Innate repair receptor agonists, e.g. asialo EPO; carbamylated EPO (CEPO)"
    );


    // Unified list of all prohibited medicines
    private final Set<String> allProhibitedMedicines = new HashSet<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kym);

        // Combine all prohibited medicines into a single set to optimize searching
        allProhibitedMedicines.addAll(prohibitedInCompetition);
        allProhibitedMedicines.addAll(prohibitedOutOfCompetition);
        allProhibitedMedicines.addAll(prohibitedInAndOutOfCompetition);

        // Initialize UI components
        EditText medicineSearch = findViewById(R.id.medicineSearch);
        Button searchButton = findViewById(R.id.selectImageButton);
        ListView resultListView = findViewById(R.id.resultListView);
        TextView resultsHeader = findViewById(R.id.resultsHeader);

        // Setup User Type and Sport Spinners
        setupUserTypeSpinner();
        setupSportSpinner();

        // Set up the search button functionality
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = medicineSearch.getText().toString().trim();
                if (query.isEmpty()) {
                    Toast.makeText(kym.this, "Enter a medicine name", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Search for the medicine and get results
                List<String> searchResults = searchMedicine(query);

                // Display the results
                if (searchResults.isEmpty()) {
                    resultsHeader.setVisibility(View.GONE);
                    resultListView.setVisibility(View.GONE);
                    Toast.makeText(kym.this, "No results found for " + query, Toast.LENGTH_SHORT).show();
                } else {
                    resultsHeader.setVisibility(View.VISIBLE);
                    resultListView.setVisibility(View.VISIBLE);

                    // Update the ListView with the search results
                    ArrayAdapter<String> resultAdapter = new ArrayAdapter<>(
                            kym.this,
                            android.R.layout.simple_list_item_1,
                            searchResults
                    );
                    resultListView.setAdapter(resultAdapter);
                }
            }
        });
    }

    /**
     * Searches for medicines and determines if they are prohibited in competition, out of competition, or allowed.
     *
     * @param query The medicine name or prefix to search for.
     * @return A list of medicines with their prohibition status.
     */
    private List<String> searchMedicine(String query) {
        List<String> results = new ArrayList<>();

        // Check if the medicine is prohibited
        for (String medicine : allProhibitedMedicines) {
            if (medicine.toLowerCase().startsWith(query.toLowerCase())) {
                if (prohibitedInAndOutOfCompetition.contains(medicine)) {
                    results.add(medicine + " - Prohibited In and Out of Competition");
                } else if (prohibitedInCompetition.contains(medicine)) {
                    results.add(medicine + " - Prohibited In Competition");
                } else if (prohibitedOutOfCompetition.contains(medicine)) {
                    results.add(medicine + " - Prohibited Out of Competition");
                }
            }
        }

        // If no prohibited matches, mark as allowed
        if (results.isEmpty()) {
            results.add(query + " - Allowed");
        }

        return results;
    }

    /**
     * Sets up the User Type Spinner with predefined options.
     */
    private void setupUserTypeSpinner() {
        Spinner userTypeSpinner = findViewById(R.id.userTypeSpinner);
        String[] userTypes = {"Athlete", "Coach", "Pharmacist", "Medical Professional", "Parent", "Sports Administrator", "Other"};
        ArrayAdapter<String> userTypeAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                userTypes
        );
        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(userTypeAdapter);
    }

    /**
     * Sets up the Sport Spinner with predefined options.
     */
    private void setupSportSpinner() {
        Spinner sportSpinner = findViewById(R.id.sportSpinner);
        String[] sports = {
                "Archery", "Athletics", "Badminton", "Baseball", "Basketball",
                "Billiards", "Biathlon", "Bowling", "Boxing", "Canoeing",
                "Cheerleading", "Chess", "Climbing", "Cricket", "Croquet",
                "Cycling", "Darts", "Disc Golf", "Dodgeball", "Equestrian",
                "Fencing", "Field Hockey", "Fishing", "Floorball", "Football",
                "Golf", "Gymnastics", "Handball", "Hiking", "Hockey",
                "Ice Hockey", "Ice Skating", "Judo", "Kabaddi", "Karate",
                "Kayaking", "Kickboxing", "Kitesurfing", "Lacrosse", "Luge",
                "Martial Arts", "Mixed Martial Arts (MMA)", "Modern Pentathlon", "Motorsport",
                "Mountain Biking", "Netball", "Orienteering", "Paddleboarding", "Paragliding",
                "Parkour", "Pickleball", "Polo", "Powerlifting", "Racquetball",
                "Rock Climbing", "Rowing", "Rugby", "Sailing", "Shooting",
                "Skateboarding", "Skiing", "Snowboarding", "Soccer", "Softball",
                "Speed Skating", "Squash", "Surfing", "Swimming", "Table Tennis",
                "Tennis", "Track and Field", "Triathlon", "Ultimate Frisbee", "Volleyball",
                "Wakeboarding", "Water Polo", "Weightlifting", "Windsurfing", "Wrestling"
        };
        ArrayAdapter<String> sportAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                sports
        );
        sportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportSpinner.setAdapter(sportAdapter);
    }
}