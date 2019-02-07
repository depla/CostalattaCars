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

public class MainActivity extends AppCompatActivity {


    //lets create references to the 2 edit texts and 1 radio group
    private EditText carPriceEditText;
    private EditText downPaymentEditText;

    private RadioGroup loanTermRadioGroup;

    //lets make a reference to our model (CarLoan)
    private CarLoan loan;

    //lets make formaters for money and percents
    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());

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



    }

    public void switchToLoanSummary(View v)
    {
        //1) extract data from 2 edit texts and radio group
        double carPrice = Double.parseDouble(carPriceEditText.getText().toString());
        double downPayment = Double.parseDouble(downPaymentEditText.getText().toString());

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
        //need to add the other 7


        //4) start the new activity
        startActivity(loanSummaryIntent);
    }
}
