/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 *`
 * @author RAHUL
 */
public class Helper {
    public static boolean deleteFile(String path)
    {
        boolean f=false;
        try
        {
            File file=new File(path);
            f=file.delete();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }
    public static boolean saveFile(InputStream in,String path)
    {
        boolean f=false;
        try
        {
            byte b[]=new byte[in.available()];
            in.read(b);
            FileOutputStream fos = new FileOutputStream(path); 
            fos.write(b);
            fos.flush();
            fos.close();
            f=true;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return f;
    } 
}
