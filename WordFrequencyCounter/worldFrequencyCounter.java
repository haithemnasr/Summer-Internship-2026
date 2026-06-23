import java.io.*;
import java.util.*;
public class worldFrequencyCounter {
    public static void main(String[] args) {

        HashMap<String, Integer> set = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez saisir le nom du fichier in.txt : ");
        String cheminFichier = scanner.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] parties = ligne.split("\\s+");
                for (int i = 0; i < parties.length; i++) {
                    if (set.containsKey(parties[i])) {
                        set.put(parties[i], set.get(parties[i]) + 1);
                    } else {
                        set.put(parties[i], 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map.Entry<String, Integer>> liste = new ArrayList<>(set.entrySet());
        Collections.sort(liste, (e1, e2) -> {
            int comparaison = e2.getValue().compareTo(e1.getValue());
            if (comparaison == 0) {
                return e1.getKey().compareTo(e2.getKey());
            }
            return comparaison;
        });
        for (Map.Entry<String, Integer> entry : liste) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        scanner.close();
    }
}
