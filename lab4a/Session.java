import java.util.ArrayList;
import java.util.List;

public class Session {
    private int sessionNumber;
    private List<Subject> subjects = new ArrayList<>();

    public Session(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public void addSubject(String name, int grade, boolean isExam) {
        subjects.add(new Subject(name, grade, isExam));
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public double getAverage() {
        int sum = 0;
        int count = 0;
        for (Subject s : subjects) {
            if (s.isExam) {
                sum += s.grade;
                count++;
            }
        }
        return count == 0 ? 0.0 : (double) sum / count;
    }

    public boolean isPassed() {
        for (Subject s : subjects) {
            if (s.isExam && s.grade < 4) return false;
            if (!s.isExam && s.grade <= 0) return false;
        }
        return true;
    }

    public boolean isExcellent() {
        for (Subject s : subjects) {
            if (s.isExam && s.grade < 9) return false;
            if (!s.isExam && s.grade <= 0) return false;
        }
        return true;
    }
}