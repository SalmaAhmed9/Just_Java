package android.example.justjava;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity=1;
    public void submitOrder(View view) {

        EditText Text1=findViewById(R.id.Text1);
        String Value = Text1.getText().toString();

        CheckBox whippedCream = ((CheckBox) findViewById(R.id.checkbox_1));
        boolean hasWhippedCream = whippedCream.isChecked();

        CheckBox chocolate = ((CheckBox) findViewById(R.id.checkbox_2));
        boolean hasChocolate = chocolate.isChecked();

        int price=CalculatePrice(quantity, hasWhippedCream,hasChocolate);
        String priceMessage=CreateOrderSummary(quantity, hasWhippedCream,hasChocolate,Value);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for" + Value);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent); }
    }


    public void increment(View view) {
        if (quantity == 100) {
            // Show an error message as a toast (grey button)
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }


    public void decrement(View view) {
        if (quantity < 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity-1;
        displayQuantity(quantity);

    }


    private int CalculatePrice (int quantity,boolean Whippedcream, boolean chocolate)
    {
        int basePrice =5;
        if ( Whippedcream == true && chocolate == true)
        {
            return quantity*(8);
        }
      else if  ( Whippedcream == true && chocolate ==false)
        {
            return quantity*(6);
        }
        else if  ( Whippedcream == false && chocolate ==true)
        {
            return quantity*(7);
        }
      else
        return quantity*5;
    }


    public void displayQuantity( int number)
    {

            TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
            quantityTextView.setText("" + number); }





    private String CreateOrderSummary (int price,boolean Addwhipped, boolean AddChocolate,String name)
    {
        String priceMessage="Name: "+name ;
        priceMessage +="\nAdd WhippedCream? "+ Addwhipped + "\nAdd Chocolate? "+ AddChocolate;
        priceMessage +="\nQuantity: "+quantity +"\nTotal price is: $"+ (CalculatePrice(quantity,Addwhipped,AddChocolate));
        priceMessage +="\nThank You " ;
        return priceMessage;
    }


}
