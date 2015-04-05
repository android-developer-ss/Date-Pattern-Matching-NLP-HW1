/**
 * *****************************************************************************
 * Developed by: Snehal Sutar 
 * Net ID: svs130130. 
 * Description: Design and write a
 * program that recognizes simple date expressions like: “March 15”, “the 22nd
 * of November”, “Christmas”, “Labor Day”. Your program should recognize all
 * such “absolute” dates, but not “deictic” ones relative to current day like
 * “the day before yesterday”. Each edge of the graph should have a word or a
 * set of words on it. Whenever possible use classes of words to avoid having
 * too many arcs (e.g. holidays for Labor Day, Memorial Day, etc).
 * *****************************************************************************
 */
package nlphw1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Snehal
 */
public class NLPHW1 {

    //--------------------------------------------------------------------------
    //Global variable declaration.
    public static String input_file = "";
    public static String input_file_name = "input_file.txt";
    //--------------------------------------------------------------------------
    //Pattern declaration.
    public static Pattern pattern;
    //--------------------------------------------------------------------------

    /**
     * *************************************************************************
     * Initialize the variables declared globally. Create list of months and
     * holidays to compare against.
     * *************************************************************************
     */
    public static void initialize_variables() {
        try {
            FileReader file_rdr = new FileReader(input_file_name);
            BufferedReader buff_rdr = new BufferedReader(file_rdr);
            String str;
            while ((str = buff_rdr.readLine()) != null) {
                input_file = input_file + str;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NLPHW1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NLPHW1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *************************************************************************
     * Create different type of date formats to search for in the input file.
     * *************************************************************************
     */
    public static void create_patterns() {

//------------------------------------------------------------------------------
//        February 9, 2015
//        March 15
//        the 22nd ofNovember
//        the 12thof August, 1914,
//        November 21st 
//        the 11th of November 1915.
//        April 14, 1917.
//        July 24,2002.
//        the 11th of April, 
//        the 29th of March 1927,
//        May 20th morning of 1927,
//        the 7th ofAugust, 1930,
//        the 15th of May 
//        the 15th of September 
//        May 2, 1933,
//        July 7th, 1910.
//        the 1st of August, 
//        September3rd of //that year
//        September 14, 2004,
//        the 10th of November 1939.
//        January 9th, 1942
//        October 29th of �43. 
//        July 17, 1968.
//        the 4th ofSeptember, 1961,
        pattern = Pattern.compile(
                //01 Jan 2003
                "((\\d{2}\\s*)"
                + "(?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|"
                + "Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?"
                + "|Oct(?:ober)?|(Nov|Dec)(?:ember)?)"
                + "\\s*\\d{2,4})"
                + "|"
                //the 7th ofAugust, 1930,
                //the 15th of May 
                + "(([Tt]he)?(\\s*)?(\\d{1,2})([srnt][tdh])?(\\s*)(of)(\\s*)"
                + "(?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|"
                + "Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?|Oct(?:ober)?"
                + "|(Nov|Dec)(?:ember)?)"
                + "(\\s*)([.,])?(\\s*)(\\d{4})?([.,])?)"
                + "|"
                //January 9th, 1942
                //October 29th of �43. 
                //July 17, 1968
                + "((?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|"
                + "Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?"
                + "|Oct(?:ober)?|(Nov|Dec)(?:ember)?)"
                + "(("
                + "((\\s*)?(\\d{1,2})([srnt][tdh])?(\\s*))"
                + "((of)(\\s*)(.\\d{2,4}))?"
                + "(\\w+\\s*of)?"
                + "([.,])?(\\s*)?(\\d{4})?([.,])?)))"
                + "|"
                //Holiday list
                + "(((Christmas)|(Easter)|(Thanksgiving)|"
                + "(Day after Thanksgiving)|(Winter Holiday ([123456])?)|"
                + "(Day After Christmas)|(Day After Thanksgiving)|(New Year’s)|"
                + "(Martin Luther King, Jr.)|(LBJ)|"
                + "(Memorial )|(Labor)|(Veteran.s)|(President.s)|"
                + "(Texas Independence)|(San Jacinto)|(Emancipation))(\\s*)?([Dd]ay)?([Ee]ve)?)"
                + "|"
                //Matches 01/01/2001 | 1/01/2001 | 2002
                + "^((((0[13578])|([13578])|(1[02]))[\\/]?"
                + "(([1-9])|([0-2][0-9])|(3[01])))|(((0[469])|([469])|(11))"
                + "[\\/]?(([1-9])|([0-2][0-9])|(30)))|((2|02)[\\/]?(([1-9])|"
                + "([0-2][0-9]))))[\\/]\\d{4}$|^\\d{4}$"
        );
    }

    /**
     * *************************************************************************
     * Search for date patterns in the input file. If any of them are present
     * print it onto the output string.
     * *************************************************************************
     */
    public static void search_for_date_patterns() {
        Matcher pattern_matcher = pattern.matcher(input_file);

        while (pattern_matcher.find()) {
            System.out.println(pattern_matcher.group());
        }
    }

    /**
     * *************************************************************************
     * @param args the command line arguments
     * *************************************************************************
     */
    public static void main(String[] args) {
        initialize_variables();
        create_patterns();
        search_for_date_patterns();
    }
}
