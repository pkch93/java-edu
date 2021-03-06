package edu.pkch.serialize;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {
    public static final String SERIALIZE_OBJECT_FILE_PATH = "/Users/chulsea/serialize/person.ser";

    @Test
    @Order(1)
    void serialize() throws IOException {
        Person person = new Person("pkch", 28);

        try (FileOutputStream fileOutputStream = new FileOutputStream(SERIALIZE_OBJECT_FILE_PATH)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(person);
            }
        }
    }

    @Test
    @Order(2)
    void deserialize() throws IOException {
        Person person = null;

        try (FileInputStream fileInputStream = new FileInputStream(SERIALIZE_OBJECT_FILE_PATH)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                person = (Person) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        assertThat(person.getName()).isEqualTo("pkch");
        assertThat(person.getAge()).isEqualTo(28);
    }
}