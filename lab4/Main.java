import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Gradebook> students = readFile("input.txt");
        if (students.isEmpty()) {
            return;
        }

        writeExcellentStudents(students, "best_students.txt");
        writeAverageScores(students, "average.txt");
    }

    private static List<Gradebook> readFile(String filename) {
        List<Gradebook> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            if (line == null) return students;
            
            int studentsCount = Integer.parseInt(line.trim());

            for (int i = 0; i < studentsCount; i++) {
                String fioLine;
                while ((fioLine = br.readLine()) != null && fioLine.trim().isEmpty());
                String[] fio = fioLine.trim().split("\\s+");

                int course = Integer.parseInt(br.readLine().trim());
                int group = Integer.parseInt(br.readLine().trim());

                Gradebook student = new Gradebook(fio[0], fio[1], fio[2], course, group);

                int sessionsCount = Integer.parseInt(br.readLine().trim());
                
                for (int j = 0; j < sessionsCount; j++) {
                    String sessionLine = br.readLine().trim();
                    String[] sessionParts = sessionLine.split(":");
                    int sessionNum = Integer.parseInt(sessionParts[0].replace("Сессия", "").trim());

                    Gradebook.Session session = student.addSession(sessionNum);

                    if (sessionParts.length > 1 && !sessionParts[1].trim().isEmpty()) {
                        String[] subjectsData = sessionParts[1].split(";");

                        for (String subData : subjectsData) {
                            if (subData.trim().isEmpty()) continue;

                            String[] parts = subData.split(",");
                            String name = parts[0].trim();
                            String gradeStr = parts[1].trim().toLowerCase();
                            String statusStr = parts[2].trim().toLowerCase();

                            boolean isExam;
                            int grade;

                            if (gradeStr.matches("\\d+")) {
                                isExam = true;
                                grade = Integer.parseInt(gradeStr);
                            } else {
                                isExam = false;
                                grade = ((gradeStr.equals("зачтено")) && (statusStr.equals("сдал"))) ? 1 : 0;
                            }

                            session.addSubject(name, grade, isExam);
                        }
                    }
                }
                students.add(student);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return students;
    }

    private static void writeExcellentStudents(List<Gradebook> students, String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Gradebook student : students) {
                if (student.isExcellentStudent()) {
                    for (Gradebook.Session session : student.getSessions()) {
                        for (Gradebook.Subject subject : session.getSubjects()) {
                            if (subject.isExam) {
                                pw.println(student.getLastName() + ", " + student.getFirstName() + ", " + student.getMiddleName() + ", " + student.getCourse() + ", " + student.getGroup() + ", " + session.getSessionNumber() + ", " + subject.name + ", " + subject.grade);
                            } else {
                                pw.println(student.getLastName() + ", " + student.getFirstName() + ", " + student.getMiddleName() + ", " + student.getCourse() + ", " + student.getGroup() + ", " + session.getSessionNumber() + ", " + subject.name);
                            }
                        }
                    }
                }
            }
            System.out.println("Файл с отличниками записан.");
        } catch (IOException e) {
            System.out.println("Ошибка записи файла отличников(");
        }
    }

    private static void writeAverageScores(List<Gradebook> students, String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Gradebook student : students) {
                if (student.isPassStudent()) {
                    pw.println("Студент: " + student.getLastName() + " " + student.getFirstName() + " " + 
                                student.getMiddleName() + " Курс: " + student.getCourse() + " Группа: " + student.getGroup());
                    
                    for (Gradebook.Session session : student.getSessions()) {
                        String resultAverage = String.format("%.2f", session.getAverage());
                        pw.println("  Сессия номер " + session.getSessionNumber() + " — Средний балл: " + resultAverage);
                    }
                }
            }
            System.out.println("Файл со средними баллами записан.");
        } catch (IOException e) {
            System.out.println("Ошибка записи файла со средним баллом.");
        }
    }
}