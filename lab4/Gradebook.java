import java.util.ArrayList;
import java.util.List;

public class Gradebook {
    private String lastName;
    private String firstName;
    private String middleName;
    private int course;
    private int group;

    private List<Session> sessions = new ArrayList<>();

    public Gradebook(String lastName, String firstName, String middleName, int course, int group) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.course = course;
        this.group = group;
    }

    public class Subject {
        public String name;
        public int grade;
        public boolean isExam;

        public Subject(String name, int grade, boolean isExam) {
            this.name = name;
            this.grade = grade;
            this.isExam = isExam;
        }
    }

    public class Session {
        private int sessionNumber;
        private List<Subject> subjects = new ArrayList<>();

        public Session(int sessionNumber) {
            this.sessionNumber = sessionNumber;
        }

        public int getSessionNumber() {
            return sessionNumber;
        }

        public List<Subject> getSubjects() {
            return subjects;
        }

         public void addSubject(String name, int grade, boolean isExam) {
            subjects.add(new Subject(name, grade, isExam));
        }

        public double getAverage() {
            double sum = 0.0;
            int count = 0;
            for (Subject s : subjects) {
                if (s.isExam) {
                    sum += s.grade;
                    count++;
                }
            }
            if (count == 0) {
                return 0.0;
            } else {
                return sum / count;
            }
        }

       public boolean isPassed() {
    for (Subject s : subjects) {
        if (s.isExam) {
            if (s.grade < 4) {
                return false;
            }
        } else {
            if (s.grade <= 0) {
                return false;
            }
        }
    }
    return true;
}

public boolean isExcellent() {
    for (Subject s : subjects) {
        if (s.isExam) {
            if (s.grade < 9) {
                return false; 
            }
        } else {
            if (s.grade <= 0) {
                return false;
            }
        }
    }
    return true;
}
    }

    public Session addSession(int sessionNumber) {
        Session s = new Session(sessionNumber);
        sessions.add(s);
        return s;
    }

    public boolean isPassStudent() {
        if (sessions.isEmpty()) return false;
        for (Session s : sessions) {
            if (!s.isPassed()) return false;
        }
        return true;
    }

    public boolean isExcellentStudent() {
        if (sessions.isEmpty()) return false;
        for (Session s : sessions) {
            if (!s.isExcellent()) return false;
        }
        return true;
    }

    public List<Session> getSessions() { return sessions; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public int getCourse() { return course; }
    public int getGroup() { return group; }
}