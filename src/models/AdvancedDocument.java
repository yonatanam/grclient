package models;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class AdvancedDocument extends PlainDocument
{
   private int max = 10;
   private String[] format = {"[0-9]+"};
   private int formatIndex;
   public static final String ONLY_NUMBERS = "OnlyNumbers";
   
   public AdvancedDocument(int max, String format) 
   { 
        this.max = max; 
        switch(format)
        {
        	case ONLY_NUMBERS:
        		formatIndex = 0;
        		break;
        }
   } 

   @Override
   public void insertString(int offs, String str, AttributeSet a)throws BadLocationException
   {
      // check string being inserted does not exceed max length
	  
      if (getLength() + str.length() > max)
      {
         // If it does, then truncate it
    	  
         str = str.substring(0, max - getLength());
      }

      if(str.matches(format[formatIndex]))
    	  super.insertString(offs, str, a);


   }
}