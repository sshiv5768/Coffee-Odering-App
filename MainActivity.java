package com.example.android.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String summary = CreateOrderSummary();
        String priceMessage = summary +"\nTotal: " + "$"+ price;
        priceMessage = priceMessage + " \nThank you";
        displayMessage(priceMessage);
    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void Increment(View view) {
        quantity = quantity +1;
        display(quantity);

    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void Decrement(View view){
        quantity = quantity - 1;
        display(quantity);
    }
    private int calculatePrice (){
        int price = quantity * 5;
        return price;
    }
    private String CreateOrderSummary(){
        String summary = "Name = Sharma Shivlal\nQuantity = " + quantity;
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