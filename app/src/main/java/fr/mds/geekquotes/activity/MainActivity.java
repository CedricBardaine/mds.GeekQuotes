package fr.mds.geekquotes.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import fr.mds.geekquotes.R;
import fr.mds.geekquotes.adapters.QuoteArrayAdapter;
import fr.mds.geekquotes.model.Quote;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String TAG = "GeekQuoteTag" ;

    private List<Quote> quotes = new ArrayList<>() ;
    private QuoteArrayAdapter aa ;

    private Button bt_main_add ;
    private EditText et_main_quote ;
    private ListView lv_main_list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_main_add = findViewById(R.id.bt_main_add) ;
        et_main_quote = findViewById(R.id.et_main_quote) ;
        lv_main_list = findViewById(R.id.lv_main_list) ;

        aa = new QuoteArrayAdapter(this , android.R.layout.simple_list_item_1 , quotes) ;
        lv_main_list.setAdapter(aa);

        lv_main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG , "OnItemClicked " + position) ;

                Quote selectedQuote = quotes.get(position) ;

                Intent intent = new Intent(MainActivity.this ,  DetailsQuoteActivity.class) ;
                intent.putExtra("quote"  , selectedQuote ) ;
                intent.putExtra("position"  , position ) ;

                MainActivity.this.startActivityForResult(intent , 5 );
            }
        });

        // implementation anonyme
        bt_main_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = et_main_quote.getText().toString() ;
                Log.d(TAG , "Button Click! "+ content );
                addQuote(content) ;
                et_main_quote.getText().clear();
            }
        });

//        for (int i = 0 ; i < 3 ; i++ ) quotes.add(new Quote("QuoteN°" + Math.round (Math.random() * ((3000 - 0) + 1))))  ;

        quotes.add(new Quote("Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live.")) ;
        quotes.add(new Quote("Measuring programming progress by lines of code is like measuring aircraft building progress by weight.")) ;
        quotes.add(new Quote("If debugging is the process of removing software bugs, then programming must be the process of putting them.")) ;
        quotes.add(new Quote("Programming is like writing a book... except if you miss a single comma on page 156 the whole thing makes no damn sense.")) ;

    }

    void addQuote(String strQuote) {
        Quote q = new Quote(strQuote) ;
        quotes.add(q) ;
        this.aa.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String contentQuote =  quotes.get(position).toString() ;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            float newRate = data.getExtras().getFloat("newRate") ;
            int newRateInt = Math.round(newRate) ;

            int position = data.getExtras().getInt("position") ;

            quotes.get(position).setRating(newRateInt);
            this.aa.notifyDataSetChanged();

            Log.d(TAG , ""+ newRateInt ) ;
        }
        catch(NullPointerException e) {
            e.getMessage();
            Log.e("error" , "NullPointerException while resulting from details") ;
            Toast.makeText(this, "Modification cancelled", 3 ).show();
        }



    }
}



/* // version with TextViews in Layout
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String TAG = "GeekQuoteTag" ;

    private List<Quote> quotes = new ArrayList<>() ;
    private LinearLayout ll_main_quotes ;
    private Button bt_main_add ;
    private EditText et_main_quote ;
    private ListView lv_main_list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll_main_quotes = findViewById(R.id.ll_main_quotes) ;
        bt_main_add = findViewById(R.id.bt_main_add) ;
        et_main_quote = findViewById(R.id.et_main_quote) ;
        lv_main_list = findViewById(R.id.lv_main_list) ;


        // implementation anonyme
        bt_main_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = et_main_quote.getText().toString() ;
                Log.d(TAG , "Button Click! "+ content );
                addQuote(content) ;
                et_main_quote.getText().clear();
            }
        });

        for (int i = 0 ; i < 3 ; i++ )
            quotes.add(new Quote("Quote number : " + i)) ;

        for(Quote q : quotes) {
            TextView tvQuote = new TextView(this) ;
            tvQuote.setText(q.getStrQuote()) ;
            quotes.add(new Quote()) ;
            ll_main_quotes.addView(tvQuote);
        }
    }

    void addQuote(String strQuote) {
        Quote q = new Quote(strQuote) ;
        quotes.add(q) ;
        TextView tvQuote = new TextView(this) ;
        tvQuote.setText(q.getStrQuote() );
        ll_main_quotes.addView(tvQuote);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String contentQuote =  quotes.get(position).toString() ;
    }
}*/
