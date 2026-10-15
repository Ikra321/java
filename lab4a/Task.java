import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task {
    public static void main(String[] args) {
        String jsonFileName = "session.json";
        String schemeFileName = "session_schema.json";
        String invalidJsonName = "invalid_session.json";

        try {
            Session session = new Session(3);
            session.addSubject("Чифры", 9, true);
            session.addSubject("ПП", 9, true);

            JSONObject json = new JSONObject();
            json.put("sessionNumber", session.getSessionNumber());

            JSONArray subjects = new JSONArray();
            for (Subject sub : session.getSubjects()) {
                JSONObject subFile = new JSONObject();
                subFile.put("name", sub.getName());
                subFile.put("grade", sub.getGrade());
                subFile.put("isExam", sub.isExam());
                subjects.put(subFile);
            }
            json.put("subjects", subjects);

            try (FileWriter writer = new FileWriter(jsonFileName)) {
                writer.write(json.toString(4));
            }
            System.out.println("Объект сохранен в файл.");

            JSONObject scheme = new JSONObject();
            scheme.put("sessionNumber", "Number");
            scheme.put("subjects", "Array");

            try (FileWriter writer = new FileWriter(schemeFileName)) {
                writer.write(scheme.toString(4));
            }

            System.out.println("\nПроверка корректного файла данных:");
            checkAndPrint(jsonFileName, schemeFileName);

            String badJsonContent = "{\n" +
                    "  \"sessionNumber\": \"первая\",\n" +
                    "  \"subjects\": []\n" +
                    "}";
            Files.writeString(Paths.get(invalidJsonName), badJsonContent);
            System.out.println("\nСоздан поломанный файл:" + invalidJsonName);

            System.out.println("\nПроверка поломанного файла:");
            checkAndPrint(invalidJsonName, schemeFileName);

        } catch (Exception e) {
            System.out.println("Произошла ошибка при чтении файла: " + e.getMessage());
        }
    }

    public static void checkAndPrint(String jsonWay, String schemaWay) {
        try {
            JSONObject data = new JSONObject(Files.readString(Paths.get(jsonWay)));
            JSONObject scheme = new JSONObject(Files.readString(Paths.get(schemaWay)));
            boolean isValid = true;

            for (String key : scheme.keySet()) {
                if (!data.has(key)) {
                    System.out.println("В структуре данных отсутствует поле: " + key);
                    isValid = false;
                    continue;
                }

                String expectedType = scheme.getString(key);
                Object val = data.get(key);
                if (expectedType.equals("String") && !(val instanceof String)) {
                    System.out.println("Поле " + key + " должно содержать тип String. ");
                    isValid = false;
                } else if (expectedType.equals("Number") && !(val instanceof Number)) {
                    System.out.println("Поле '" + key + "' должно содержать тип Number. ");
                    isValid = false;
                } else if (expectedType.equals("Array") && !(val instanceof JSONArray)) {
                    System.out.println("Поле '" + key + "' должно содержать массив. " );
                    isValid = false;
                } else if (expectedType.equals("Boolean") && !(val instanceof Boolean)) {
                    System.out.println("Поле '" + key + "' должно содержать тип Boolean. ");
                    isValid = false;
                }
            }

            if (isValid) {
                System.out.println("Объект соответствует заданной схеме.");
                System.out.println("Содержимое сессии:");
                System.out.println("Номер сессии: " + data.getInt("sessionNumber"));
                
                JSONArray subjects = data.getJSONArray("subjects");
                System.out.println("Дисциплины:");
                for (int i = 0; i < subjects.length(); i++) {
                    JSONObject subj = subjects.getJSONObject(i);
                    System.out.println(subj.getString("name") + " (оценка: " + subj.getInt("grade") + ")");
                }
            }

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}