package com.codename1.uikit.pheonixui;
import com.codename1.io.Log;
import com.codename1.javascript.JSFunction;
import com.codename1.javascript.JSObject;
import com.codename1.javascript.JavascriptContext;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;


/**
 *
 * @author shannah
 */
public class CKeditor extends Container {

    private BrowserComponent browser;
    private JSObject ckeditor, javaPeer;
    private Object lock = new Object();
    private boolean initialized = false;
    private boolean initializationError = false;
    private Exception initializationException = null;

    public CKeditor(){


    }



    private void init(final Runnable afterInit){
        Display.getInstance().setProperty("WebLoadingHidden", "true");
        browser = new BrowserComponent();
        browser.getAllStyles().setPadding(0, 0, 0, 0);
        browser.getAllStyles().setMargin(0, 0, 0, 0);
        this.setLayout(new BorderLayout());
        this.addComponent(BorderLayout.CENTER, browser);

        browser.addWebEventListener("onLoad", new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                try {
                    System.out.println("In onLoad");
                    JavascriptContext c = new JavascriptContext(browser);

                    ckeditor = (JSObject)c.get("CKEDITOR.instances['ckeditor']");
                    ckeditor.call("setData", new Object[]{"<p>Some test data</p>"});
                    initialized = true;

                } catch ( Exception ex){
                    initializationError = true;
                    initializationException = ex;


                }

                afterInit.run();
            }

        });
        try {
            System.out.println("About to load file "+"ca/weblite/codename1/components/ckeditor/resources/ckeditor.html");
            browser.setURLHierarchy("ca/weblite/codename1/components/ckeditor/resources/ckeditor.html");
        } catch (IOException ex) {
            Log.e(ex);
        }
    }

    public void initLater(Runnable afterInit){
        init(afterInit);
    }

    public void initLater(){
        init(new Runnable(){public void run(){}});
    }

    public void initAndWait(){
        init(new Runnable(){

            public void run() {
                synchronized (lock){
                    lock.notifyAll();
                }
            }

        });

        Display.getInstance().invokeAndBlock(new Runnable(){

            public void run() {
                while ( !initialized && !initializationError ){

                    synchronized(lock){
                        try {
                            lock.wait();
                        } catch (InterruptedException ex) {
                            initializationError = true;
                            initializationException = ex;
                        }
                    }
                }
            }

        });
    }


    public Exception getInitializationException(){
        return initializationException;
    }


    public void setData(String content, final Runnable callback, boolean internal){
        ckeditor.call("setData", new Object[]
                {
                        content,
                        new JSFunction(){

                            public void apply(JSObject self, Object[] args) {
                                callback.run();
                            }
                        },
                        new Boolean(internal)

                }
        );
    }

    public void setData(String content, final Runnable callback){
        setData(content, callback, false);
    }

    public void setData(String content){
        setData(content, new Runnable(){ public void run(){}});
    }

    public String getData(){
        return ckeditor.callString("getData");
    }

    public void insertHtml(String html){
        ckeditor.call("insertHtml", new Object[]{html});
    }

    public void insertText(String text){
        ckeditor.call("insertText", new Object[]{text});
    }

    public void addCss(String css){
        ckeditor.call("addCss", new Object[]{css});
    }

    public boolean checkDirty(){
        Boolean val = (Boolean)ckeditor.call("checkDirty");
        return val.booleanValue();
    }

    public void resetDirty(){
        ckeditor.call("resetDirty");
    }

    public void setReadOnly(boolean readonly){
        ckeditor.call("setReadOnly", new Object[]{new Boolean(readonly)});
    }


}
