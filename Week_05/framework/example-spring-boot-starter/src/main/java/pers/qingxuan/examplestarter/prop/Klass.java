package pers.qingxuan.examplestarter.prop;

import lombok.Data;

import java.util.List;

@Data
public class Klass {
    public List<Student> students;

    public void dong() {
        System.out.println(this.getStudents());
    }
}
