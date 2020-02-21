package fr.mds.geekquotes.model ;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Quote implements Serializable {
    private String strQuote ;
    private Date creationDate ;
    private int rating ;

    public void setStrQuote(String strQuote) { this.strQuote = strQuote; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public void setRating(int rating) { this.rating = rating; }

    public String getStrQuote() {
        return strQuote;
    }

    public Date getCreationDate() { return creationDate ; }

    public int getRating() {
        return rating;
    }

    public Quote(String strQuote, Date creationDate, int rating) {
        this.strQuote = strQuote;
        this.creationDate = creationDate;
        this.rating = rating;
    }
    public Quote() {}

    public Quote(String add) {
        this.strQuote = add;
        this.creationDate = Calendar.getInstance().getTime();
    }

    @NonNull
    @Override
    public String toString() {
        return  "name : " + getStrQuote() + "\n" +
                "date   : " + getCreationDate() + "\n" +
                "rating : " + getRating() ;
    }
}
