package Message;

import java.io.*;
import java.util.function.*;

public class CharCounter
{
  public static String readLine() 
  {
    try 
    {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    } 
    catch (IOException e) 
    {
      return new String();
    }
  }

  public static void main(String [] args)
  {
    System.out.println("Enter any text:");
    String strInput = readLine();
//    System.out.println(new StringBuilder(strInput).reverse().toString());
    for (int iIdx = strInput.length() - 1; iIdx > 0; iIdx--)
    {
        System.out.print(strInput.charAt(iIdx));
    }
    System.out.println();

  }
}