package edu.miracostacollege.cs134.costalattacars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Activity for the loan summary. Shows the user the data for their car financing.
 * Allows the user to go back to the data entry activity (MainActivity.java) as well.
 *
 * @author Dennis La
 * @version 1.0
 */
public class LoanSummaryActivity extends AppCompatActivity {

    private TextView monthlyPaymentTextView;
    private TextView carStickerPriceTextView;
    private TextView salesTaxRateTextView;
    private TextView taxAmountTextView;
    private TextView downPaymentTextView;
    private TextView totalCostTextView;
    private TextView borrowedAmountTextView;
    private TextView interestAmountTextView;
    private TextView loanTermTextView;


    /**
     * Grabs the data from MainActivity using an intent. Uses that intent to populate the
     * textViews
     * @param savedInstanceState any saved data if applicable
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_summary);

        monthlyPaymentTextView = findViewById(R.id.monthlyPaymentTextView);
        carStickerPriceTextView = findViewById(R.id.carStickerPriceTextView);
        salesTaxRateTextView = findViewById(R.id.salesTaxRateTextView);
        taxAmountTextView = findViewById(R.id.taxAmountTextView);
        downPaymentTextView = findViewById(R.id.downPaymentTextView);
        totalCostTextView = findViewById(R.id.totalCostTextView);
        borrowedAmountTextView = findViewById(R.id.borrowedAmountTextView);
        interestAmountTextView = findViewById(R.id.interestAmountTextView);
        loanTermTextView = findViewById(R.id.loanTermTextView);

        //1. receive the intent from main activity
        Intent intent = getIntent();


        //2.populate all the text views
        monthlyPaymentTextView.setText(intent.getStringExtra("MonthlyPayment"));
        carStickerPriceTextView.setText(intent.getStringExtra("CarPrice"));
        salesTaxRateTextView.setText(intent.getStringExtra("TaxRate"));
        taxAmountTextView.setText(intent.getStringExtra("TaxAmount"));
        downPaymentTextView.setText(intent.getStringExtra("DownPayment"));
        totalCostTextView.setText(intent.getStringExtra("TotalCost"));
        borrowedAmountTextView.setText(intent.getStringExtra("BorrowedAmount"));
        interestAmountTextView.setText(intent.getStringExtra("InterestAmount"));

        if(intent.getIntExtra("LoanTerm", 0) == 3)
        {
            loanTermTextView.setText(getString(R.string.years3));
        }
        else if(intent.getIntExtra("LoanTerm", 0) == 4)
        {
            loanTermTextView.setText(getString(R.string.years4));
        }
        else if(intent.getIntExtra("LoanTerm", 0) == 5)
        {
            loanTermTextView.setText(getString(R.string.years5));
        }

    }

    /**
     * closes this activity. triggered when the "Return to data entry" button is clicked
     * @param v the button view
     */
    public void closeActivity(View v)
    {
        this.finish();
    }
}
