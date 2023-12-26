import com.agricultural.dbchange.ProductDB;
import com.agricultural.iframe.Login;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        try
        {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        }
        catch(Exception e)
        {
            System.out.println("主题应用失败");
        }
        Login login = new Login();
    }
}