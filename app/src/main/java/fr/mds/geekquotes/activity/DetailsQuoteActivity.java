package fr.mds.geekquotes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import fr.mds.geekquotes.R;
import fr.mds.geekquotes.model.Quote;

import static fr.mds.geekquotes.activity.MainActivity.TAG;

public class DetailsQuoteActivity extends Activity {

    private Button bt_detail_back ;
    private TextView tv_detail_quoteText ;
    private TextView tv_detail_date ;
    private RatingBar rb_detail_stars ;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_quote);

        Bundle bundle = getIntent().getExtras() ;
        Quote quote = (Quote) bundle.getSerializable("quote" ) ;

        bt_detail_back = findViewById(R.id.bt_detail_back) ;
        bt_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // // go back only
                // setResult(2) ;
                // finish();

                Intent intentBack = new Intent() ;
                intentBack.putExtra("newRate" , rb_detail_stars.getRating() ) ;
                intentBack.putExtra("position" , getIntent().getExtras().getInt("position") ) ;
                setResult(28 , intentBack) ;
                finish();
            }
        });

        rb_detail_stars = findViewById(R.id.rb_detail_stars) ;
      /*  rb_detail_stars.setOnRatingBarChangeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        tv_detail_quoteText = findViewById(R.id.tv_detail_quoteText) ;
        tv_detail_date = findViewById(R.id.tv_detail_date) ;


        tv_detail_quoteText.setText(quote.getStrQuote());
        tv_detail_date.setText(quote.getCreationDate().toString());
        rb_detail_stars.setRating(quote.getRating() );

        Log.d(TAG , "Quote" + quote.toString() ) ;
    }
}
