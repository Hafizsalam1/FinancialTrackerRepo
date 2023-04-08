package org.example.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class stringToDate {
    public static Date generate(String Dateparam){
        Date date = null;
        try{
            date = new SimpleDateFormat("yyyy-MM-dd").parse(Dateparam);

        }
        catch(ParseException e){
            System.err.println(e.getMessage());

        }
        return date;


    }
}
