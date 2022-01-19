package API;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;


public class YouTubeViewer extends JFrame {	
	
public YouTubeViewer(String url) {
	NativeInterface.open();	
	
	JFrame frame = new JFrame("YouTube Viewer");           
	frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	frame.getContentPane().add(getBrowserPanel(url), BorderLayout.CENTER);
    frame.setSize(600, 400);
    frame.setLocationByPlatform(true);
	frame.setVisible(true);
	
	//NativeInterface.runEventPump();
	new Thread(new Runnable() {
        @Override
        public void run() {
            if (!NativeInterface.isEventPumpRunning())
                NativeInterface.runEventPump();
        }
    }).start();
	
	 Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            NativeInterface.close();
	        }
	    }));
}

public static JPanel getBrowserPanel(String url) {
    JPanel webBrowserPanel = new JPanel(new BorderLayout());
    JWebBrowser webBrowser = new JWebBrowser();
    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
    webBrowser.setBarsVisible(false);
    webBrowser.navigate(url);
    
    return webBrowserPanel;
}



}