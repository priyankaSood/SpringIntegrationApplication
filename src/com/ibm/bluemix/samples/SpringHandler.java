package com.ibm.bluemix.samples;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class SpringHandler {
    @SuppressWarnings("unused")
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createTable(String tableName) {
        try {
            jdbcTemplate.execute("drop table " + tableName);
        } catch (Exception e) {
            if (e.toString().contains("Unknown table 'tbl_servicetest'")) {
                //The table does not exist, this is fine as we are going to create it.
            } else {
                System.err.println(e.toString());
            }
        }

        try {
            jdbcTemplate.execute("CREATE TABLE " + tableName + "(ID INT NOT NULL AUTO_INCREMENT, NAME VARCHAR(20) NOT NULL, PRIMARY KEY (ID));");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public void createPerson(String Name, String tableName) {
        try {
            String SQL = "insert into " + tableName + " (name) values ('" + Name + "')";
            jdbcTemplate.update(SQL);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public List<Person> listPeople(String tableName) {
        List<Person> people = null;
        try {
            String SQL = "select * from " + tableName;
            people = jdbcTemplate.query(SQL, new PeopleMapper());
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return people;
    }
}
