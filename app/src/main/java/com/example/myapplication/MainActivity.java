package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner sourceUnit;
    Spinner destinationUnit;
    EditText inputValue;
    Button convertButton;
    TextView resultOutput;
    String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sourceUnit = findViewById(R.id.source_unit_spinner);
        destinationUnit = findViewById(R.id.destination_unit_spinner);
        inputValue = findViewById(R.id.value_input);
        convertButton = findViewById(R.id.convert_button);
        resultOutput = findViewById(R.id.result_output);

        //convert button
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //error handling for no input entered
                // Check if inputValue EditText is empty
                if (inputValue.getText().toString().isEmpty()) {
                    // Show error message
                    Toast.makeText(getApplicationContext(), "Please enter a value", Toast.LENGTH_SHORT).show();
                    return; // Exit the method as there's nothing to process
                }

                // If inputValue is not empty, proceed with conversion
                try {
                    //takes input from inputValue EditText
                    double input = Double.parseDouble(inputValue.getText().toString());

                    //puts input into conversion functions depending on selection from spinners
                    result = Double.toString(convertLength(input, sourceUnit.getSelectedItem().toString(), destinationUnit.getSelectedItem().toString()));

                    //sets resultOutput TextView to the result
                    resultOutput.setText(result);
                } catch (NumberFormatException e) {
                    // Handle the case where the input cannot be parsed as a double
                    Toast.makeText(getApplicationContext(), "Invalid input format", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Used cm as a base value to convert, so this functions always converts to cm from the source unit first
    // then converts to the destination unit
    // I did it this way to save from writing every possible conversion possibility
    //This functions takes the input value, the source unit and destination unit
    //Converts to cm from source unit first, then converts to destination unit second, and returns the result
    public double convertLength(double value, String sourceUnit, String destinationUnit) {
        double result;
        double cm;

        switch (sourceUnit) {
            case "Inches":
                cm = convertToCM(value, "Inches");
                result = convertFromCM(cm, destinationUnit);
                break;
            case "Feet":
                cm = convertToCM(value, "Feet");
                result = convertFromCM(cm, destinationUnit);
                break;
            case "Yards":
                cm = convertToCM(value, "Yards");
                result = convertFromCM(cm, destinationUnit);
                break;
            case "Miles":
                cm = convertToCM(value, "Miles");
                result = convertFromCM(cm, destinationUnit);
                break;
            case "Centimeters":
                cm = convertToCM(value, "Centimeters");
                result = convertFromCM(cm, destinationUnit);
                break;
            case "Metres":
                cm = convertToCM(value, "Metres");
                result = convertFromCM(cm, destinationUnit);
                break;
            case "Kilometers":
                cm = convertToCM(value, "Kilometers");
                result = convertFromCM(cm, destinationUnit);
                break;
            default:
                System.out.println("Invalid source unit");
                return 1;
        }
        return result;
    }

    //convert to CM function, takes source unit and converts to CM
    public double convertToCM(double input, String sourceUnit) {
        switch (sourceUnit) {
            case "Inches":
                return input * 2.54;
            case "Feet":
                return input * 30.48;
            case "Yards":
                return input * 91.44;
            case "Miles":
                return input * 160934;
            case "Centimeters":
                return input;
            case "Meters":
                return input * 100;
            case "Kilometers":
                return input * 100000;
            default:
                System.out.println("Invalid destination unit");
                return 1;
        }
    }

    //convert from CM function, comes after previous function, converts form CM to destination unit
    public double convertFromCM(double cm, String destinationUnit) {
        switch (destinationUnit) {
            case "Inches":
                return cm / 2.54;
            case "Feet":
                return cm / 30.48;
            case "Yards":
                return cm / 91.44;
            case "Miles":
                return cm / 160934;
            case "Centimeters":
                return cm;
            case "Meters":
                return cm / 100;
            case "Kilometers":
                return cm / 100000;
            default:
                System.out.println("Invalid destination unit");
                return 1;
        }
    }
}