package Java24;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.net.*;

public class NewLinkRotator extends JFrame implements Runnable{

String[] pageTitle = new String[7];
URI[] pageLink = new URI[7];
int current = 0;
Thread runner;
JLabel siteLabel = new JLabel();

public NewLinkRotator(){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300,100);
    FlowLayout flo = new FlowLayout();
    setLayout(flo);
    add(siteLabel);
    pageTitle = new String[]{
        "Oracle's Java site",
        "SourceForge",
        "GitHub",
        "Java in 24 Hours",
        "Sams Publishing",
        "Workbench",
        "Stack Overflow"
        };
    pageLink[0] = getURI("http://www.oracle.com/technetwork/java");
    pageLink[1] = getURI("https://sourceforge.net/");
    pageLink[2] = getURI("http://www.github.com");
    pageLink[3] = getURI("http://www.java24hours.com");
    pageLink[4] = getURI("http://www.samspublishing.com");
    pageLink[5] = getURI("http://www.workbench.cadenhead.org");
    pageLink[6] = getURI("https://stackoverflow.com/");
    Button visitButton = new Button("Visit Site");
    ActionListener act = (event) -> {
            Desktop desktop = Desktop.getDesktop();
            if (pageLink[current] != null) {
                try {
                    desktop.browse(pageLink[current]);
                    runner = null;
                    System.exit(0);
                } catch (IOException exc) {
                    // do nothing
                }
            }
        };
    visitButton.addActionListener(act);
    add(visitButton);
    setVisible(true);
    start();
}

private URI getURI(String urlText){
    URI pageURI = null;
    
    try{
        pageURI = new URI(urlText);
    } catch(URISyntaxException ex){
        // ne pravi nishto - do nothing
    }
    return pageURI;
}

public void start(){
    if(runner == null){
        runner = new Thread(this);
        runner.start();
    }
}

public void run(){
    Thread thisThread = Thread.currentThread();
    while(runner == thisThread){
        current++;
        if(current > 6){
            current = 0;
        }
        siteLabel.setText(pageTitle[current]);
        repaint();
        try{
            Thread.sleep(2000);
        } catch(InterruptedException exc){
            // ne pravi nishto - do nothing
        }
    }
}


public static void main(String[] args){
    new LinkRotator();
}
}
