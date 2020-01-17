package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_id);
        String name = nameField.getText().toString();
        Log.v("MainActivity","Name: " + name);
        
        /**
         * Below code will create two CheckBox Objects.
         */
        CheckBox WhippedCreamBox =  (CheckBox) findViewById(R.id.Whipped_cream);
        CheckBox ChocolateCreamBox = (CheckBox) findViewById(R.id.Chocolate_cream);

        boolean hasWhippedCream = WhippedCreamBox.isChecked();
        boolean hasChocolateCream = ChocolateCreamBox.isChecked();
        Log.v("MainActivity", "Has Whipped Cream: " + hasWhippedCream);
        
        /**
        *Below code will create a order summary.
        */
        int price = calculatePrice(hasWhippedCream, hasChocolateCream);
        String summary = CreateOrderSummary(name);
        String OrderSummary = "\nAdded Whipped Cream ?: " + hasWhippedCream + "\nAdded Chocolate Cream ?: " + hasChocolateCream;
        String priceMessage = summary + OrderSummary +"\nTotal: " + "$"+ price;
        priceMessage = priceMessage + " \nThank you";
        
        /**
         * Below code will create a new intent that will show oder summary in gmail app.
         */
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: sshivlal9601@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }


    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void Increment(View view) {
        if(quantity == 100){
            Toast.makeText(this,"You can't order coffee greater then 100.",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity +1;
        display(quantity);

    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void Decrement(View view){
        if(quantity == 1){
            Toast.makeText(this,"You can't order coffee less then 1.",Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity - 1;
        display(quantity);
    }
    private int calculatePrice (Boolean hasWhippedCream, Boolean hasChocolateCream){
        int price = 5;
        if (hasWhippedCream){
            price = price + 2;
        }
        if (hasChocolateCream){
            price = price + 3;
        }

        return quantity * price;
    }
    private String CreateOrderSummary(String name){
        String summary = "Name = " + name + "\nQuantity = " + quantity;
        return summary;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayMessage(String message){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}
