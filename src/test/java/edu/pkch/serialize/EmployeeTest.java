package edu.pkch.serialize;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeTest {
    public static final String SERIALIZE_OBJECT_FILE_PATH = "/Users/chulsea/serialize/employee.ser";

    @Test
    @Order(1)
    void serialize() throws IOException {
        Employee employee = new Employee("woowabros", "서비스개발팀");

        try (FileOutputStream fileOutputStream = new FileOutputStream(SERIALIZE_OBJECT_FILE_PATH)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(employee);
            }
        }
    }

    @Test
    @Order(2)
    void deserialize() throws IOException {
        Employee employee = null;

        try (FileInputStream fileInputStream = new FileInputStream(SERIALIZE_OBJECT_FILE_PATH)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                employee = (Employee) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        assertThat(employee.getCompany()).isEqualTo("woowabros");
        assertThat(employee.getTeam()).isEqualTo("서비스개발팀");
        assertThat(employee.getName()).isEqualTo("pkch");
        assertThat(employee.getAge()).isEqualTo(28);
    }
}
