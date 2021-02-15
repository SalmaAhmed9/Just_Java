package android.example.justjava;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
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
            int price=CalculatePrice(quantity);
            String priceMessage=CreateOrderSummary(quantity, hasWhippedCream,hasChocolate,Value);
            displayMessage(priceMessage);

                    }


     public void increment(View view) {
           quantity = quantity+1;
displayQuantity(quantity);
    }
    public void decrement(View view) {
           quantity = quantity-1;
displayQuantity(quantity);

    }
    private int CalculatePrice (int quantity)
    {
        return quantity*5;

    }
public void displayQuantity( int number)
{
    if (quantity<0){System.out.println("please increment coffee");}
    else
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number); }

}

/**
     * This method displays the given quantity value on the screen.
     */
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }
    /**
     * This method displays the given price on the screen.
     */
    private String CreateOrderSummary (int price,boolean Addwhipped, boolean AddChocolate,String name)
    {
        String priceMessage="Name: "+name ;
        priceMessage +="\nAdd WhippedCream? "+ Addwhipped + "\nAdd Chocolate? "+ AddChocolate;
        priceMessage +="\nQuantity: "+quantity +"\nTotal price is: $"+ (CalculatePrice(quantity));
        priceMessage +="\nThank You " ;
        return priceMessage;
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}
