package com.tests.utils;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ProjectFactory {
	public static final byte Z_EDITION = 0, I_EDITION = 1, E_EDITION = 2;
	public static final byte NONE = 127;
	public static final byte AS_400_SCREENS = 0, AS_400_RPC = 1, MAINFRAME_SCREENS = 2, MAINFRAME_RPC = 3, STORED_PROCEDURES = 4, WEB_SERVICES = 5, JDBC = 6;
	public static final byte JAVA = 0, REST_API = 1, MOBILE_RESPONSIVE = 2, INTEGRATION = 3;
	public static final byte MYSQL = 0, H2 = 1, DB400 = 2, DB2 = 3;
	public static final byte WEB_SERVICE = 0, RESTFUL_SERVICE = 1;
	public static final byte EMILY = 0, DYNAMICS = 1, LIGHT = 2, HEALTHCARE = 3, BLUE = 4;
	
	public static final byte URL = 0, USER = 1, PASSWORD = 2, HOST = 3, PORT = 4, CODE_PAGE = 5, TRAIL_PATH = 6, SSL = 7, URI_MAP = 8, JDBC_TYPE = 9;
	
	public static String[] backendsNames = {"AS/400 Screens", "AS/400 RPC", "MainFrame Screens", "MainFrame RPC", "Stored Procedures", "WebServices", "JDBC"}; 
	public static String[] frontendsNames = {"Java", "REST API", "Mobile/Responsive", "Integration"}; 
	public static String[] jdbcsNames = {"MySQL", "H2", "DB400", "DB2"};
	public static String[] servicesNames = {"Web Service", "RESTful service"};
	//private static String[] themeNames = {"Emily", "Dynamics", "Light", "Healthcare", "Blue"};
	public static Map<Byte, Map<Byte, Object>> defaultProperties = new HashMap<Byte, Map<Byte,Object>>(){{
        put(AS_400_SCREENS, new HashMap<Byte,Object>(){{
        	put(HOST, "as400demo.openleacy.org");
			put(PORT, "23");
			put(CODE_PAGE, "037");
			put(TRAIL_PATH, "");
			put(SSL, false);
        }});
        put(AS_400_RPC, new HashMap<Byte,Object>(){{
			put(ProjectFactory.HOST, "as400.openleacy.org");
			put(ProjectFactory.PORT, "23");
			put(ProjectFactory.CODE_PAGE, "037");
			put(ProjectFactory.TRAIL_PATH, "");
        }});
        put(MAINFRAME_SCREENS, new HashMap<Byte,Object>(){{
			put(ProjectFactory.HOST, "192.86.32.142");
			put(ProjectFactory.PORT, "623");
			put(ProjectFactory.CODE_PAGE, "037");
			put(ProjectFactory.TRAIL_PATH, "");
        }});
        put(MAINFRAME_RPC, new HashMap<Byte,Object>(){{
			put(ProjectFactory.HOST, "http://192.86.32.142");
			put(ProjectFactory.URI_MAP, "openlegacy");
			put(ProjectFactory.PORT, "12345");
			put(ProjectFactory.CODE_PAGE, "CP037");
			put(ProjectFactory.TRAIL_PATH, "");
        }});
        put(STORED_PROCEDURES, new HashMap<Byte,Object>(){{
			put(ProjectFactory.JDBC_TYPE, ProjectFactory.MYSQL);
			put(ProjectFactory.URL, "a");
			put(ProjectFactory.USER, "");
			put(ProjectFactory.PASSWORD, "");
        }});
        put(WEB_SERVICES, new HashMap<Byte,Object>(){{
			put(ProjectFactory.URL, "http://abc");
        }});
        put(JDBC, new HashMap<Byte,Object>(){{
			put(ProjectFactory.JDBC_TYPE, ProjectFactory.MYSQL);
			put(ProjectFactory.URL, "a");
			put(ProjectFactory.USER, "");
			put(ProjectFactory.PASSWORD, "");
        }});
    }};
	
	
	//Full Edition
	public static Byte[] all_backends= {AS_400_SCREENS, AS_400_RPC, MAINFRAME_SCREENS, MAINFRAME_RPC, STORED_PROCEDURES, WEB_SERVICES, JDBC}; 
	public static Byte[] frontends= {JAVA, REST_API, MOBILE_RESPONSIVE, INTEGRATION};
	public static Byte[] all_jdbcs = {MYSQL, H2, DB400, DB2};
	public static Byte[] services = {WEB_SERVICE, RESTFUL_SERVICE};
	public static Byte[] themes = {EMILY, DYNAMICS, LIGHT, HEALTHCARE, BLUE};
	
	//Z - Edition
	public static Byte[] z_backends= {MAINFRAME_SCREENS, MAINFRAME_RPC, STORED_PROCEDURES, WEB_SERVICES, JDBC};
	public static Byte[] z_jdbcs = {H2, DB2};
	//I - Edition
	public static Byte[] i_backends= {AS_400_SCREENS, AS_400_RPC, STORED_PROCEDURES, WEB_SERVICES, JDBC}; 
	public static Byte[] i_jdbcs = {H2, DB400};
	//E - Edition
	public static Byte[] e_backends= {STORED_PROCEDURES, WEB_SERVICES, JDBC};
	public static Byte[] e_jdbcs = {MYSQL, H2};
	
	private byte edition;
	private Robot robot;
	public Byte[] backends;
	public Byte[] jdbcs;
	public ProjectFactory(Robot robot) {
		super();
		this.edition = NONE;
		this.backends = all_backends;
		this.jdbcs = all_jdbcs;
		this.robot = robot;
	}	
	public ProjectFactory(Robot robot, byte edition) {
		super();
		this.edition = edition;
		switch (edition) {
		case Z_EDITION:
			backends = z_backends;
			jdbcs = z_jdbcs;
			break;
		case I_EDITION:
			backends = i_backends;
			jdbcs = i_jdbcs;
			break;
		case E_EDITION:
			backends = e_backends;
			jdbcs = e_jdbcs;
			break;
		default:
			backends = all_backends;
			jdbcs = all_jdbcs;
			break;
		}
		this.robot = robot;
	}
	public void createNewProject(byte backend, byte frontend) {
		createProject(robot, backend, frontend, NONE, false, EMILY, defaultProperties.get(backend));
	}
	public void createNewProject(byte backend, byte frontend, Map<Byte, Object> properties) {
		createProject(robot, backend, frontend, NONE, false, EMILY, properties);
	}
	public void createNewProject(byte backend, byte frontend, byte service) {
		createProject(robot, backend, frontend, service, false, EMILY, defaultProperties.get(backend));
	}
	public void createNewProject(byte backend, byte frontend, byte service, Map<Byte, Object> properties) {
		createProject(robot, backend, frontend, service, false, EMILY, properties);
	}
	public void createNewProject(byte backend, byte frontend, byte service, boolean cachable) {
		createProject(robot, backend, frontend, service, cachable, EMILY, defaultProperties.get(backend));
	}
	public void createNewProject(byte backend, byte frontend, byte service, boolean cachable, Map<Byte, Object> properties) {
		createProject(robot, backend, frontend, service, cachable, EMILY, properties);
	}
	public void createNewProject(byte backend, byte frontend, byte service, boolean cachable, byte theme) {
		createProject(robot, backend, frontend, service, cachable, theme, defaultProperties.get(backend));
	}
	public void createNewProject(byte backend, byte frontend, byte service, boolean cachable, byte theme, Map<Byte, Object> properties) {
		createProject(robot, backend, frontend, service, cachable, theme, properties);
	}
	
	public static void typeText(Robot robot, String text) throws Exception {
		synchronized (robot){
			StringSelection stringSelection = new StringSelection(text);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);		 		
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
		}
	}
	public void tabs(Robot robot, int tabs) {
		synchronized (robot){
			robot.setAutoWaitForIdle(false);
			robot.setAutoDelay(0);
			for (int i = 0; i < tabs; i++) {
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
			}
			robot.setAutoWaitForIdle(true);
			robot.setAutoDelay(10);
		}
	}
	public static String createProjectName(byte backend, byte frontend, Byte jdbc, byte service, boolean cachable) {
		String projectName = backendsNames[backend];
		if (jdbc != null)
			projectName = projectName + "_" + jdbcsNames[jdbc];
		if (service != NONE)
			projectName = projectName + "_" + servicesNames[service];
		else
			projectName = projectName + "_" + frontendsNames[frontend];
		if (cachable)
			projectName = projectName + "_cachable";
		return projectName.replaceAll("[^A-Za-z0-9]", "_").toLowerCase();
	}
	
	public void hostConfiguration(Robot robot, byte backend, Map<Byte, Object> properties) throws Exception {
		tabs(robot, 3);
		if ((backend == STORED_PROCEDURES) || (backend == JDBC)) {
			for (int i = 0; i < Arrays.asList(jdbcs).indexOf(properties.get(JDBC_TYPE)); i++) {
				synchronized (robot) {
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
				}
			}
			tabs(robot, 1);
			typeText(robot, (String)properties.get(URL));
			tabs(robot, 1);
			typeText(robot, (String)properties.get(USER));
			tabs(robot, 1);
			typeText(robot, (String)properties.get(PASSWORD));
			tabs(robot, 2);
		} else if (backend == AS_400_SCREENS) {
			typeText(robot, (String)properties.get(HOST));
			tabs(robot, 1);
			typeText(robot, (String)properties.get(PORT));
			tabs(robot, 1);
			typeText(robot, (String)properties.get(CODE_PAGE));
			tabs(robot, 1);
			typeText(robot, (String)properties.get(TRAIL_PATH));
			tabs(robot, 2);
			if ((Boolean)properties.get(SSL)){
				synchronized (robot) {
					robot.keyPress(KeyEvent.VK_SPACE);
					robot.keyRelease(KeyEvent.VK_SPACE);
				}				 		
			}
			tabs(robot, 2);
		} else if ((backend == AS_400_RPC) || (backend == MAINFRAME_SCREENS)) {
			typeText(robot, (String)properties.get(HOST));				 		
			tabs(robot, 1);
			typeText(robot, (String)properties.get(PORT));
			tabs(robot, 1);
			typeText(robot, (String)properties.get(CODE_PAGE));
			tabs(robot, 1);
			typeText(robot, (String)properties.get(TRAIL_PATH));
			tabs(robot, 3);
		} else if (backend == MAINFRAME_RPC) {
			typeText(robot, (String)properties.get(HOST));
			tabs(robot, 1);
			typeText(robot, (String)properties.get(URI_MAP));
			tabs(robot, 1);
			typeText(robot, (String)properties.get(PORT));
			tabs(robot, 1);
			typeText(robot, (String)properties.get(CODE_PAGE));
			tabs(robot, 1);
			typeText(robot, (String)properties.get(TRAIL_PATH));
			tabs(robot, 3);
		} else if (backend == WEB_SERVICES){
			typeText(robot, (String)properties.get(URL));
			tabs(robot, 2);
		}
	}
	
	public synchronized void createProject(Robot robot, byte backend, byte frontend, byte service, boolean cachable, byte theme, Map<Byte, Object> properties) {
		try {
			robot.setAutoWaitForIdle(true);
			robot.setAutoDelay(10);
			String projectName = createProjectName(backend, frontend, (Byte)properties.get(JDBC_TYPE), service, cachable);
			System.out.println(projectName);
			StatesList statesList = new StatesList();
			do {
				synchronized (robot) {
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_N);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.keyRelease(KeyEvent.VK_N);
				}
				TimeUnit.MILLISECONDS.sleep(1);
			} while (!statesList.getState(0).isInState(robot));
			typeText(robot, "op");
			do { TimeUnit.MILLISECONDS.sleep(1); } while (!statesList.getState(1).isInState(robot));
			synchronized (robot) {
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			}
			do { TimeUnit.MILLISECONDS.sleep(1); } while (!statesList.getState(5).isInState(robot));
			tabs(robot, 4);
			for (int i = 0; i < Arrays.asList(backends).indexOf(backend); i++) {
				synchronized (robot) {
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
				}
			}
			tabs(robot, 1);
			for (int i = 0; i < Arrays.asList(frontends).indexOf(frontend); i++) {
				synchronized (robot) {
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
				}
			}
			tabs(robot, 1);
			if (frontend == INTEGRATION){
				for (int i = 0; i < Arrays.asList(services).indexOf(service); i++) {
					synchronized (robot) {
						robot.keyPress(KeyEvent.VK_RIGHT);
						robot.keyRelease(KeyEvent.VK_RIGHT);
					}
				}
				tabs(robot, 1);
			}
			typeText(robot, projectName);
			tabs(robot, 4);
			do { TimeUnit.MILLISECONDS.sleep(1); } while (!statesList.getState(3).isInState(robot));
			synchronized (robot) {
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			}
			hostConfiguration(robot, backend, properties);
			if (frontend == MOBILE_RESPONSIVE) {
				do { TimeUnit.MILLISECONDS.sleep(1); } while (!statesList.getState(3).isInState(robot));
				synchronized (robot) {
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
				}
				tabs(robot, 4);
				for (int i = 0; i < Arrays.asList(themes).indexOf(theme); i++) {
					synchronized (robot) {
						robot.keyPress(KeyEvent.VK_DOWN);
						robot.keyRelease(KeyEvent.VK_DOWN);
					}
				}
				tabs(robot, 2);
			}
			if (!cachable) {
				do { TimeUnit.MILLISECONDS.sleep(1); } while (!statesList.getState(3).isInState(robot));
				synchronized (robot) {
					robot.keyPress(KeyEvent.VK_ENTER);				 		
					robot.keyRelease(KeyEvent.VK_ENTER);
				}
				tabs(robot, 4);
				synchronized (robot) {
					robot.keyPress(KeyEvent.VK_SPACE);
					robot.keyRelease(KeyEvent.VK_SPACE);
				}
				if (service == RESTFUL_SERVICE) 
					tabs(robot, 1);
				tabs(robot, 1);
			}
			tabs(robot, 1);
			do { TimeUnit.MILLISECONDS.sleep(1); } while (!statesList.getState(4).isInState(robot));
			synchronized (robot) {
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public byte getEdition() {
		return edition;
	}
	public void setEdition(byte edition) {
		this.edition = edition;
		switch (edition) {
		case Z_EDITION:
			backends = z_backends;
			jdbcs = z_jdbcs;
			break;
		case I_EDITION:
			backends = i_backends;
			jdbcs = i_jdbcs;
			break;
		case E_EDITION:
			backends = e_backends;
			jdbcs = e_jdbcs;
			break;
		default:
			backends = all_backends;
			jdbcs = all_jdbcs;
			break;
		}
	}

	
}
