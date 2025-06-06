package com.example.doping_defiance;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class TUEOverviewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tue_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Open WADA Prohibited List
        CardView checkProhibitedList = view.findViewById(R.id.check_prohibited_list_container);
        checkProhibitedList.setOnClickListener(v -> {
            String url = "https://www.wada-ama.org/en/prohibited-list";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        // Open TUE Process PDF
        CardView knowTUEProcess = view.findViewById(R.id.know_tue_process_container);
        knowTUEProcess.setOnClickListener(v -> {
            String pdfUrl = "https://nadaindia.yas.gov.in/wp-content/uploads/TUE-Procedure-website-2.pdf";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(pdfUrl));
            startActivity(intent);
        });

        // Open Q&A on TUE PDF
        CardView qaTUE = view.findViewById(R.id.qa_tue_container);
        qaTUE.setOnClickListener(v -> {
            String pdfUrl = "https://nadaindia.yas.gov.in/wp-content/uploads/QA-on-TUE.pdf";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(pdfUrl));
            startActivity(intent);
        });


        // Open TUE Statistics PDF
        CardView tueStatistics = view.findViewById(R.id.tue_statistics_container);
        tueStatistics.setOnClickListener(v -> {
            String pdfUrl = "https://nadaindia.yas.gov.in/wp-content/uploads/TUE-Statistics-2023.pdf";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(pdfUrl));
            startActivity(intent);
        });

        CardView tueApplicationForm = view.findViewById(R.id.tue_application_form_container);
        tueApplicationForm.setOnClickListener(v -> {
            String pdfUrl = "https://nadaindia.yas.gov.in/wp-content/uploads/TUE-Application-Form-privacy-Notice-to-the-athlete.pdf";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(pdfUrl));
            startActivity(intent);
        });
    }

}