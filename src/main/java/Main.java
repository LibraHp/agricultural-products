import com.agricultural.iframe.Login;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        try
        {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow; // 设置窗口边框样式
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        }
        catch(Exception e)
        {
            System.out.println("主题应用失败");
        }
        Login login = new Login();
    }
}