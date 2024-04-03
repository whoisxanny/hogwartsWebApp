package ru.hogwarts.school.model;


import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long avatar_id;

    private String filaPath;
    private long fileSize;

    private String mediaType;

    @Lob
    private byte[] avatarsData;

    @OneToOne
    private Student student;

    public Long getAvatar_id() {
        return avatar_id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setAvatar_id(Long avatar_id) {
        this.avatar_id = avatar_id;
    }

    public String getFilaPath() {
        return filaPath;
    }

    public void setFilaPath(String filaPath) {
        this.filaPath = filaPath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getAvatarsData() {
        return avatarsData;
    }

    public void setAvatarsData(byte[] preview) {
        this.avatarsData = preview;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return fileSize == avatar.fileSize && Objects.equals(avatar_id, avatar.avatar_id) && Objects.equals(filaPath, avatar.filaPath) && Arrays.equals(avatarsData, avatar.avatarsData) && Objects.equals(student, avatar.student);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(avatar_id, filaPath, fileSize, student);
        result = 31 * result + Arrays.hashCode(avatarsData);
        return result;
    }



//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//
//    public class Main {
//        public static void main(String[] args) {
//            Configuration configuration = new Configuration().configure();
//            SessionFactory sessionFactory = configuration.buildSessionFactory();
//
//            Session session = sessionFactory.openSession();
//            Transaction transaction = session.beginTransaction();
//
//            // Создание нового объекта Person
//            Person person = new Person();
//            person.setName("John Doe");
//            person.setAge(30);
//
//            // Сохранение объекта в базе данных
//            session.save(person);
//
//            transaction.commit();
//            session.close();
//            sessionFactory.close();
//        }
//    }
}
