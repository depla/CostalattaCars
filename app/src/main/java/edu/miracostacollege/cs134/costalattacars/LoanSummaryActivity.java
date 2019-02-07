package edu.miracostacollege.cs134.costalattacars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoanSummaryActivity extends AppCompatActivity {

    private TextView monthlyPaymentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_summary);

        monthlyPaymentTextView = findViewById(R.id.monthlyPaymentTextView);
        //finish the rest


        //1. receive the intent from main activity
        Intent intent = getIntent();


        //2.populate all the text views
        monthlyPaymentTextView.setText(intent.getStringExtra("MonthlyPayment"));
        //repeat for other ones



    }

    public void closeActivity(View v)
    {
        this.finish();
    }
}
