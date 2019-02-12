package edu.miracostacollege.cs134.costalattacars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.NumberFormat;
import java.util.Locale;

import edu.miracostacollege.cs134.costalattacars.R;
import edu.miracostacollege.cs134.costalattacars.model.CarLoan;

/**
 * Main activity that lets the user enter the car price and down payment and select a loan term.
 * Lets the user see the report by pressing the "Loan report" button
 *
 * @author Dennis La
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {


    //lets create references to the 2 edit texts and 1 radio group
    private EditText carPriceEditText;
    private EditText downPaymentEditText;

    private RadioGroup loanTermRadioGroup;

    //lets make a reference to our model (CarLoan)
    private CarLoan loan;

    //lets make formatters for money and percents
    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());
    NumberFormat percent = NumberFormat.getPercentInstance(Locale.getDefault());

    /**
     * Instantiates the edit texts and the radio group. also instantiates the model
     * and makes the percent formatter use decimals to the hundredths place
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //link the controller variables to the view
        carPriceEditText = findViewById(R.id.carPriceEditText);
        downPaymentEditText = findViewById(R.id.downPaymentEditText);
        loanTermRadioGroup = findViewById(R.id.loanTermRadioGroup);

        //instantiate a new car loan
        loan = new CarLoan();

        //make percents round to the nearest hundredths
        percent.setMinimumFractionDigits(2);

    }

    /**
     * Extracts the data from the edit text views and the radio button group. updates the model.
     * Then gets data from the model and then packages the data into an intent to be sent off to
     * the LoanSummaryActivity. then starts the new activity. triggered by pressing the "Loan
     * report" button
     * @param v the button view
     */
    public void switchToLoanSummary(View v)
    {
        //1) extract data from 2 edit texts and radio group
        double carPrice;
        double downPayment;

        if(carPriceEditText.getText().toString().length() > 0)
        {
            carPrice = Double.parseDouble(carPriceEditText.getText().toString());
        }
        else
        {
            carPrice = 0;
        }
        if(downPaymentEditText.getText().toString().length() > 0)
        {
            downPayment = Double.parseDouble(downPaymentEditText.getText().toString());
        }
        else
        {
            downPayment = 0;
        }

        int loanTerm;

        //use a switch statement to determine radio button checked in the radio group
        switch (loanTermRadioGroup.getCheckedRadioButtonId())
        {
            case R.id.threeYearsRadioButton:
                loanTerm = 3;
                break;

            case R.id.fourYearsRadioButton:
                loanTerm = 4;
                break;

            case R.id.fiveYearsRadioButton:
                loanTerm = 5;
                break;

            default:
                loanTerm = 3;
                break;
        }

        //2) update the model
        loan.setPrice(carPrice);
        loan.setDownPayment(downPayment);
        loan.setLoanTerm(loanTerm);

        //3) create a new intent to share the data between activities
                                                //start activity, destination activity
        Intent loanSummaryIntent = new Intent(this, LoanSummaryActivity.class);
        //share the data
        loanSummaryIntent.putExtra("MonthlyPayment", currency.format(loan.monthlyPayment()));
        loanSummaryIntent.putExtra("CarPrice", currency.format(loan.getPrice()));
        loanSummaryIntent.putExtra("TaxRate", percent.format(loan.OCEANSIDE_TAX_RATE));
        loanSummaryIntent.putExtra("TaxAmount", currency.format(loan.taxAmount()));
        loanSummaryIntent.putExtra("DownPayment", currency.format(loan.getDownPayment()));
        loanSummaryIntent.putExtra("TotalCost", currency.format(loan.totalCost()));
        loanSummaryIntent.putExtra("BorrowedAmount", currency.format(loan.borrowedAmount()));
        loanSummaryIntent.putExtra("InterestAmount", currency.format(loan.interestAmount()));
        loanSummaryIntent.putExtra("LoanTerm", loan.getLoanTerm());


        //4) start the new activity
        startActivity(loanSummaryIntent);


    }
}
