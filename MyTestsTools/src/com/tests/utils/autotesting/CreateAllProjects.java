package com.tests.utils.autotesting;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Map;

import com.tests.utils.ProjectFactory;

public class CreateAllProjects {
	private static final byte CURRENT_EDITION = ProjectFactory.NONE;

	public static void typeText(Robot robot, String text) {
		synchronized (robot){
			StringSelection stringSelection = new StringSelection(text);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
		}
	}
	public static void tabs(Robot robot, int tabs) {
		synchronized (robot){
			for (int i = 0; i < tabs; i++) {
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		final long startTime = System.currentTimeMillis();
		try {
			Robot robot = new Robot();
			ProjectFactory newProjectFactory = new ProjectFactory(robot, CURRENT_EDITION);
			for (int backendsIndex = 0; backendsIndex < newProjectFactory.backends.length; backendsIndex++) {
				Map<Byte, Object> properties = ProjectFactory.defaultProperties.get(newProjectFactory.backends[backendsIndex]);
				for (int frontendsIndex = 0; frontendsIndex < ProjectFactory.frontends.length; frontendsIndex++) {
					if (newProjectFactory.backends[backendsIndex] == ProjectFactory.STORED_PROCEDURES) {
						for (int jdbcsIndex = 0; jdbcsIndex < newProjectFactory.jdbcs.length; jdbcsIndex++) {
							if (newProjectFactory.jdbcs[jdbcsIndex] != ProjectFactory.H2) {
								properties.put(ProjectFactory.JDBC_TYPE, newProjectFactory.jdbcs[jdbcsIndex]);
								if (ProjectFactory.frontends[frontendsIndex] == ProjectFactory.INTEGRATION) {
									newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.WEB_SERVICE, true, ProjectFactory.EMILY, properties);
									newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.WEB_SERVICE, false, ProjectFactory.EMILY, properties);
									newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.RESTFUL_SERVICE, true, ProjectFactory.EMILY, properties);
									newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.RESTFUL_SERVICE, false, ProjectFactory.EMILY, properties);
								} else {
									newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.NONE, true, ProjectFactory.EMILY,properties);
									newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex],  ProjectFactory.NONE, false, ProjectFactory.EMILY, properties);
								}
							}
						}
					} else if (newProjectFactory.backends[backendsIndex] == ProjectFactory.JDBC) {
						for (int jdbcsIndex = 0; jdbcsIndex < newProjectFactory.jdbcs.length; jdbcsIndex++) {
							properties.put(ProjectFactory.JDBC_TYPE, newProjectFactory.jdbcs[jdbcsIndex]);
							if (ProjectFactory.frontends[frontendsIndex] == ProjectFactory.INTEGRATION) {
								newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.WEB_SERVICE, true, ProjectFactory.EMILY, properties);
								newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.WEB_SERVICE, false, ProjectFactory.EMILY, properties);
								newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.RESTFUL_SERVICE, true, ProjectFactory.EMILY, properties);
								newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.RESTFUL_SERVICE, false, ProjectFactory.EMILY, properties);
							} else {
								newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.NONE, true, ProjectFactory.EMILY, properties);
								newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.NONE, false, ProjectFactory.EMILY, properties);
							}
						}
					} else {
						if (ProjectFactory.frontends[frontendsIndex] == ProjectFactory.INTEGRATION) {
							newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.WEB_SERVICE, true, ProjectFactory.EMILY);
							newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.WEB_SERVICE, false, ProjectFactory.EMILY);
							newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.RESTFUL_SERVICE, true, ProjectFactory.EMILY);
							newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.RESTFUL_SERVICE, false, ProjectFactory.EMILY);
						} else {
							newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.NONE, true, ProjectFactory.EMILY);
							newProjectFactory.createNewProject(newProjectFactory.backends[backendsIndex], ProjectFactory.frontends[frontendsIndex], ProjectFactory.NONE, false, ProjectFactory.EMILY);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Fail!");
			final long endTime = System.currentTimeMillis();
			System.out.println("Done in: "+(endTime-startTime)+"ms");
			throw e;
		}
		final long endTime = System.currentTimeMillis();
		System.out.println("Done in: "+(endTime-startTime)+"ms");
	}

}
