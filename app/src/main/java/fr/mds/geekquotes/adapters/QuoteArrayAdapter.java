package fr.mds.geekquotes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;

import fr.mds.geekquotes.R;
import fr.mds.geekquotes.model.Quote;

public class QuoteArrayAdapter extends ArrayAdapter<Quote> {

    public QuoteArrayAdapter(@NonNull Context context, int resource, @NonNull List<Quote> quotes) {
        super(context, resource, quotes);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Quote quote = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1 , parent, false);

        // Lookup view for data population
        TextView tvT1 = (TextView) convertView.findViewById(android.R.id.text1);

        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm") ;


        String theQuoteName = quote.getStrQuote() ;
        String theQuoteDate = format.format( quote.getCreationDate() ) ;
        int theQuoteRate = quote.getRating() ;
        if (theQuoteName.length() > 30 ) theQuoteName = theQuoteName.substring(0 , 30) + "..." ;
        else theQuoteName = theQuoteName ;

        tvT1.setText("Name  : " + theQuoteName + "\n" +
                "Date    : " + theQuoteDate + "\n" +
                "Rating : " + theQuoteRate );
//        tvT1.setText(""+position);

        if (position % 2 == 0 )
            tvT1.setBackgroundResource(R.color.colorPrimaryLight);

        else
            tvT1.setBackgroundResource(R.color.colorWhite);


        return convertView;
    }
}
