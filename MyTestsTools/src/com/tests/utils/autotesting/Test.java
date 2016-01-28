package com.tests.utils.autotesting;

import java.awt.Robot;
import java.util.HashMap;
import java.util.Map;

import com.tests.utils.ProjectFactory;

public class Test {
	public static void main(String[] args) throws Exception {
		try {
			Robot robot = new Robot();
			ProjectFactory newProjectFactory = new ProjectFactory(robot);
							 		
			newProjectFactory.createNewProject(ProjectFactory.AS_400_SCREENS, ProjectFactory.REST_API);
			newProjectFactory.createNewProject(ProjectFactory.AS_400_RPC, ProjectFactory.REST_API);
			newProjectFactory.createNewProject(ProjectFactory.MAINFRAME_SCREENS, ProjectFactory.REST_API);
			newProjectFactory.createNewProject(ProjectFactory.MAINFRAME_RPC, ProjectFactory.REST_API);
			newProjectFactory.createNewProject(ProjectFactory.STORED_PROCEDURES, ProjectFactory.REST_API);
			newProjectFactory.createNewProject(ProjectFactory.WEB_SERVICES, ProjectFactory.REST_API);
			newProjectFactory.createNewProject(ProjectFactory.JDBC, ProjectFactory.REST_API);
		} catch (Exception e){}
	}
}