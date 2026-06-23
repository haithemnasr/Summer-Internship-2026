import java.util.*;
public class gradecount {
    public static void main(String[] args){

        List<student> students = new ArrayList<>();
        students.add(new student ("Alice", "A"));
        students.add(new student ("Bob", "B"));
        students.add(new student("charlie", "A"));
        students.add(new student("David", "C"));
        students.add(new student("Eva", "B"));
        students.add(new student("Frank", "A"));
        Map<String , Integer> gradeCounts = countByGrade(students);
        for (Map.Entry<String, Integer> entry : gradeCounts.entrySet()){
            System.out.println("Grade"+entry.getKey() + ": "+ entry.getValue()+" student " );
        }
    }
    public static Map<String, Integer> countByGrade(List<student> students){
        Map<String, Integer> counts = new HashMap<>();
        for (student s: students){
            String grade=s.getgrade();
            counts.put(grade, counts.getOrDefault(grade,0)+1);
        }
        return counts;
    }
    
}