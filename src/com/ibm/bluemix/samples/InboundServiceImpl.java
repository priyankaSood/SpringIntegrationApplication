package com.ibm.bluemix.samples;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class InboundServiceImpl implements InboundService {
	
	public String getMessage(String name) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		SpringHandler helloWorld = (SpringHandler) context.getBean("params");
		String tableName = "tbl_servicetest";

		List<Person> people = helloWorld.listPeople(tableName);
		String nameOutput = "";
		for (int i = 0; i < people.size(); i++) {
			if (i == (people.size() - 1)) {
				nameOutput += people.get(i).getName() + ".";
			} else {
				nameOutput += people.get(i).getName() + " and ";
			}
		}

		String listOfNames = (name + " and " + nameOutput);
		return listOfNames;
	}
}
