public class Subject {
    String name;
    int grade;
    boolean isExam;

    public Subject(String name, int grade, boolean isExam) {
        this.name = name;
        this.grade = grade;
        this.isExam = isExam;
    }

    public String getName(){
        return name;
    }

    public int getGrade(){
        return grade;
    }

    public boolean isExam(){
        return isExam;
    }
}