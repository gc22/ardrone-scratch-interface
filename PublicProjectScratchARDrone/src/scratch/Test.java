package scratch;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.Character.UnicodeBlock;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.python.core.PySystemState;
import org.python.google.common.net.HttpHeaders;
import org.python.util.PythonInterpreter;

public class Test extends AbstractHandler {

	private PythonInterpreter interpreter;
	
	public static void main(String[] args) {
		System.out.println("System: " + System.getProperty("user.dir"));
		
		Test t = new Test();
	}
	
	public Test() {
		loadSwtJar();
		
		Server server = new Server(12345);
		server.setHandler(this);
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Properties props = new Properties();
	    //props = setDefaultPythonPath(props);
	    //PySystemState state = new PySystemState();
	    //state.setClassLoader(ClassLoader.getSystemClassLoader());
		//PySystemState.initialize(System.getProperties(), props, null, null);
		
//		this.interpreter = new PythonInterpreter(); //null, state);
//		System.out.println(this.interpreter.getSystemState().path);
//		interpreter.exec("import sys");
//		interpreter.exec("print sys.path");
//		interpreter.exec("print dir(sys)");
//		interpreter.exec("print sys.modules");
//		
		//this.interpreter.exec("from scratch import Scratch, ScratchError, ScratchConnectionError");
		//this.interpreter.exec("import test_scratch");
		//this.interpreter.exec("import gui");
	}
	
	private void loadSwtJar() {
	    try {
	        Class.forName ("org.eclipse.swt.widgets.Shell");
	        return;
	    } catch (ClassNotFoundException e) {
	        System.out.println (" ! Need to add the proper swt jar: "+e.getMessage());
	    }

	    String osName = System.getProperty("os.name").toLowerCase();
	    String osArch = System.getProperty("os.arch").toLowerCase();

	    //NOTE - I have not added the mac and *nix swt jars.
	    String osPart = 
	        osName.contains("win") ? "win" :
	        osName.contains("mac") ? "cocoa" :
	        osName.contains("linux") || osName.contains("nix") ? "gtk" :
	        null;

	    if (null == osPart)
	        throw new RuntimeException ("Cannot determine correct swt jar from os.name [" + osName + "] and os.arch [" + osArch + "]");

	    String archPart = osArch.contains ("64") ? "64" : "32";

	    System.out.println ("Architecture and OS == "+archPart+"bit "+osPart);

	    String swtFileName = "swt_" +osPart + archPart +".jar";
	    String workingDir = System.getProperty("user.dir");
	    String libDir = "\\lib\\";
	    File file = new File(workingDir.concat(libDir), swtFileName);
	    if (!file.exists ())
	        System.out.println("Can't locate SWT Jar " + file.getAbsolutePath());

	    try {
	        URLClassLoader classLoader = (URLClassLoader) getClass().getClassLoader ();
	        Method addUrlMethod = URLClassLoader.class.getDeclaredMethod ("addURL", URL.class);
	        addUrlMethod.setAccessible (true);

	        URL swtFileUrl = file.toURI().toURL();
	        //System.out.println("Adding to classpath: " + swtFileUrl);
	        addUrlMethod.invoke (classLoader, swtFileUrl);
	    }
	    catch (Exception e) {
	        throw new RuntimeException ("Unable to add the swt jar to the class path: " + file.getAbsoluteFile (), e);
	    }
	}
	
	public void GUI(){
	}
	
	/**
	 * Adds user.dir into python.path to make Jython look for python modules in working directory in all cases
	 * (both standalone and not standalone modes)
	 * @param props
	 * @return props
	 */
	private Properties setDefaultPythonPath(Properties props) {
	    String pythonPathProp = props.getProperty("python.path");
	    String new_value;
	    if (pythonPathProp==null)
	    {
	        new_value  = System.getProperty("user.dir");
	    } else {
	        new_value = pythonPathProp +java.io.File.pathSeparator + System.getProperty("user.dir") + java.io.File.pathSeparator;
	    }
	    props.setProperty("python.path",new_value);
	    return props;
	}
	
	public void addFile(){
	}

	@Override
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if(!target.startsWith("/poll")){
			System.out.println(target);
		}
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("volume 100");
	}
}
